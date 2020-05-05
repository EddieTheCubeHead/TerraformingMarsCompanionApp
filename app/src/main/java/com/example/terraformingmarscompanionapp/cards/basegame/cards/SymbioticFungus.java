package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class SymbioticFungus extends Card implements MetadataAction {
    public SymbioticFungus(Game game) {
        super(Type.BLUE, game);
        name = "Symbiotic fungus";
        price = 4;
        tags.add(Tag.MICROBE);
        requirements.setMinTemperature(-14);
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        }
        //TODO lisää toiminto jahka korttiresurssijärjestelmä implementoitu
        action_used = true;
        return 0;
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
