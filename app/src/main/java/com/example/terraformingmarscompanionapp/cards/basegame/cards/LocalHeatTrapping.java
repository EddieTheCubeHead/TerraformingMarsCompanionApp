package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping(Game game) {
        super(Type.RED, game);
        name = "Local heat trapping";
        price = 1;
        tags.add(Tag.EVENT);
        requirements.setMinHeat(5);
    }

    @Override
    public void onPlay(Player player) {
        //TODO boolean valinta UI
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (data != 0) {
            //TODO korttiresurssi UI
        }
        super.playServerConnection(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHeat(-5);
        if (data == 0) {
            player.changePlants(4);
        }
        super.playWithMetadata(player, data);
    }
}
