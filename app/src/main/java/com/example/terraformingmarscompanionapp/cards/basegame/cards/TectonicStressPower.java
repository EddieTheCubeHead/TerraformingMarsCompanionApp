package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TectonicStressPower extends Card {
    public TectonicStressPower(Game game) {
        super("green");
        name = "Tectonic stress power";
        price = 18;
        tags.add("energy");
        tags.add("building");
        requirements.setMinScienceTags(2);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeEnergyProduction(3);
        super.onPlay(player);
    }
}
