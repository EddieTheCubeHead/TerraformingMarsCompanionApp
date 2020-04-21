package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class SymbioticFungus extends Card implements MetadataAction {
    public SymbioticFungus(Game game) {
        super(Type.BLUE);
        name = "Symbiotic fungus";
        price = 4;
        tags.add(Tag.MICROBE);
        requirements.setMinTemperature(-14);
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

    @Override
    public boolean actionWithMetadata(Integer data) {
        return true;
    }
}
