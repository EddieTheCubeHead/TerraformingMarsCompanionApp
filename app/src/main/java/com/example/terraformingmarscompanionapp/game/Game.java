package com.example.terraformingmarscompanionapp.game;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game implements Serializable {

    public final UpdateManager update_manager;
    private final ArrayList<Player> players = new ArrayList<>();
    private final HashMap<String, Card> deck;

    private HashMap<String, Card> preludes = new HashMap<>();
    private final HashMap<String, Card> corporations = new HashMap<>();
    public final TileHandler tile_handler;

    public ArrayList<Player> getPlayers() {return  players;}
    public HashMap<String, Card> getDeck() {return deck;}
    public HashMap<String, Card> getPreludes() {return preludes;}
    public HashMap<String, Card> getCorporations() {return  corporations;}
    ArrayList<Card> getDeckAsList() {return new ArrayList<>(deck.values());}
    HashMap<String, EffectCard> getEffectCards() {
        HashMap<String, EffectCard> effect_cards = new HashMap<>();
        for (Map.Entry<String, Card> entry : deck.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard)entry.getValue());
            }
        }
        return effect_cards;
    }

    private Integer global_temperature;
    public Integer getGlobalTemperature() {return global_temperature;}
    private Integer global_oxygen;
    public Integer getGlobalOxygen() {return global_oxygen;}
    private final Integer oceans_placed;
    Integer getOceansPlaced() {return oceans_placed;}
    private Integer venus_terraform;
    public Integer getVenusTerraform() {return venus_terraform;}
    private Integer cities_on_mars;
    public Integer getCitiesOnMars() {return cities_on_mars;}
    void addCityOnMars() {cities_on_mars++;}
    private Integer cities_in_space;
    public Integer getCitiesInSpace() {return cities_in_space;}
    void addCityInSpace() {cities_in_space++;}

    public Game(
                    ArrayList<String> player_names,
                    boolean hellas_elysium,
                    boolean corporate_era,
                    boolean prelude,
                    boolean colonies,
                    boolean venus,
                    boolean turmoil,
                    boolean extra_corporations,
                    Integer map
                )
    {

        for (String player_name : player_names) {
            players.add(new Player(this, player_name));
        }

        GameConstructor constructor = new GameConstructor();
        deck = constructor.createDeck(this, corporate_era, prelude, colonies, venus, turmoil);

        if (prelude) {
            preludes = constructor.createPreludes();
        }

        tile_handler = new TileHandler(this, map, venus);
        update_manager =  new UpdateManager(this, corporate_era, prelude, colonies, venus, turmoil);

        global_temperature = -30;
        global_oxygen = 0;
        oceans_placed = 0;
        cities_in_space = 0;
        cities_on_mars = 0;
        venus_terraform = 0;

        //TODO viimeistele constructor
    }

    public boolean raiseTemperature(Player raising_player) {
        if (global_temperature >= 8) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        global_temperature += 2;
        return true;
    }

    public boolean raiseOxygen(Player raising_player) {
        if (global_oxygen >= 14) {
            return false;
        }
        raising_player.changeTerraformingRating(1);
        global_oxygen++;
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

    private CardCost checkCardCost(Card card, Player player) {

        Integer actual_price = card.getPrice();
        Integer money_amount;
        Integer steel_amount = 0;
        Integer titanium_amount = 0;
        Integer heat_amount = 0;
        Integer plants_amount = 0;
        Integer floaters_amount = 0;
        Integer needed_money;
        ArrayList<String> card_tags = card.getTags();

        //If -tarkistukset tagialennuksille
        if (!card_tags.contains("Standard project")) {
            actual_price -= player.getCardDiscount();
        }

        if (card_tags.contains("Building")) {
            actual_price -= player.getBuildingTagDiscount();
        }

        if (card_tags.contains("Space")) {
            actual_price -= player.getSpaceTagDiscount();
        }

        if (card_tags.contains("Earth")) {
            actual_price -= player.getEarthTagDiscount();
        }

        if (card_tags.contains("Science")) {
            actual_price -= player.getScienceTagDiscount();
        }

        if (card_tags.contains("Energy")) {
            actual_price -= player.getEnergyTagDiscount();
        }

        if (card_tags.contains("Venus")) {
            actual_price -= player.getVenusTagDiscount();
        }

        if (actual_price < 0) {
            actual_price = 0;
        }


        //Mikäli raaka raha ei riitä, ahne algoritmi tarkistamaan voiko korvata muilla resursseilla
        if (actual_price < player.getMoney()) {
            money_amount = player.getMoney();
            needed_money = actual_price - money_amount;

            if (card_tags.contains("Space")) {
                if (player.getTitanium() * (3 + player.getTitaniumValueModifier()) >= needed_money) {
                    titanium_amount = needed_money / (3 + player.getTitaniumValueModifier());
                    money_amount = actual_price - titanium_amount * (3 + player.getTitaniumValueModifier());
                    return new CardCost(money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                } else {
                    titanium_amount = player.getTitanium();
                    needed_money -= titanium_amount * (3 + player.getTitaniumValueModifier());
                }
            }

            if (card_tags.contains("Building")) {
                if (player.getSteel() * (2 + player.getSteelValueModifier()) >= needed_money) {
                    steel_amount = needed_money / (2 + player.getSteelValueModifier());
                    money_amount = actual_price - (steel_amount * (2 + player.getSteelValueModifier()
                                                   + titanium_amount * (3 + player.getTitaniumValueModifier())));
                    return new CardCost(money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                } else {
                    steel_amount = player.getTitanium();
                    needed_money -= steel_amount * (2 + player.getSteelValueModifier());
                }
            }

            //TODO lisää psychrophiles check tähän jahka kortti implementoitu

            //TODO Lisää floater = venus tag rahaa jahka kortti implementoitu

            if (player.getHeatIsMoney()) {
                if (player.getHeat() >= needed_money) {
                    return new CardCost(money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                } else {
                    return null;
                }
            }
            return null;

        } else {
            return new CardCost(actual_price, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
        }
    }

    private Boolean checkCardRequirements(Card card, Player player) {
        //Erittäin tylsä switch-case hirviö. Palauttaa true jos kaikki vaatimukset täytetty, muuten false.
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
            return false;
        }

        if (requirements.getMinAnimalTags() != null && player.getAnimalTags() < requirements.getMinAnimalTags()) {
            return false;
        }

        if (requirements.getMinEarthTags() != null && player.getEarthTags() < requirements.getMinEarthTags()) {
            return false;
        }

        if (requirements.getMinEnergyTags() != null && player.getEnergyTags() < requirements.getMinEnergyTags()) {
            return false;
        }

        if (requirements.getMinFloaters() != null) {
            int floaters = 0;
            for (ResourceCard resource_card : player.getResourceHolders()) {
                if (resource_card.getResourceType() == 4) {
                    floaters += resource_card.getResourceAmount();
                }
            }
            if (floaters < requirements.getMinFloaters()) {
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
            return false;
        }

        if (requirements.getMinHeat() != null && player.getHeat() < requirements.getMinHeat()) {
            return false;
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

        return requirements.getMaxVenusTr() == null || venus_terraform <= requirements.getMaxVenusTr() + base_discount;
    }
    
    public void playCard(Card card, Player player) {
        CardCost resources_to_use = checkCardCost(card, player);
        if (resources_to_use == null | !checkCardRequirements(card, player)) {
            return;
        }

        //TODO UI kysy haluaako pelaaja muuttaa resurssien määrää

        player.changeMoney(-resources_to_use.getMoney());
        player.changeSteel(-resources_to_use.getSteel());
        player.changeTitanium(-resources_to_use.getTitanium());
        player.changeHeat(-resources_to_use.getHeat());

        //TODO pelaajan korttiresurssien vähentäminen kun kyseinen järjestelmä implementoitu.

        card.onPlay(player);
    }

    //Tätä tarvitaan erityisesti serveripohjaisessa moninpelissä, mutta myös parissa erityistapauksessa
    //paikallisissa peleissä.
    public void changeCardResources(Card card, Integer amount) {

        if (!(card instanceof ResourceCard)) {
            return;
        }
        ResourceCard resource_holder = (ResourceCard)card;

        resource_holder.changeResourceAmount(amount);
    }

    public void onGenerationEnd() {
        if (global_temperature >= 8 && global_oxygen >= 14 && oceans_placed >= 9) {
            endGame();
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
        }
        //TODO turmoil-hommat tähän
    }

    private void endGame() {

    }
}