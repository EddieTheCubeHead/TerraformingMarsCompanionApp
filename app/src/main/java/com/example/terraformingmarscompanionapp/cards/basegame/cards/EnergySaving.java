package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EnergySaving extends Card {
    public EnergySaving(Game game) {
        super(Type.GREEN);
        name = "Energy saving";
        price = 15;
        tags.add(Tag.ENERGY);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace());
        return super.onPlay(player);
    }
}
