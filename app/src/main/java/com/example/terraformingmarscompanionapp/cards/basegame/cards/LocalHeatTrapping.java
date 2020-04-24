package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping(Game game) {
        super(Type.RED);
        name = "Local heat trapping";
        price = 1;
        tags.add(Tag.EVENT);
        requirements.setMinHeat(5);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeHeat(-5);
        boolean added_animals = true;
        //TODO UI kysy kasvien vai eläinten lisäys, jos eläimet, minne?
        playWithMetadata(player, added_animals ? 1 : 0);
        return added_animals ? 1 : 0;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHeat(-5);
        if (data == 0) {
            player.changePlants(4);
        }
        super.onPlay(player);
    }
}
