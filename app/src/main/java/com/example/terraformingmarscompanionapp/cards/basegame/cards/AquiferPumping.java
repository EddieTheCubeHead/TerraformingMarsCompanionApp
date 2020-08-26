package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.CardCostEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class AquiferPumping extends Card implements ActionCard {
    public AquiferPumping() {
        super(Type.BLUE);
        name = "Aquifer pumping";
        price = 18;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, game));
        EventScheduler.addEvent(new CardCostEvent(game.getDeck().get("Aquifer pumping ghost")));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {EventScheduler.playNextEvent(GameController.getContext());}

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || (owner_player.getResources().getMoney() + owner_player.getResources().getSteel() * (2 + owner_player.getModifiers().getSteelValueModifier()) < 8));
    }

    @Override
    public void setActionToUsed() {action_used = true;}
}
