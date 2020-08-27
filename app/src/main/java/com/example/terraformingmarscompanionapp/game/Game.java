package com.example.terraformingmarscompanionapp.game;

import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.exceptions.GameplayException;
import com.example.terraformingmarscompanionapp.exceptions.InvalidRequirementsException;
import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.BuildGreenery;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.tileSystem.GameMap;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.Tile;
import com.example.terraformingmarscompanionapp.game.tileSystem.TileHandler;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the game. Mostly houses data but also has some important methods that either
 * manipulate the data or utilise it. Most of the logic running the game is situated in {@link GameController},
 * not here.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class Game implements Serializable {

    public final UpdateManager update_manager;
    public final TileHandler tile_handler;

    // Different decks
    private final HashMap<String, Card> deck;
    private final HashMap<String, Card> preludes;
    private final HashMap<String, Card> corporations;
    private final HashMap<String, Card> ghosts;
    private final HashMap<String, Award> awards;
    private final HashMap<String, Card> milestones;
    private final HashMap<String, Card> all_cards;

    // Storing expansions and house rules
    public GameModifiers modifiers;

    // Milestones and awards
    private Integer claimed_milestones = 0;
    private Integer claimed_awards = 0;

    private Boolean server_multiplayer;

    private Integer global_temperature;
    private Integer global_oxygen;
    private Integer oceans_placed;
    private Integer venus_terraform;
    private Integer cities_on_mars;
    private Integer cities_in_space;

    /**
     * Constructor
     *
     * @param modifiers {@link GameModifiers} the modifiers of the game
     * @param server_multiplayer {@link Boolean} representing whether the game is played through a server
     * @param map {@link GameMap} representing the map the game is played on
     */
    public Game(
            GameModifiers modifiers,
            boolean server_multiplayer,
            GameMap map) {
        this.modifiers = modifiers;

        Card.SetGame(this);

        DeckConstructor constructor = new DeckConstructor(this, map);
        deck = constructor.createDeck();
        corporations = constructor.createCorporations();
        ghosts = constructor.createGhosts();
        awards = constructor.createAwards();
        milestones = constructor.createMilestones();

        all_cards = new HashMap<>();

        if (modifiers.getPrelude()) {
            preludes = constructor.createPreludes();
        } else {
            preludes = new HashMap<>();
        }

        // Using a stream would require higher API level. This keeps API level low
        for (Map.Entry<String, Card> entry : corporations.entrySet()) {
            all_cards.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Card> entry : deck.entrySet()) {
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

        tile_handler = new TileHandler(this, map);
        update_manager =  new UpdateManager(this);

        global_temperature = -30;
        global_oxygen = 0;
        oceans_placed = 0;

        cities_in_space = 0;
        cities_on_mars = 0;
        venus_terraform = 0;
    }

    /**
     * @return {@link Boolean} whether the game is played through a server
     */
    public Boolean getServerMultiplayer() {
        return server_multiplayer;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing the main deck of the game
     */
    public HashMap<String, Card> getDeck() {
        return deck;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing the preludes of the game
     */
    public HashMap<String, Card> getPreludes() {
        return preludes;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing the corporations in the game
     */
    public HashMap<String, Card> getCorporations() {
        return corporations;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing the ghost cards used in the game
     */
    public HashMap<String, Card> getGhosts() {
        return ghosts;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing the awards of the game
     */
    public HashMap<String, Award> getAwards() {
        return awards;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing the milestones of the game
     */
    public HashMap<String, Card> getMilestones() {
        return milestones;
    }

    /**
     * @return {@link HashMap} of {@link String} and {@link Card} representing all cards in the game
     */
    public HashMap<String, Card> getAllCards() {
        return all_cards;
    }

    /**
     * A simple method to increment the claimed milestone count
     */
    public void claimMilestone() {
        claimed_milestones++;
    }

    /**
     * A simple method to increment the claimed award count
     */
    public void claimAward() {
        claimed_awards++;
    }

    /**
     * @return {@link Integer} the amount of awards claimed in the game
     */
    public Integer getClaimedAwards() {
        return claimed_awards;
    }

    /**
     * A method for getting all cards that have effects in them
     *
     * @return {@link HashMap} of {@link String} and {@link EffectCard} representing all cards implementing {@link EffectCard} in the game
     */
    HashMap<String, EffectCard> getEffectCards() {
        HashMap<String, EffectCard> effect_cards = new HashMap<>();
        for (Map.Entry<String, Card> entry : all_cards.entrySet()) {
            if (entry.getValue() instanceof EffectCard) {
                effect_cards.put(entry.getKey(), (EffectCard) entry.getValue());
            }
        }
        return effect_cards;
    }

    /**
     * @return {@link Integer} the global temperature level of the game
     */
    public Integer getGlobalTemperature() {
        return global_temperature;
    }

    /**
     * A method to modify temperature level without triggering any effects. Don't call unless you
     * are exactly sure what you're doing. Prefer {@link #raiseTemperature(Player)} instead
     *
     * @param value {@link Integer} the amount of change. Can be negative to decrease the oxygen level
     */
    public void rawChangeTemperature(Integer value) {
        global_temperature += value;
    }

    /**
     * @return {@link Integer} the global oxygen level of the game
     */
    public Integer getGlobalOxygen() {
        return global_oxygen;
    }

    /**
     * A method to modify oxygen level without triggering any effects. Don't call unless you are
     * exactly sure what you're doing. Prefer {@link #raiseOxygen(Player)} instead
     *
     * @param value {@link Integer} the amount of change. Can be negative to decrease the oxygen level
     */
    public void rawChangeOxygen(Integer value) {
        global_oxygen += value;
    }

    /**
     * @return {@link Integer} the global venus terraforming level
     */
    public Integer getVenusTerraform() {
        return venus_terraform;
    }

    /**
     * @return {@link Integer} the number of cities on the map, not in space
     */
    public Integer getCitiesOnMars() {
        return cities_on_mars;
    }

    /**
     * A simple method to increment the amount of cities on the map
     */
    void addCityOnMars() {
        cities_on_mars++;
    }

    /**
     * @return {@link Integer} the number of cities in space
     */
    public Integer getCitiesInSpace() {
        return cities_in_space;
    }

    /**
     * A simple method to increment the amount of cities in space
     */
    void addCityInSpace() {
        cities_in_space++;
    }

    /**
     * A simple method to increment the amount of placed oceans
     */
    public void placeOcean() {
        oceans_placed++;
    }

    /**
     * @return {@link Integer} the amount of oceans on the map
     */
    public Integer getOceansPlaced() {
        return oceans_placed;
    }

    /**
     * A method to increase the temperature one step and perform all actions required by it like
     * incrementing terraforming rating for the raising player.
     *
     * @param raising_player {@link Player} raising the temperature
     * @return {@link Boolean} whether the action was successful
     */
    public boolean raiseTemperature(Player raising_player) {
        if (global_temperature >= 8) {
            return false;
        }

        raising_player.getResources().setTerraformingRating(raising_player.getResources().getTerraformingRating() + 1);
        global_temperature += 2;
        if (global_temperature == 0 && oceans_placed < 9) {
            tile_handler.getCoordinatesFromPlayer(Placeable.OCEAN, GameController.getContext());
        } else if (global_temperature == -20 || global_temperature == -24) {

            // This can only raise heat production, thus it can be ignored
            try {
                raising_player.getResources().setHeatProduction(raising_player.getResources().getHeatProduction() + 1);
            } catch (InvalidResourcesException ignored) {}
        }
        return true;
    }

    /**
     * A method to increase the oxygen level one step and perform all actions required by it like
     * incrementing terraforming rating for the raising player.
     *
     * @param raising_player {@link Player} raising the temperature
     * @return {@link Boolean} whether the action was successful
     */
    public boolean raiseOxygen(Player raising_player) {
        if (global_oxygen >= 14) {
            return false;
        }

        raising_player.getResources().setTerraformingRating(raising_player.getResources().getTerraformingRating() + 1);

        global_oxygen++;
        if (global_oxygen == 8) {
            raiseTemperature(raising_player);
        }

        return true;
    }

    /**
     * A method to increase the venus terraforming level one step and perform all actions required
     * by it like incrementing terrafroming rating for the raising player.
     *
     * @param raising_player {@link Player} raising the temperature
     * @return {@link Boolean} whether the action was successful
     */
    public boolean raiseVenus(Player raising_player) {
        if (venus_terraform >= 30) {
            return false;
        }

        raising_player.getResources().setTerraformingRating(raising_player.getResources().getTerraformingRating() + 1);

        venus_terraform += 2;
        return true;
    }


    /**
     * The first method to be called when preparing to play a card. Takes a card and calls {@link #getRecommendedCardCost(Card)}
     * with the given card to get a {@link CardCostPacket} of the card, as well as checking the
     * requirements of the card and reject the play action if the requirements are not met.
     *
     * @param card {@link Card} that is being played
     * @return {@link CardCostPacket} representing the resources the player has to use for playing the card
     */
    public CardCostPacket prepareCardPlayAction(Card card) throws InvalidRequirementsException {
        ArrayList<Type> single_owner = new ArrayList<>(Arrays.asList(Type.BLUE, Type.RED, Type.GREEN, Type.CORPORATION, Type.GHOST, Type.AWARD, Type.MILESTONE));

        CardCostPacket resources_to_use = getRecommendedCardCost(card);

        // This should never happen if all menus work correctly but this is here just in case something
        // goes wrong in the UI.
        if (single_owner.contains(card.getType()) && card.getOwner() != null) {
            throw new InvalidRequirementsException("trying to play a card that has been played already");
        }

        checkCardRequirements(card);

        return resources_to_use;
    }

    /**
     * A method to get the resources a player needs to use to play a card. Takes into consideration
     * discounts and resource value increses like PhoboLog special effect.
     *
     * @param card {@link Card} which cost should be calculated
     * @return {@link CardCostPacket} representing the resources the player has to use for playing the card
     */
    private CardCostPacket getRecommendedCardCost(Card card)
    {
        Player player = GameController.getCurrentPlayer();

        Integer actual_price = card.getPrice();
        Integer money_amount;
        Integer steel_amount = 0;
        Integer titanium_amount = 0;
        Integer heat_amount = 0;
        Integer plants_amount = 0;
        Integer floaters_amount = 0;
        Integer needed_money;
        ArrayList<Tag> card_tags = card.getTags();

        // Checks for tag-type specific discounts
        if (card.getType() != Type.STANDARD_PROJECT && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getCardDiscount();
        }

        if (card_tags.contains(Tag.BUILDING) && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getBuildingTagDiscount();
        }

        if (card_tags.contains(Tag.SPACE) && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getSpaceTagDiscount();
        }

        if (card_tags.contains(Tag.EARTH) && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getEarthTagDiscount();
        }

        if (card_tags.contains(Tag.SCIENCE) && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getScienceTagDiscount();
        }

        if (card_tags.contains(Tag.ENERGY) && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getEnergyTagDiscount();
        }

        if (card_tags.contains(Tag.VENUS) && card.getType() != Type.OTHER) {
            actual_price -= player.getModifiers().getVenusTagDiscount();
        }

        if (Card.MAIN_DECK.contains(card.getType())) {
            actual_price -= player.getModifiers().getNextCardDiscount();
        }

        if (actual_price < 0) {
            actual_price = 0;
        }

        // If player doesn't have enough raw money, other resources can be used
        if (actual_price <= player.getResources().getMoney())
        {
            return new CardCostPacket(GameController.getCurrentPlayer().getName(), actual_price, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
        }
        else
        {
            money_amount = player.getResources().getMoney();
            needed_money = actual_price - money_amount;

            // Checks for used steel and titanium work as follows:
            // If the card has the corrseponding tag for the resources, the app checks if the player
            // has enough of that resource to pay for the card. Possible value added to the resource
            // by the cards player has played are calculated as well. Then modulo is used to remove
            // excess money as not to wasy any money on playing the card.

            // If the player doesn't have enough resources, all resources are dumped into the usage pool
            // and have their value added to the available funds sum. Then the program moves on to
            // checking the next resource

            // Adding titanium
            if (card_tags.contains(Tag.SPACE))
            {

                if (player.getResources().getTitanium() * (3 + player.getModifiers().getTitaniumValueModifier()) >= needed_money)
                {
                    titanium_amount = (needed_money + needed_money % (3 + player.getModifiers().getTitaniumValueModifier())) / (3 + player.getModifiers().getTitaniumValueModifier());
                    money_amount = actual_price - titanium_amount * (3 + player.getModifiers().getTitaniumValueModifier());

                    if (money_amount < 0)
                        money_amount = 0;

                    return new CardCostPacket(GameController.getCurrentPlayer().getName(), money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                }
                else
                {
                    titanium_amount = player.getResources().getTitanium();
                    needed_money -= titanium_amount * (3 + player.getModifiers().getTitaniumValueModifier());
                }
            }

            // Adding steel
            if (card_tags.contains(Tag.BUILDING))
            {

                if (player.getResources().getSteel() * (2 + player.getModifiers().getSteelValueModifier()) >= needed_money)
                {
                    steel_amount = (needed_money + needed_money % (2 + player.getModifiers().getSteelValueModifier())) / (2 + player.getModifiers().getSteelValueModifier());
                    money_amount = actual_price - (steel_amount * (2 + player.getModifiers().getSteelValueModifier()
                                                   + titanium_amount * (3 + player.getModifiers().getTitaniumValueModifier())));

                    if (money_amount < 0)
                        money_amount = 0;

                    return new CardCostPacket(GameController.getCurrentPlayer().getName(), money_amount, steel_amount, titanium_amount, heat_amount, plants_amount, floaters_amount);
                }
                else
                {
                    steel_amount = player.getResources().getSteel();
                    needed_money -= steel_amount * (2 + player.getModifiers().getSteelValueModifier());
                }
            }

            // TODO implement psychrophiles here when preludes is added

            // TODO implement dirigibles here when venus next is added

            // Adding heat
            CardCostPacket cardCostPacket;
            if (player.getModifiers().getHeatIsMoney() && player.getResources().getHeat() >= needed_money)
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

    /**
     * A method to check whether the requirements of a card have been met. Throws an exception detailing
     * the missing requirements if any exist.
     *
     * @param card {@link Card} which requirements need to be checked
     *
     * @throws InvalidRequirementsException detailing the missing requirements
     */
    private void checkCardRequirements(Card card) throws InvalidRequirementsException {

        if (GameController.getGreeneryRound() && !(card instanceof BuildGreenery)) {
            throw new InvalidRequirementsException("Can only play greeneries.");
        }

        Player player = GameController.getCurrentPlayer();

        CardRequirements requirements = card.getRequirements();

        Integer base_discount = player.getModifiers().getBaseTrRequirementDiscount();
        Integer venus_discount = player.getModifiers().getVenusTrRequirementDiscount();

        Integer unused_jokers = player.getTags().getJokerTags();

        String error_message;
        Integer amount;

        if (requirements.getMinOceans() != null && oceans_placed < requirements.getMinOceans() - base_discount) {

            amount = (requirements.getMinOceans() - oceans_placed - base_discount);

            error_message = "requires " + amount + " more ocean tile";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += ".";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinOxygen() != null && global_oxygen < requirements.getMinOxygen() - base_discount) {

            amount = (requirements.getMinOxygen() - global_oxygen - base_discount);
            error_message = "requires " + amount + "% higher oxygen level.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinScienceTags() != null && player.getTags().getScienceTags() < requirements.getMinScienceTags()) {
            if (player.getTags().getScienceTags() + unused_jokers > requirements.getMinScienceTags()) {
                unused_jokers -= (requirements.getMinScienceTags() - player.getTags().getScienceTags());
            } else {

                amount = (requirements.getMinScienceTags() - player.getTags().getScienceTags() - unused_jokers);

                error_message = "requires " + amount + " more science tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinJovianTags() != null && player.getTags().getJovianTags() < requirements.getMinJovianTags()) {
            if (player.getTags().getJovianTags() + unused_jokers > requirements.getMinJovianTags()) {
                unused_jokers -= (requirements.getMinJovianTags() - player.getTags().getJovianTags());
            } else {

                amount = (requirements.getMinJovianTags() - player.getTags().getJovianTags() - unused_jokers);

                error_message = "requires " + amount + " more jovian tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinSteelProduction() != null && player.getResources().getSteelProduction() < requirements.getMinSteelProduction()) {

            amount = (requirements.getMinSteelProduction() - player.getResources().getSteelProduction());

            error_message = "requires " + amount + " more steel production.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinGlobalCities() != null && cities_in_space + cities_on_mars < requirements.getMinGlobalCities()) {

            amount = (requirements.getMinGlobalCities() - cities_in_space - cities_on_mars);

            error_message = "requires " + amount + " more ";
            if (amount > 1) {
                error_message += "cities";
            } else {
                error_message += "city";
            }
            error_message += " in play.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinPersonalCities() != null && player.getCities() < requirements.getMinPersonalCities()) {

            amount = (requirements.getMinPersonalCities() - player.getCities());

            error_message = "requires " + amount + " more ";
            if (amount > 1) {
                error_message += "cities";
            } else {
                error_message += "city";
            }
            error_message += " you own in play.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinVenusTr() != null && venus_terraform < requirements.getMinVenusTr() - venus_discount) {

            amount = (requirements.getMinVenusTr() - venus_terraform - venus_discount);

            error_message = "requires " + amount + " point";
            if (amount > 1) {
                error_message += "s";
            }

            error_message += " higher venus terraforming rating.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinTemperature() != null && global_temperature < requirements.getMinTemperature() - venus_discount) {

            amount = (requirements.getMinTemperature() - global_temperature - base_discount * 2);

            error_message = "requires " + amount + " degrees higher global temperature";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinMicrobeTags() != null && player.getTags().getMicrobeTags() < requirements.getMinMicrobeTags()) {
            if (player.getTags().getMicrobeTags() + unused_jokers > requirements.getMinMicrobeTags()) {
                unused_jokers -= (requirements.getMinMicrobeTags() - player.getTags().getMicrobeTags());

            } else {

                amount = (requirements.getMinMicrobeTags() - player.getTags().getMicrobeTags() - unused_jokers);

                error_message = "requires " + amount + " more microbe tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinAnimalTags() != null && player.getTags().getAnimalTags() < requirements.getMinAnimalTags()) {
            if (player.getTags().getAnimalTags() + unused_jokers > requirements.getMinAnimalTags()) {
                unused_jokers -= (requirements.getMinAnimalTags() - player.getTags().getAnimalTags());
            } else {

                amount = (requirements.getMinAnimalTags() - player.getTags().getAnimalTags() - unused_jokers);

                error_message = "requires " + amount + " more animal tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinEarthTags() != null && player.getTags().getEarthTags() < requirements.getMinEarthTags()) {
            if (player.getTags().getEarthTags() + unused_jokers > requirements.getMinEarthTags()) {
                unused_jokers -= (requirements.getMinEarthTags() - player.getTags().getEarthTags());
            } else {

                amount = (requirements.getMinEarthTags() - player.getTags().getEarthTags() - unused_jokers);

                error_message = "requires " + amount + " more earth tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinEnergyTags() != null && player.getTags().getEnergyTags() < requirements.getMinEnergyTags()) {
            if (player.getTags().getEnergyTags() + unused_jokers > requirements.getMinEnergyTags()) {
                unused_jokers -= (requirements.getMinEnergyTags() - player.getTags().getEnergyTags());
            } else {

                amount = (requirements.getMinEnergyTags() - player.getTags().getEnergyTags() - unused_jokers);

                error_message = "requires " + amount + " more energy tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinFloaters() != null) {
            int floaters = 0;
            for (Card resource_card : all_cards.values()) {
                if (resource_card instanceof ResourceCard && resource_card.getOwner() == player) {
                    if (((ResourceCard) resource_card).getResourceType() == ResourceCard.ResourceType.FLOATER) {
                        floaters += ((ResourceCard) resource_card).getResourceAmount();
                    }
                }
            }
            if (floaters < requirements.getMinFloaters()) {

                amount = (requirements.getMinFloaters() - floaters);

                error_message = "requires " + amount + " more floater";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getPlantsForGreenery()) {
            if (player.getResources().getPlants() < (8 + player.getModifiers().getGreeneryPlantCostModifier())) {

                amount = ((8 + player.getModifiers().getGreeneryPlantCostModifier()) - player.getResources().getPlants());

                error_message = "requires " + amount + " more plant";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinPersonalColonies() != null && player.getColonies() < requirements.getMinPersonalColonies()) {

            amount = (requirements.getMinPersonalColonies() - player.getColonies());

            error_message = "requires " + amount + " more ";
            if (amount > 1) {
                error_message += "colonies";
            } else {
                error_message += "colony";
            }
            error_message += " you own in play.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinTitaniumProduction() != null && player.getResources().getTitaniumProduction() < requirements.getMinTitaniumProduction()) {

            amount = (requirements.getMinTitaniumProduction() - player.getResources().getTitaniumProduction());

            error_message = "requires " + amount + " more titanium.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinPersonalGreeneries() != null && player.getGreeneries() < requirements.getMinPersonalGreeneries()) {

            amount = (requirements.getMinPersonalGreeneries() - player.getGreeneries());

            error_message = "requires " + amount + " more ";
            if (amount > 1) {
                error_message += "greeneries";
            } else {
                error_message += "greenery";
            }
            error_message += " you own in play.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinTr() != null && player.getResources().getTerraformingRating() < requirements.getMinTr()) {

            amount = (requirements.getMinTr() - player.getResources().getTerraformingRating());

            error_message = "requires " + amount + " point";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += " higher terraforming rating.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinVenusTags() != null && player.getTags().getVenusTags() < requirements.getMinVenusTags()) {
            if (player.getTags().getVenusTags() + unused_jokers > requirements.getMinVenusTags()) {
                unused_jokers -= (requirements.getMinVenusTags() - player.getTags().getVenusTags());
            } else {

                amount = (requirements.getMinVenusTags() - player.getTags().getVenusTags() - unused_jokers);

                error_message = "requires " + amount + " more venus tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinHighestProduction() != null) {
            Integer max = 0;
            if (player.getResources().getMoneyProduction() > max) {
                max = player.getResources().getMoneyProduction();
            }

            if (player.getResources().getSteelProduction() > max) {
                max = player.getResources().getSteelProduction();
            }

            if (player.getResources().getTitaniumProduction() > max) {
                max = player.getResources().getTitaniumProduction();
            }

            if (player.getResources().getPlantsProduction() > max) {
                max = player.getResources().getPlantsProduction();
            }

            if (player.getResources().getEnergyProduction() > max) {
                max = player.getResources().getEnergyProduction();
            }

            if (player.getResources().getHeatProduction() > max) {
                max = player.getResources().getHeatProduction();
            }

            if (max < requirements.getMinHighestProduction()) {

                amount = (requirements.getMinHighestProduction() - max);

                error_message = "highest production must be " + amount + " point";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += " higher.";

                throw new InvalidRequirementsException(error_message);
            }
        }

        // Always in a milestone: never in the same card as other tag requirements
        if (requirements.getMinOrganicTags() != null) {
            if (player.getTags().getPlantTags() + player.getTags().getMicrobeTags() + player.getTags().getAnimalTags() + player.getTags().getJokerTags() < requirements.getMinOrganicTags()) {

                amount = (requirements.getMinOrganicTags() - (player.getTags().getPlantTags() + player.getTags().getMicrobeTags() + player.getTags().getAnimalTags() + player.getTags().getJokerTags()));

                error_message = "requires " + amount + " more organic tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinCardsInHand() != null && player.getHandSize() < requirements.getMinCardsInHand()) {

            amount = (requirements.getMinCardsInHand() - player.getHandSize());

            error_message = "requires " + amount + " more card";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += " in hand.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinCardsOnTable() != null &&
                player.getGreen() + player.getBlue() < requirements.getMinCardsOnTable()) {

            amount = (requirements.getMinCardsOnTable() - player.getGreen() - player.getBlue());

            error_message = "requires " + amount + " more card";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += " owned by you in play.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinEventsPlayed() != null && player.getRed() < requirements.getMinEventsPlayed()) {

            amount = (requirements.getMinEventsPlayed() - player.getRed());

            error_message = "requires " + amount + " more event";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += " played by you.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinUniqueTags() != null && player.getTags().getUniqueTags() < requirements.getMinUniqueTags()) {

            amount = (requirements.getMinUniqueTags() - player.getTags().getUniqueTags());

            error_message = "requires " + amount + " more unique tag";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += ".";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMinRequirementCards() != null) {
            Integer card_amount = 0;
            for (Card card_to_check : all_cards.values()) {
                if (card_to_check.getRequirements().getDrawableRequrement() != null && card_to_check.getOwner() == player) {
                    card_amount++;
                }
            }

            if (card_amount < requirements.getMinRequirementCards()) {

                amount = (requirements.getMinRequirementCards() - card_amount);

                error_message = "requires " + amount + " more card";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += " with requirements owned by you in play.";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinPolarTiles() != null) {
            Integer tile_amount = 0;
            for (Tile tile : player.getOwnedTiles()) {
                if (tile.getY() < 2) {
                    tile_amount++;
                }
            }

            if (tile_amount < requirements.getMinPolarTiles()) {

                amount = (requirements.getMinPolarTiles() - tile_amount);

                error_message = "requires " + amount + " more tile";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += " in the polar are owned by you.";

                throw new InvalidRequirementsException(error_message);
            }
        }

        if (requirements.getMinBuildingTags() != null &&
                (!(player.getTags().getBuildingTags() + unused_jokers >= requirements.getMinBuildingTags()))) {

                amount = (requirements.getMinBuildingTags() - player.getTags().getBuildingTags() - unused_jokers);

                error_message = "requires " + amount + " more building tag";
                if (amount > 1) {
                    error_message += "s";
                }
                error_message += ".";

                throw new InvalidRequirementsException(error_message);
        }


        if (requirements.getMaxTemperature() != null && global_temperature > requirements.getMaxTemperature() + base_discount) {

            amount = -(requirements.getMaxTemperature() - global_temperature + base_discount * 2);

            error_message = "requires " + amount + " degrees lower global temperature";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMaxOceans() != null && oceans_placed > requirements.getMaxOceans() + base_discount) {

            amount = -(requirements.getMaxOceans() - oceans_placed + base_discount);

            error_message = "requires " + amount + " fewer ocean tile";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += ".";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMaxOxygen() != null && global_oxygen > requirements.getMaxOxygen() + base_discount) {

            amount = -(requirements.getMaxOxygen() - global_oxygen + base_discount);

            error_message = "requires " + amount + "% lower global oxygen level.";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMaxPersonalColonies() != null && player.getColonies() > requirements.getMaxPersonalColonies()) {

            amount = -(requirements.getMaxPersonalColonies() - player.getColonies());

            error_message = "requires " + amount + " fewer ";
            if (amount > 1) {
                error_message += "colonies";
            } else {
                error_message += "colony";
            }
            error_message += " you own in play";

            throw new InvalidRequirementsException(error_message);
        }

        if (requirements.getMaxMilestonesClaimed() != null && claimed_milestones > requirements.getMaxMilestonesClaimed()) {
            throw new InvalidRequirementsException("Maximum number of milestones claimed already.");
        }

        if (requirements.getMaxAwardsClaimed() != null && claimed_awards > requirements.getMaxAwardsClaimed()) {
            throw new InvalidRequirementsException("Maximum number of awards funded already.");
        }

        if (requirements.getMaxVenusTr() != null && venus_terraform > requirements.getMaxVenusTr() + base_discount) {

            amount = -(requirements.getMaxVenusTr() - venus_terraform + venus_discount);

            error_message = "requires " + amount + " point";
            if (amount > 1) {
                error_message += "s";
            }
            error_message += " lower venus terraforming rating.";

            throw new InvalidRequirementsException(error_message);
        }
    }

    /**
     * A method to actualise the playing of a card
     *
     * @param card {@link Card} being played
     * @param resources_to_use {@link CardCostPacket} representing the resources the player has to use for playing the card
     * @param context {@link Context} the UI context the playing action is being played from
     */
    public void playCard(Card card, CardCostPacket resources_to_use, Context context) throws InvalidResourcesException {
        Player player = GameController.getCurrentPlayer();

        if (!resources_to_use.isEligible())
        {
            return;
        }

        resources_to_use.playPacket();

        if (server_multiplayer) {
            GameActions.sendCardCost(resources_to_use);
        }

        // TODO event to handle UI better?
        // AKA: implement a way to reset into main game view after every action
        if (card.getType() != Type.OTHER) {
            EventScheduler.addEvent(new ActionUseEvent());
        }
        card.initializePlayEvents(player);
        EventScheduler.playNextEvent(context);
    }

    /**
     * A method to call when a generation ends. Modifies player resources based on their productions.
     * A part of the chain of methods responsible for changing the generation. Calls {@link GameController#syncGenerationChange(Context)}
     * as the next part of the chain.
     *
     * @param context {@link Context} the UI context the method is called from. Should be {@link com.example.terraformingmarscompanionapp.InGameUI}
     */
    void onGenerationEnd(Context context) {
        if (global_temperature >= 8 && global_oxygen >= 14 && oceans_placed >= 9) {
            Log.i("Game", "Calling GameController.gameEndPreparation");
            GameController.gameEndPreparation();
        }
        for (Player player : GameController.getPlayers()) {
            if (GameController.getGeneration() != 0) {
                player.getResources().addProduction();
            }
            player.setDrewCardsThisGen(false);
        }

        GameController.syncGenerationChange(context);
    }
}