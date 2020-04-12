package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Helion extends Card {
    public Helion(Game game) {
        super("corporation");
        name = "Helion";
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoney(42);
        player.changeHeatProduction(3);
        player.setHeatIsMoney(true);
        super.onPlay(player);
    }
}
