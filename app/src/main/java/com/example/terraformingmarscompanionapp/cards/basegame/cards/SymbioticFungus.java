package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class SymbioticFungus extends Card implements ActionCard {
    public SymbioticFungus(Game game) {
        super(Type.BLUE, game);
        name = "Symbiotic fungus";
        price = 4;
        tags.add(Tag.MICROBE);
        requirements.setMinTemperature(-14);
    }

    @Override
    public void cardAction() {
        //TODO korttiresurssi UI
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void actionWithMetadata(Integer data) {}
}
