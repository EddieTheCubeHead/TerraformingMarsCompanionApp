package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Helion extends Card {
    public Helion(Game game) {
        super(Type.CORPORATION);
        name = "Helion";
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoney(42);
        player.changeHeatProduction(3);
        player.setHeatIsMoney(true);
        return super.onPlay(player);
    }
}
