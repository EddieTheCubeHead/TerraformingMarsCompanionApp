package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ImportOfAdvancedGHG extends Card {
    public ImportOfAdvancedGHG(Game game) {
        super(Type.RED);
        name = "Import of advanced GHG";
        price = 9;
        tags.add(Tag.SPACE);
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(2);
        super.onPlay(player);
    }
}
