package com.example.terraformingmarscompanionapp.game;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Award;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.BuildGreenery;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.Tile;
import com.example.terraformingmarscompanionapp.game.tileSystem.TileHandler;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the game. Has more direct access to game parameters than Controller, hence
 * some functions such as card rewuirement checking are also here.
 */
public class Game implements Serializable {

    public final UpdateManager update_manager;
    public final TileHandler tile_handler;

    //Different decks
    private final HashMap<String, Card> deck;
    private HashMap<String, Card> preludes = new HashMap<>();
    private final HashMap<String, Card> corporations;
    private final HashMap<String, Card> ghosts;
    private final HashMap<String, Award> awards;
    private final HashMap<String, Card> milestones;
    private final HashMap<String, Card> all_cards;


    private final ArrayList<Player> players = new ArrayList<>();

    private Boolean server_multiplayer;
    public Boolean getServerMultiplayer() {return server_multiplayer;}

    //Storing expansions and house rules
    public GameModifiers modifiers;

    //Getters for players and decks
    public ArrayList<Player> getPlayers() {return players;}
    public HashMap<String, Card> getDeck() {return deck;}
    public HashMap<String, Card> getPreludes() {return preludes;}
    public HashMap<String, Card> getCorporations() {return corporations;}
    public HashMap<String, Card> getGhosts() {return ghosts;}
    public HashMap<String, Award> getAwards() {return awards;}
    public HashMap<String, Card> getMilestones() {return milestones;}
    public HashMap<String, Card> getAllCards() {return all_cards;}

    //Milestones and awards
    private Integer claimed_milestones = 0;
    public void claimMilestone() {claimed_milestones++;}

    private Integer claimed_awards = 0;
    public void claimAward() {
        claimed_awards++;
    }
    public Integer getClaimedAwards() {return claimed_awards;}

    //EffectCard -interface implementing cards get called to UpdateManager
    HashMap<String, EffectCard> getEffectCards() {
        HashMap<String, EffectCard> effect_cards = new HashMap<>();
        for (Map.Entry<String, Card> entry : deck.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }

        for (Map.Entry<String, Card> entry : corporations.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }

        for (Map.Entry<String, Card> entry : ghosts.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }
        return effect_cards;
    }

    //Getter for player based on name
    public Player getPlayer(String player_name) {
        for (Player player : players) {
            if (player.getName().equals(player_name)) {
                return player;
            }
        }
        return null;
    }

    //Global game state parameters, getters and setters
    private Integer global_temperature;
    public Integer getGlobalTemperature() {return global_temperature;}
    public void rawChangeTemperature(Integer value) {global_temperature += value;}

    private Integer global_oxygen;
    public Integer getGlobalOxygen() {return global_oxygen;}
    public void rawChangeOxygen(Integer value) {global_oxygen += value;}

    private Integer oceans_placed;

    private Integer venus_terraform;
    public Integer getVenusTerraform() {return venus_terraform;}

    private Integer cities_on_mars;
    public Integer getCitiesOnMars() {return cities_on_mars;}
    void addCityOnMars() {cities_on_mars++;}

    private Integer cities_in_space;
    public Integer getCitiesInSpace() {return cities_in_space;}
    void addCityInSpace() {cities_in_space++;}

    //Constructor
    public Game(
            @NotNull ArrayList<String> player_names,
            boolean corporate_era,
            boolean prelude,
            boolean colonies,
            boolean venus,
            boolean turmoil,
            boolean extra_corporations,
            boolean world_government_terraforming,
            boolean must_max_venus,
            boolean turmoil_terraforming_revision,
            boolean server_multiplayer,
            Integer map
                )
    {

        for (String player_name : player_names) {
            players.add(new Player(this, player_name));
        }

        this.modifiers = new GameModifiers(corporate_era, prelude, colonies, venus, turmoil, extra_corporations, world_government_terraforming, must_max_venus, turmoil_terraforming_revision);

        GameConstructor constructor = new GameConstructor(this, corporate_era, prelude, colonies, venus, turmoil, extra_corporations, map);
        deck = constructor.createDeck();
        corporations = constructor.createCorporations();
        ghosts = constructor.createGhosts();
        awards = constructor.createAwards();
        milestones = constructor.createMilestones();

        all_cards = new HashMap<>();

        if (prelude) {
            preludes = constructor.createPreludes();
        }

        //Using a stream would require higher API level. This keeps API level low
        for (Map.Entry<String, Card> entry : deck.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Card> entry : corporations.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Card> entry : ghosts.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Award> entry : awards.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Card> entry : milestones.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Card> entry : preludes.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }


        this.server_multiplayer = server_multiplayer;

        tile_handler = new TileHandler(this, map, venus);
        update_manager =  new UpdateManager(this, corporate_era, prelude, colonies, venus, turmoil);

        global_temperature = -30;
        global_oxygen = 0;
        oceans_placed = 0;

        cities_in_space = 0;
        cities_on_mars = 0;
        venus_terraform = 0;
    }

