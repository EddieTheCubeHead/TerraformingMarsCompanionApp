package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class KelpFarming extends Card {
    public KelpFarming(Game game) {
        super(Type.GREEN);
        name = "Kelp farming";
        price = 17;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(6);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoneyProduction(2);
        player.changePlantsProduction(3);
        player.changePlants(2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
