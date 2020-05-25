package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

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
        defaultEvents(owner_player);
        EventScheduler.addEvent(new ResourceEvent(ResourceCard.ResourceType.MICROBE, owner_player, 1, true));
        EventScheduler.playNextEvent(GameController.getContext());
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
