package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TectonicStressPower extends Card {
    public TectonicStressPower(Game game) {
        super(Type.GREEN);
        name = "Tectonic stress power";
        price = 18;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinScienceTags(2);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeEnergyProduction(3);
        return super.onPlay(player);
    }
}
