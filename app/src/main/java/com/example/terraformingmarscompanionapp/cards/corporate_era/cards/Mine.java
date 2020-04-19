package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Mine extends Card {
    public Mine(Game game) {
        super(Type.GREEN);
        name = "Mine";
        price = 4;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSteelProduction(1);
        return super.onPlay(player);
    }
}
