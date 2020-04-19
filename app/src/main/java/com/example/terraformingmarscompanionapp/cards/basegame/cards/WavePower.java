package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class WavePower extends Card {
    public WavePower(Game game) {
        super(Type.GREEN);
        name = "Wave power";
        price = 8;
        tags.add(Tag.ENERGY);
        requirements.setMinOceans(3);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
