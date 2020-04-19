package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MethaneFromTitan extends Card {
    public MethaneFromTitan(Game game) {
        super(Type.GREEN);
        name = "Methane from titan";
        price = 10;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        requirements.setMinOxygen(2);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeHeatProduction(2);
        player.changePlantsProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