    //Functions for global parameter manipulation
    public void placeOcean() {oceans_placed++;}
    public Integer getOceansPlaced() {return oceans_placed;}

    public boolean raiseTemperature(Player raising_player) {
        if (global_temperature >= 8) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        global_temperature += 2;
        if (global_temperature == 0) {
            tile_handler.getCoordinatesFromPlayer(Placeable.OCEAN, GameController.getContext());
        } else if (global_temperature == -20 || global_temperature == -24) {
            raising_player.changeHeatProduction(1);
        }
        return true;
    }

    public boolean raiseOxygen(Player raising_player) {
        if (global_oxygen >= 14) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        global_oxygen++;
        if (global_oxygen == 8) {
            raiseTemperature(raising_player);
        }

        return true;
    }

    public boolean raiseVenus(Player raising_player) {
        if (venus_terraform >= 30) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        venus_terraform += 2;
        return true;
    }


    //Functions for playing cards. First part in three functions
    public CardCostPacket getRecommendedCardCost(Card card)
    {
        ArrayList<Type> single_owner = new ArrayList<>(Arrays.asList(Type.BLUE, Type.RED, Type.GREEN, Type.CORPORATION, Type.GHOST, Type.AWARD, Type.MILESTONE));

        CardCostPacket resources_to_use = checkCardCost(card);

        if (single_owner.contains(card.getType()) && card.getOwner() != null) {
            resources_to_use.reject();
        }

        if (!checkCardRequirements(card))
        {
            resources_to_use.reject();
        }

        return resources_to_use;
    }

