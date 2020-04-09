package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class AdvancedEcosystems extends Card {
    public AdvancedEcosystems(Game game) {
        super("green");
        name = "Advanced ecosystems";
        price = 11;
        tags.add("plant");
        tags.add("microbe");
        tags.add("animal");
        requirements.put("min_plant_tags", 1);
        requirements.put("min_microbe_tags", 1);
        requirements.put("min_animal_tags", 1);
        victory_points = 3;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
