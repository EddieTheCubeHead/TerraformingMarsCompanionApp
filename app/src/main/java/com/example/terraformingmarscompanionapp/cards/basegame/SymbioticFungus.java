package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SymbioticFungus extends Card implements ActionCard {
    public SymbioticFungus(Game game) {
        super("blue");
        name = "Symbiotic fungus";
        price = 4;
        tags.add("microbe");
        requirements.put("min_temperature", -14);
    }

    @Override
    public void onPlay(Player player) {
        player.addMicrobeTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public boolean cardAction() {
        //TODO lisää toiminto jahka korttiresurssijärjestelmä implementoitu
        return true;
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
