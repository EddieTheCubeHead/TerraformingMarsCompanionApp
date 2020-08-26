package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class UnitedNationsMarsInitiative extends Card implements ActionCard {
    public UnitedNationsMarsInitiative() {
        super(Type.CORPORATION);
        name = "UNMI";
        tags.add(Tag.EARTH);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(40);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() - 3);
        owner_player.getResources().setTerraformingRating(owner_player.getResources().getTerraformingRating() + 1);
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
        return (action_used || !owner_player.getModifiers().getRaisedTrThisGeneration() || owner_player.getResources().getMoney() < 3);
    }
}
