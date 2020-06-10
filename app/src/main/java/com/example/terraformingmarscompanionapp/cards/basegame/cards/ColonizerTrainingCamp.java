package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ColonizerTrainingCamp extends Card {
    public ColonizerTrainingCamp(Game game) {
        super(Type.GREEN, game);
        name = "Colonizer training camp";
        price = 8;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(5);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
