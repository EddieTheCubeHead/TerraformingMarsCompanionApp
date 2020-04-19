package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BeamFromAThoriumAsteroid extends Card {
    public BeamFromAThoriumAsteroid(Game game) {
        super(Type.GREEN);
        name = "Beam from a thorium asteroid";
        price = 32;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        tags.add(Tag.ENERGY);
        requirements.setMinJovianTags(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeHeatProduction(3);
        player.changeEnergyProduction(3);
        return super.onPlay(player);
    }
}
