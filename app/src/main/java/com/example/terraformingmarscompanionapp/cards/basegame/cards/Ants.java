package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;

public final class Ants extends ResourceCard implements ActionCard {
    public Ants(Game game) {
        super(Type.BLUE, game);
        name = "Ants";
        price = 9;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(4);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.addEvent(new ResourceChoiceEvent(ResourceType.MICROBE, owner_player, -1));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public void actionWithMetadata(Integer data) {
        resource_amount++;
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void onGameEnd() {
        victory_points = resource_amount / 2;
        super.onGameEnd();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void setActionToUsed() {action_used = true;}
}
