package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class AdvancedEcosystems extends Card {
    public AdvancedEcosystems(Game game) {
        name = "Advanced ecosystems";
        price = 11;
        tags.put("plant", 1);
        tags.put("microbe", 1);
        tags.put("animal", 1);
        requirements.put("min_plant_tags", 1);
        requirements.put("min_microbe_tags", 1);
        requirements.put("min_animal_tags", 1);
        victory_points = 3;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.addMicrobeTag();
        player.addAnimalTag();
        owner_game.updateManager.onVpCardPlayed(player);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
