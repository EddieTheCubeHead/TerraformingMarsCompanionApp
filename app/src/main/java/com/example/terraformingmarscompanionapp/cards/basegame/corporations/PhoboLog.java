package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PhoboLog extends Card {
    public PhoboLog(Game game) {
        super("corporation");
        name = "PhoboLog";
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeTitaniumValueModifier(1);
        player.changeMoney(23);
        player.changeTitanium(10);
        super.onPlay(player);
    }
}
