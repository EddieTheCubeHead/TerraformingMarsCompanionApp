package com.example.terraformingmarscompanionapp.game;

import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;

import java.util.HashMap;

@SuppressWarnings("EmptyMethod")
public final class UpdateManager {
    private final HashMap<String, EffectCard> effect_cards;
    private final Game owner_game;
    private final Boolean corporate_era;
    private final Boolean prelude;
    private final Boolean colonies;
    private final Boolean venus;
    private final Boolean turmoil;

    UpdateManager(Game game, boolean game_corporate_era, boolean game_prelude, boolean game_colonies, boolean game_venus, boolean game_turmoil) {
        effect_cards = game.getEffectCards();
        owner_game = game;
        corporate_era = game_corporate_era;
        prelude = game_prelude;
        colonies = game_colonies;
        venus = game_venus;
        turmoil = game_turmoil;
    }

    void onVenusTrUp(Player player) {

    }

    void onCityPlaced(Player player, Boolean onMars) {
        if (onMars) {
            owner_game.addCityOnMars();
            effect_cards.get("Tharsis republic ghost").cardEffect(player);
        } else {
            owner_game.addCityInSpace();
        }
        effect_cards.get("Tharsis republic").cardEffect(player);
        effect_cards.get("Immigrant city").cardEffect(player);
        effect_cards.get("Rover construction").cardEffect(player);
    }

    void onNewUniqueTag(Player player) {

    }

    public void onPlantTag(Player player) {
        effect_cards.get("Ecological zone").cardEffect(player);
    }

    public void onAnimalTag(Player player) {
        effect_cards.get("Ecological zone").cardEffect(player);
    }

    public void onMicrobeTag(Player player) {

    }

    void onNewColony(Player player) {

    }

    void onMoneyProductionRaised(Player player, Integer amount) {

    }

    void onSteelProductionRaised(Player player, Integer amount) {

    }

    void onTitaniumProductionRaised(Player player, Integer amount) {

    }

    void onPlantsProductionRaised(Player player, Integer amount) {

    }

    void onEnergyProductionRaised(Player player, Integer amount) {

    }

    void onHeatProductionRaised(Player player, Integer amount) {

    }

    void onOceanPlaced(Player player) {
        effect_cards.get("Arctic algae").cardEffect(player);
    }

    public void onJovianTag(Player player) {

    }

    public void onEarthTag(Player player) {

    }

    public void onScienceTag(Player player) {

    }

    void onBaseCost20(Player player, Boolean standard_project) {
        effect_cards.get("Credicor").cardEffect(player);
    }

    void onPlacementBonus(Player player) {
        effect_cards.get("Mining guild").cardEffect(player);
    }

    //Tämä on simppelimpää kutsua korteista, jotka sijaitsevat eri packagessa, siksi public
    public void onVpCardPlayed(Player player) {

    }

    public void onEventPlayed(Player player) {
        effect_cards.get("Interplanetary cinematics").cardEffect(player);
    }

    void onStandardProjectPayment(Player player) {

    }

    void onGreeneryPlaced(Player player) {
        effect_cards.get("Herbivores").cardEffect(player);
    }

    //Tämä on simppelimpää kutsua korteista, jotka sijaitsevat eri packagessa, siksi public
    public void onSpaceEvent(Player player) {
        effect_cards.get("Optimal aerobraking").cardEffect(player);
    }
}