package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AdvancedEcosystems extends Card {
    public AdvancedEcosystems(Game game) {
        super(Type.GREEN);
        name = "Advanced ecosystems";
        price = 11;
        tags.add(Tag.PLANT);
        tags.add(Tag.MICROBE);
        tags.add(Tag.ANIMAL);
        requirements.setMinPlantTags(1);
        requirements.setMinMicrobeTags(1);
        requirements.setMinAnimalTags(1);
        victory_points = 3;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
