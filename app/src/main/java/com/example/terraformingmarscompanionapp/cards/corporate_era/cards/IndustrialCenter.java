package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class IndustrialCenter extends Card implements ActionCard {
    public IndustrialCenter() {
        super(Type.BLUE);
        name = "Industrial center";
        price = 4;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.INDUSTRIAL_CENTER, game));
    }

    @Override
    public void cardAction() throws InvalidResourcesException {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) throws InvalidResourcesException {
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() - 7);
        owner_player.getResources().setSteelProduction(owner_player.getResources().getSteelProduction() + 1);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return !action_used && owner_player.getResources().getMoney() >= 7;
    }

    @Override
    public void setActionToUsed() {

    }
}
