package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Tardigrades extends ResourceCard implements ActionCard {
    public Tardigrades() {
        super(Type.BLUE);
        name = "Tardigrades";
        price = 4;
        tags.add(Tag.MICROBE);
        resource_type = ResourceCard.ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        victory_points = resource_amount / 4;
        super.onGameEnd();
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        resource_amount++;
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
}
