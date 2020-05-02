package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GreatEscarpmentConsortium extends Card {
    public GreatEscarpmentConsortium(Game game) {
        super(Type.GREEN);
        name = "Great escarpment consortium";
        price = 6;
        requirements.setMinSteelProduction(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSteelProduction(1);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO poista toiselta steel production
        super.playWithMetadata(player, data);
    }
}
