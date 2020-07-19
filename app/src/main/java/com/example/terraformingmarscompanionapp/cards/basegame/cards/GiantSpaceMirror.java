package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class GiantSpaceMirror extends Card {
    public GiantSpaceMirror(Game game) {
        super(Type.GREEN, game);
        name = "Giant space mirror";
        price = 17;
        tags.add(Tag.ENERGY);
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(3);
        super.playWithMetadata(player, data);
    }
}
