package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis(Game game) {
        super(Type.GREEN, game);
        name = "Artificial photosynthesis";
        price = 12;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void onPlay(Player player) {
        //TODO linkki päätös-UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.changePlantsProduction(1);
        } else {
            player.changeEnergyProduction(2);
        }
        super.playWithMetadata(player, data);
    }
}