    private CardCostPacket checkCardCost(Card card)
    {
        Player player = GameController.getCurrentPlayer();

        //Hyvin tylsä ja repetitiivinen funktio. Suosittelen minimoimaan.

        Integer actual_price = card.getPrice();
        Integer money_amount;
        Integer steel_amount = 0;
        Integer titanium_amount = 0;
        Integer heat_amount = 0;
        Integer plants_amount = 0;
        Integer floaters_amount = 0;
        Integer needed_money;
        ArrayList<Tag> card_tags = card.getTags();

        //If -tarkistukset tagialennuksille
        if (card.getType() != Type.STANDARD_PROJECT && card.getType() != Type.OTHER) {
            actual_price -= player.getCardDiscount();
        }

        if (card_tags.contains(Tag.BUILDING) && card.getType() != Type.OTHER) {
            actual_price -= player.getBuildingTagDiscount();
        }

        if (card_tags.contains(Tag.SPACE) && card.getType() != Type.OTHER) {
            actual_price -= player.getSpaceTagDiscount();
        }

        if (card_tags.contains(Tag.EARTH) && card.getType() != Type.OTHER) {
            actual_price -= player.getEarthTagDiscount();
        }

        if (card_tags.contains(Tag.SCIENCE) && card.getType() != Type.OTHER) {
            actual_price -= player.getScienceTagDiscount();
        }

        if (card_tags.contains(Tag.ENERGY) && card.getType() != Type.OTHER) {
            actual_price -= player.getEnergyTagDiscount();
        }

        if (card_tags.contains(Tag.VENUS) && card.getType() != Type.OTHER) {
            actual_price -= player.getVenusTagDiscount();
        }

        if (Card.MAIN_DECK.contains(card.getType())) {
            actual_price -= player.getNextCardDiscount();
        }

        if (actual_price < 0) {
            actual_price = 0;
        }

        //Mikäli raaka raha ei riitä, tarkistetaan voiko korvata muilla resursseilla
        if (actual_price <= player.getMoney())
        {
            return new CardCostPacket(GameController.getCurrentPlayer().getName(), actual_price, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
        }
        else
        {
            money_amount = player.getMoney();
            needed_money = actual_price - money_amount;

            /* Käytettävän teräksen ja titaanin tarkastus toimii seuraavasti:
             * Jos kortissa on käyttämiseen oikeuttava tägi, katsotaan onko pelaajan
             * ko. resurssin määrä tarpeeksi suuri maksamaan kortin, huomioiden mahdolliset korteista
             * saadut arvomuutokset resurssille. Sitten jakojäännöksen avulla poistetaan käytettyä rahaa
             * niin että resurssin arvoa ei mene hukkaan.
             *
             * Jos pelaajan resurssimäärä ei riitä, lisätään kaikki pelaajalta löytyvät resurssit muistiin
             * ja siirrytään tarkastamaan näiden kanssa seuraavan resurssin riittävyys. */

            //titanium lisäys
            if (card_tags.contains(Tag.SPACE))
            {

                if (player.getTitanium() * (3 + player.getTitaniumValueModifier()) >= needed_money)
                {
                    titanium_amount = (needed_money + needed_money % (3 + player.getTitaniumValueModifier())) / (3 + player.getTitaniumValueModifier());
                    money_amount = actual_price - titanium_amount * (3 + player.getTitaniumValueModifier());

                    if (money_amount < 0)
                        money_amount = 0;

                    return new CardCostPacket(GameController.getCurrentPlayer().getName(), money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                }
                else
                {
                    titanium_amount = player.getTitanium();
                    needed_money -= titanium_amount * (3 + player.getTitaniumValueModifier());
                }
            }

            //steel lisäys
            if (card_tags.contains(Tag.BUILDING))
            {

                if (player.getSteel() * (2 + player.getSteelValueModifier()) >= needed_money)
                {
                    steel_amount = (needed_money + needed_money % (2 + player.getSteelValueModifier())) / (2 + player.getSteelValueModifier());
                    money_amount = actual_price - (steel_amount * (2 + player.getSteelValueModifier()
                                                   + titanium_amount * (3 + player.getTitaniumValueModifier())));

                    if (money_amount < 0)
                        money_amount = 0;

                    return new CardCostPacket(GameController.getCurrentPlayer().getName(), money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                }
                else
                {
                    steel_amount = player.getSteel();
                    needed_money -= steel_amount * (2 + player.getSteelValueModifier());
                }
            }

            //TODO lisää psychrophiles check tähän jahka kortti implementoitu

            //TODO Lisää floater = venus tag rahaa jahka kortti implementoitu

            //heat lisäys
            CardCostPacket cardCostPacket;
            if (player.getHeatIsMoney() && player.getHeat() >= needed_money)
            {
                heat_amount = needed_money;
                cardCostPacket = new CardCostPacket(GameController.getCurrentPlayer().getName(), money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
            }
            else
            {
                cardCostPacket = new CardCostPacket(GameController.getCurrentPlayer().getName(), money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                cardCostPacket.reject();
            }

            return cardCostPacket;
        }
    }

    private Boolean checkCardRequirements(Card card) {

        if (GameController.getGreeneryRound() && !(card instanceof BuildGreenery)) {
            return false;
        }

        Player player = GameController.getCurrentPlayer();

        //Erittäin tylsä if-hirviö. Palauttaa true jos kaikki vaatimukset täytetty, muuten false.
        //Suosittelen lämpimästi minimoimaan tämän.
        CardRequirements requirements = card.getRequirements();

        Integer base_discount = player.getBaseTrRequirementDiscount();
        Integer venus_discount = player.getVenusTrRequirementDiscount();

        Integer unused_jokers = player.getJokerTags();

        //Erillisinä portteina, jotta mahdollisuus kirjoittaa myöhemmin tarkempi palautteen anto
        if (requirements.getMinOceans() != null && oceans_placed < requirements.getMinOceans() - base_discount) {
            return false;
        }

        if (requirements.getMinEnergyProduction() != null && player.getEnergyProduction() < requirements.getMinEnergyProduction()) {
            return false;
        }

        if (requirements.getMinPlants() != null && player.getPlants() < requirements.getMinPlants()) {
            return false;
        }

        if (requirements.getMinOxygen() != null && global_oxygen < requirements.getMinOxygen() - base_discount) {
            return false;
        }

        if (requirements.getMinScienceTags() != null && player.getScienceTags() < requirements.getMinScienceTags()) {
            if (player.getScienceTags() + unused_jokers > requirements.getMinScienceTags()) {
                unused_jokers -= (requirements.getMinScienceTags() - player.getScienceTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinJovianTags() != null && player.getJovianTags() < requirements.getMinJovianTags()) {
            if (player.getJovianTags() + unused_jokers > requirements.getMinJovianTags()) {
                unused_jokers -= (requirements.getMinJovianTags() - player.getJovianTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinSteelProduction() != null && player.getSteelProduction() < requirements.getMinSteelProduction()) {
            return false;
        }

        if (requirements.getMinGlobalCities() != null && cities_in_space + cities_on_mars < requirements.getMinGlobalCities()) {
            return false;
        }

        if (requirements.getMinPersonalCities() != null && player.getCities() < requirements.getMinPersonalCities()) {
            return false;
        }

        if (requirements.getMinVenusTr() != null && venus_terraform < requirements.getMinVenusTr() - venus_discount) {
            return false;
        }

        if (requirements.getMinTemperature() != null && global_temperature < requirements.getMinTemperature() - venus_discount) {
            return false;
        }

        if (requirements.getMinPlantProduction() != null && player.getPlantsProduction() < requirements.getMinPlantProduction()) {
            return false;
        }

        if (requirements.getMinMicrobeTags() != null && player.getMicrobeTags() < requirements.getMinMicrobeTags()) {
            if (player.getMicrobeTags() + unused_jokers > requirements.getMinMicrobeTags()) {
                unused_jokers -= (requirements.getMinMicrobeTags() - player.getMicrobeTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinAnimalTags() != null && player.getAnimalTags() < requirements.getMinAnimalTags()) {
            if (player.getAnimalTags() + unused_jokers > requirements.getMinAnimalTags()) {
                unused_jokers -= (requirements.getMinAnimalTags() - player.getAnimalTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinEarthTags() != null && player.getEarthTags() < requirements.getMinEarthTags()) {
            if (player.getEarthTags() + unused_jokers > requirements.getMinEarthTags()) {
                unused_jokers -= (requirements.getMinEarthTags() - player.getEarthTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinEnergyTags() != null && player.getEnergyTags() < requirements.getMinEnergyTags()) {
            if (player.getEnergyTags() + unused_jokers > requirements.getMinEnergyTags()) {
                unused_jokers -= (requirements.getMinEnergyTags() - player.getEnergyTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinFloaters() != null) {
            int floaters = 0;
            for (ResourceCard resource_card : player.getResourceHolders()) {
                if (resource_card.getResourceType() == ResourceCard.ResourceType.FLOATER) {
                    floaters += resource_card.getResourceAmount();
                }
            }
            if (floaters < requirements.getMinFloaters()) {
                return false;
            }
        }

        if (requirements.getPlantsForGreenery()) {
            if (player.getPlants() < (8 + player.getGreeneryPlantCostModifier())) {
                return false;
            }
        }

        if (requirements.getMinPersonalColonies() != null && player.getColonies() < requirements.getMinPersonalColonies()) {
            return false;
        }

        if (requirements.getMinTitaniumProduction() != null && player.getTitaniumProduction() < requirements.getMinTitaniumProduction()) {
            return false;
        }

        if (requirements.getMinPersonalGreeneries() != null && player.getGreeneries() < requirements.getMinPersonalGreeneries()) {
            return false;
        }

        if (requirements.getMinHeatProduction() != null && player.getHeatProduction() < requirements.getMinHeatProduction()) {
            return false;
        }

        if (requirements.getMinTr() != null && player.getTerraformingRating() < requirements.getMinTr()) {
            return false;
        }

        if (requirements.getMinVenusTags() != null && player.getVenusTags() < requirements.getMinVenusTags()) {
            if (player.getVenusTags() + unused_jokers > requirements.getMinVenusTags()) {
                unused_jokers -= (requirements.getMinVenusTags() - player.getVenusTags());
            } else {
                return false;
            }
        }

        if (requirements.getMinHeat() != null && player.getHeat() < requirements.getMinHeat()) {
            return false;
        }

        if (requirements.getMinHighestProduction() != null) {
            Integer max = 0;
            if (player.getMoneyProduction() > max) {
                max = player.getMoneyProduction();
            }

            if (player.getSteelProduction() > max) {
                max = player.getSteelProduction();
            }

            if (player.getTitaniumProduction() > max) {
                max = player.getTitaniumProduction();
            }

            if (player.getPlantsProduction() > max) {
                max = player.getPlantsProduction();
            }

            if (player.getEnergyProduction() > max) {
                max = player.getEnergyProduction();
            }

            if (player.getHeatProduction() > max) {
                max = player.getHeatProduction();
            }

            if (max < requirements.getMinHighestProduction()) {
                return false;
            }
        }

        //Always in a milestone: never in the same card as other tag requirements
        if (requirements.getMinOrganicTags() != null) {
            if (player.getPlantTags() + player.getMicrobeTags() + player.getAnimalTags() + player.getJokerTags() < requirements.getMinOrganicTags()) {
                return false;
            }
        }

        if (requirements.getMinCardsInHand() != null && player.getHandSize() < requirements.getMinCardsInHand()) {
            return false;
        }

        if (requirements.getMinCardsOnTable() != null &&
                player.getGreen().size() + player.getBlue().size() < requirements.getMinCardsOnTable()) {
            return false;
        }

        if (requirements.getMinEventsPlayed() != null && player.getRed().size() < requirements.getMinEventsPlayed()) {
            return false;
        }

        if (requirements.getMinMoneyProduction() != null && player.getMoneyProduction() < requirements.getMinMoneyProduction()) {
            return false;
        }

        if (requirements.getMinUniqueTags() != null && player.getUniqueTags() < requirements.getMinUniqueTags()) {
            return false;
        }

        if (requirements.getMinRequirementCards() != null) {
            Integer amount = 0;
            for (Card card_to_check : player.getBlue()) {
                if (card_to_check.getRequirements().getDrawableRequrement() != null) {
                    amount++;
                }
            }

            for (Card card_to_check : player.getGreen()) {
                if (card_to_check.getRequirements().getDrawableRequrement() != null) {
                    amount++;
                }
            }

            if (amount < requirements.getMinRequirementCards()) {
                return false;
            }
        }

        if (requirements.getMinPolarTiles() != null) {
            Integer amount = 0;
            for (Tile tile : player.getOwnedTiles()) {
                if (tile.getY() < 2) {
                    amount++;
                }
            }

            if (amount < requirements.getMinPolarTiles()) {
                return false;
            }
        }

        if (requirements.getMinBuildingTags() != null) {
            if (!(player.getBuildingTags() + unused_jokers > requirements.getMinBuildingTags())) {
                return false;
            }
        }


        if (requirements.getMaxTemperature() != null && global_temperature > requirements.getMaxTemperature() + base_discount) {
            return false;
        }

        if (requirements.getMaxOceans() != null && oceans_placed > requirements.getMaxOceans() + base_discount) {
            return false;
        }

        if (requirements.getMaxOxygen() != null && global_oxygen > requirements.getMaxOxygen() + base_discount) {
            return false;
        }

        if (requirements.getMaxPersonalColonies() != null && player.getColonies() > requirements.getMaxPersonalColonies()) {
            return false;
        }

        if (requirements.getMaxMilestonesClaimed() != null && claimed_milestones > requirements.getMaxMilestonesClaimed()) {
            return false;
        }

        if (requirements.getMaxAwardsClaimed() != null && claimed_awards > requirements.getMaxAwardsClaimed()) {
            return false;
        }

        return requirements.getMaxVenusTr() == null || venus_terraform <= requirements.getMaxVenusTr() + base_discount;
    }

    //Second part of playing a card. See Card -class for a more detailed explanation of playing cards
    public void playCard(Card card, CardCostPacket resources_to_use, Context context)
    {
        Player player = GameController.getCurrentPlayer();

        if (!resources_to_use.isEligible())
        {
            return;
        }

        resources_to_use.playPacket();

        if (server_multiplayer) {
            GameActions.sendCardCost(resources_to_use);
        }

        card.onPlay(player, context);
    }

    //End a generation
    void onGenerationEnd(Context context) {
        if (global_temperature >= 8 && global_oxygen >= 14 && oceans_placed >= 9) {
            GameController.gameEndPreparation();
        }
        for (Player player : players) {
            player.changeMoney(player.getMoneyProduction() + player.getTerraformingRating());
            player.changeSteel(player.getSteelProduction());
            player.changeTitanium(player.getTitaniumProduction());
            player.changePlants(player.getPlantsProduction());
            player.changeHeat(player.getEnergy());
            player.changeEnergy(-player.getEnergy());
            player.changeEnergy(player.getEnergyProduction());
            player.changeHeat(player.getHeatProduction());
            player.setRaisedTrThisGeneration(false);
            player.resetActions();
        }

        GameController.changeGeneration(context);
    }
}