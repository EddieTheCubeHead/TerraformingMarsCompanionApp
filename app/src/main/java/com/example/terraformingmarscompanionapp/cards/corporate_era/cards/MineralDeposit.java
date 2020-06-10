package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MineralDeposit extends Card {
    public MineralDeposit(Game game) {
        super(Type.RED, game);
        name = "Mineral deposit";
        price = 5;
        tags.add(Tag.EVENT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setSteel(player.getResources().getSteel() + 5);
        super.playWithMetadata(player, data);
    }
}
