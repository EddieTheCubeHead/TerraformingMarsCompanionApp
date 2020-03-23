package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SymbioticFungus extends Card {
    public SymbioticFungus(Game game) {
        name = "Symbiotic fungus";
        price = 4;
        tags.put("microbe", 1);
        requirements.put("min_temperature", -14);
    }

    @Override
    public void onPlay(Player player) {
        player.addMicrobeTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        //TODO lisää toiminto jahka korttiresurssijärjestelmä implementoitu
        return true;
    }
}
