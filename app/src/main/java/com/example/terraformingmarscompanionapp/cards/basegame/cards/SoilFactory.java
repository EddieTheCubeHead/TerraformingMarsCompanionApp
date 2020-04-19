package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SoilFactory extends Card {
    public SoilFactory(Game game) {
        super(Type.GREEN);
        name = "Soil factory";
        price = 9;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(-1);
        player.changePlantsProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
