package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Insulation extends Card {
    public Insulation(Game game) {
        super(Type.GREEN, game);
        name = "Insulation";
        price = 2;
    }

    @Override
    public void onPlay(Player player) {
        //TODO luvun valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(-data);
        production_box.setMoneyProduction(data);
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        //TODO luvun valinta UI
    }
}
