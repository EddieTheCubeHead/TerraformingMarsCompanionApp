package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GeneRepair extends Card {
    public GeneRepair(Game game) {
        super(Type.GREEN);
        name = "Gene repair";
        price = 12;
        tags.add(Tag.SCIENCE);
        requirements.setMinScienceTags(3);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
