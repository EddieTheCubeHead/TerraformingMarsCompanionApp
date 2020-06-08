package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class Predators extends ResourceCard implements ActionCard {
    public Predators(Game game) {
        super(Type.BLUE, game);
        name = "Predators";
        price = 14;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(11);
        resource_type = ResourceType.ANIMAL;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    public void cardAction() {
        defaultEvents(owner_player);
        EventScheduler.addEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, owner_player, 1));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
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
    public void actionWithMetadata(Integer data) {
        resource_amount++;
        EventScheduler.playNextEvent(GameController.getContext());
    }
}
