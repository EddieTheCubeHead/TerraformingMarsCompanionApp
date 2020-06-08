package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class AdvancedEcosystems extends Card {
    public AdvancedEcosystems(Game game) {
        super(Type.GREEN, game);
        name = "Advanced ecosystems";
        price = 11;
        tags.add(Tag.PLANT);
        tags.add(Tag.MICROBE);
        tags.add(Tag.ANIMAL);
        requirements.setMinPlantTags(1);
        requirements.setMinMicrobeTags(1);
        requirements.setMinAnimalTags(1);
        victory_points = 3;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
