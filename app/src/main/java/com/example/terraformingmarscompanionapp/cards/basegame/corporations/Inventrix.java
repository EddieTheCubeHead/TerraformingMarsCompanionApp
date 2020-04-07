package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Inventrix extends Card {
    public Inventrix(Game game) {
        name = "Inventrix";
        tags.put("science", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoney(45);
        player.addScienceTag();
        player.changeBaseTrRequirementDiscount(2);
        //TODO UI-prompt ota kolme korttia
        owner_player = player;
        player.setCorporation(this);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
