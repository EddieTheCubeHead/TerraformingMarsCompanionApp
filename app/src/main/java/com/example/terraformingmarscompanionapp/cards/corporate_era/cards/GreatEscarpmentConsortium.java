package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GreatEscarpmentConsortium extends Card {
    public GreatEscarpmentConsortium(Game game) {
        super(Type.GREEN, game);
        name = "Great escarpment consortium";
        price = 6;
        requirements.setMinSteelProduction(1);
    }

    @Override
    public void onPlay(Player player) {
        //TODO pelaajan valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takeSteelProduction(1);
        }
        player.changeSteelProduction(1);
        super.playWithMetadata(player, data);
    }
}
