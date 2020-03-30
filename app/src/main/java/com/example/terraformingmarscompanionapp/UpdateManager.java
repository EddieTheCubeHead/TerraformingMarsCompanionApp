package com.example.terraformingmarscompanionapp;

import com.example.terraformingmarscompanionapp.cards.basegame.Plantation;

import java.util.ArrayList;
import java.util.HashMap;

public final class UpdateManager {
    private HashMap<String, Card> game_deck;
    private Game owner_game;

    UpdateManager(Game game) {
        game_deck = game.getDeck();
        owner_game = game;
    }

    void onVenusTrUp(Player player) {

    }

    void onCityPlaced(Player player, Boolean onMars) {
        if (onMars) {
            owner_game.addCityOnMars();
        } else {
            owner_game.addCityInSpace();
        }
        game_deck.get("Immigrant city").cardEffect(player);
        game_deck.get("Rover construction").cardEffect(player);
    }

    void onNewUniqueTag(Player player) {

    }

    void onPlantTag(Player player) {
        game_deck.get("Ecological zone").cardEffect(player);
    }

    void onAnimalTag(Player player) {
        game_deck.get("Ecological zone").cardEffect(player);
    }

    void onMicrobeTag(Player player) {

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
        game_deck.get("Arctic algae").cardEffect(player);
    }

    void onJovianTag(Player player) {

    }

    void onEarthTag(Player player) {

    }

    void onScienceTag(Player player) {

    }

    void onBaseCost20(Player player, Boolean standard_project) {

    }

    void onPlacementBonus(Player player, Integer type) {
        /* 0: teräs
         * 1: titaani
         */
    }

    //Tämä on simppelimpää kutsua korteista, jotka sijaitsevat eri packagessa, siksi public
    public void onVpCardPlayed(Player player) {

    }

    void onEventPlayed(Player player) {

    }

    void onOrganicTag(Player player) {

    }

    void onStandardProjectPayment(Player player) {

    }

    void onGreeneryPlaced(Player player) {
        game_deck.get("Herbivores").cardEffect(player);
    }

    //Tämä on simppelimpää kutsua korteista, jotka sijaitsevat eri packagessa, siksi public
    public void onSpaceEvent(Player player) {
        game_deck.get("Optimal aerobraking").cardEffect(player);
    }
}