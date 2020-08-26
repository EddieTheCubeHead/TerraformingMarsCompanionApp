package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MineralDeposit extends Card {
    public MineralDeposit() {
        super(Type.RED);
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
