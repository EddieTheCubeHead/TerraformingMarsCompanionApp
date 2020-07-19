package com.example.terraformingmarscompanionapp.game;

import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.player.Player;

import java.util.HashMap;

/**
 * A class to handle triggerable effects in cards. Different parts of code can call this class when
 * they handle logic concerning an event and this class will call the effect of all {@link EffectCard}
 * instances that should get triggered.
 * <p></p>
 * All effect triggers take {@link Player} as a parameter and use it to run {@link EffectCard#cardEffect(Player)}
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
@SuppressWarnings("EmptyMethod")
public final class UpdateManager {
    private final HashMap<String, EffectCard> effect_cards;
    private final Game owner_game;

    /**
     * Constructor
     * 
     * @param game {@link Game} this class is associated with
     */
    UpdateManager(Game game) {
        effect_cards = game.getEffectCards();
        owner_game = game;
    }

    /**
     * A trigger for raising the venus terraforming rating slider
     * 
     * @param player {@link Player} raising the slider
     */
    void onVenusTrUp(Player player) {

    }

    /**
     * A trigger for placing a city.
     *
     * @param player {@link Player} placing the city
     * @param onMars {@link Boolean} whether the city was placed on mars or not
     */
    public void onCityPlaced(Player player, Boolean onMars) {
        if (onMars) {
            owner_game.addCityOnMars();
            effect_cards.get("Tharsis republic ghost").cardEffect(player);
        } else {
            owner_game.addCityInSpace();
        }
        effect_cards.get("Tharsis republic").cardEffect(player);
        effect_cards.get("Immigrant city").cardEffect(player);
        effect_cards.get("Rover construction").cardEffect(player);
        effect_cards.get("Pets").cardEffect(player);
    }

    /**
     * A trigger for a player playing a card that has a tag that player does not have in their cards
     * yet
     *
     * @param player {@link Player} playing the tag
     */
    public void onNewUniqueTag(Player player) {

    }

    /**
     * A trigger for a plant tag being played
     *
     * @param player {@link Player} playing the tag
     */
    public void onPlantTag(Player player) {
        effect_cards.get("Ecological zone").cardEffect(player);
    }

    /**
     * A trigger for an animal tag being played
     *
     * @param player {@link Player} playing the tag
     */
    public void onAnimalTag(Player player) {
        effect_cards.get("Ecological zone").cardEffect(player);
    }

    /**
     * A trigger for a microbe tag being played
     *
     * @param player {@link Player} playing the tag
     */
    public void onMicrobeTag(Player player) {

    }

    /**
     * A trigger for a new colony being placed
     *
     * @param player {@link Player} placing the colony
     */
    void onNewColony(Player player) {

    }

    /**
     * A trigger for an ocean being placed
     *
     * @param player {@link Player} placing the ocean. Might be null in case of solar phase
     */
    public void onOceanPlaced(Player player) {
        effect_cards.get("Arctic algae").cardEffect(player);
    }

    /**
     * A trigger for a jovian tag being played
     *
     * @param player {@link Player} playing the tag
     */
    public void onJovianTag(Player player) {
        if (owner_game.modifiers.getCorporateEra()) {
            effect_cards.get("Saturn systems").cardEffect(player);
        }
    }

    /**
     * A trigger for an earth tag being played
     *
     * @param player {@link Player} playing the tag
     */
    public void onEarthTag(Player player) {

    }

    /**
     * A trigger for a scieance tag being played
     *
     * @param player {@link Player} playing the tag
     */
    public void onScienceTag(Player player) {

    }

    /**
     * A trigger for a card with a base cost of 20 or more being played
     *
     * @param player {@link Player} playing the card
     * @param standard_project {@link Boolean} whether the card was a standard project
     */
    void onBaseCost20(Player player, Boolean standard_project) {
        effect_cards.get("Credicor").cardEffect(player);
    }

    /**
     * A trigger for gaining steel or titanium as a placement bonus
     *
     * @param player {@link Player} gaining the placement bonus
     */
    public void onPlacementBonus(Player player) {
        effect_cards.get("Mining guild").cardEffect(player);
    }

    /**
     * A trigger for playing a card that has a non-negative VP value (includes gaining VP from resources etc.)
     *
     * @param player
     */
    public void onVpCardPlayed(Player player) {

    }

    /**
     * A trigger for playing an event card
     *
     * @param player {@link Player} playing the card
     */
    public void onEventPlayed(Player player) {
        effect_cards.get("Interplanetary cinematics").cardEffect(player);
    }

    /**
     * A trigger for a standard project with a cost (ie. not sell patents) being played
     *
     * @param player {@link Player} playing the project
     */
    public void onStandardProjectPayment(Player player) {
        if (owner_game.modifiers.getCorporateEra()) {
            effect_cards.get("Standard technology").cardEffect(player);
        }
    }

    /**
     * A trigger for a greenery getting placed
     *
     * @param player {@link Player} placing the greenery
     */
    public void onGreeneryPlaced(Player player) {
        effect_cards.get("Herbivores").cardEffect(player);
    }

    /**
     * A trigger for an event with a space tag getting played
     *
     * @param player {@link Player} plaing the event
     */
    public void onSpaceEvent(Player player) {
        effect_cards.get("Optimal aerobraking").cardEffect(player);
    }
}