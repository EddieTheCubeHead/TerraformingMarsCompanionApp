package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Inventrix extends Card {
    public Inventrix(Game game) {
        super("corporation");
        name = "Inventrix";
        tags.add("science");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoney(45);
        player.changeBaseTrRequirementDiscount(2);
        //TODO UI-prompt ota kolme korttia
        super.onPlay(player);
    }
}
