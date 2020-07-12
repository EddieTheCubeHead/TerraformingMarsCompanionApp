package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.CardCostEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class AquiferPumping extends Card implements ActionCard {
    public AquiferPumping(Game game) {
        super(Type.BLUE, game);
        name = "Aquifer pumping";
        price = 18;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, owner_game));
        EventScheduler.addEvent(new CardCostEvent(owner_game.getDeck().get("Aquifer pumping ghost")));
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
