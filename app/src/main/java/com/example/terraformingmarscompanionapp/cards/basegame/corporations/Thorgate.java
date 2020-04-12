package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Thorgate extends Card {
    public Thorgate(Game game) {
        super("corporation");
        name = "Thorgate";
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoney(48);
        player.changeEnergyProduction(1);
        player.changeEnergyTagDiscount(3);
        super.onPlay(player);
    }
}
