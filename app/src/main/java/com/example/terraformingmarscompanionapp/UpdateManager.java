package com.example.terraformingmarscompanionapp;

import java.util.ArrayList;

final class UpdateManager {
    Game super_game;

    UpdateManager(Game game) {
        super_game = game;
    }

    void onVenusTrUp(Player player) {

    }

    void onCityPlaced(Player player, Boolean onMars) {
        super_game.getDeck().get("Immigrant city").cardEffect(player);
    }

    void onNewUniqueTag(Player player) {

    }

    void onTagPlayedGrassAnimal(Player player) {

    }

    void onNewColony(Player player) {

    }

    void onProductionRaised(Player player, Integer type) {
        /* 0: raha
         * 1: teräs
         * 2: titaani
         * 3: kasvi
         * 4: energia
         * 5: lämpö
         */
    }

    void onOceanPlaced(Player player) {

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

    void onVpCardPlayed(Player player) {

    }

    void onEventPlayed(Player player) {

    }

    void onOrganicTag(Player player) {

    }

    void onStandardProjectPayment(Player player) {

    }

    void onGreeneryPlaced(Player player) {

    }

    void onSpaceEvent(Player player) {

    }
}