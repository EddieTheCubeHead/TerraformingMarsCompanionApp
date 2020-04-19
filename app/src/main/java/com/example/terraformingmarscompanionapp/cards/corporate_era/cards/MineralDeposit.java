package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MineralDeposit extends Card {
    public MineralDeposit(Game game) {
        super(Type.RED);
        name = "Mineral deposit";
        price = 5;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_player.changeSteel(5);
        return super.onPlay(player);
    }
}
