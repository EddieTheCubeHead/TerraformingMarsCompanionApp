package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

public final class ExtremeColdFungus extends Card implements ActionCard {
    public ExtremeColdFungus(Game game) {
        super(Type.BLUE, game);
        name = "Extreme-cold fungus";
        price = 13;
        tags.add(Tag.MICROBE);
        requirements.setMaxTemperature(-10);
    }

    @Override
    public void cardAction() {
        //TODO boolean valinta UI
    }

    @Override
    public void actionServerHook(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), 0));
        }
        setActionToUsed();
        if (data == 0) {
            //TODO korttiresurssi UI
        }
        actionWithMetadata(data);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data != 0) {
            owner_player.changePlants(1);
        }
        GameController.getInstance().useAction();
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
    public void setActionToUsed() {
        action_used = true;
    }
}
