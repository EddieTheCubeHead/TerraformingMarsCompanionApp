package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Inventrix extends Card {
    public Inventrix(Game game) {
        super(Type.CORPORATION);
        name = "Inventrix";
        tags.add(Tag.SCIENCE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoney(45);
        player.changeBaseTrRequirementDiscount(2);
        //TODO UI-prompt ota kolme korttia
        player.changeHandSize(3);
        return super.onPlay(player);
    }
}
