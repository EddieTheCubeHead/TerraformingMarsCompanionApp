package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class Fish extends ResourceCard implements ActionCard {
    public Fish() {
        super(Type.BLUE);
        name = "Fish";
        price = 9;
        tags.add(Tag.ANIMAL);
        requirements.setMinTemperature(2);
        resource_type = ResourceType.ANIMAL;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setStealPlantsProduction(1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public void onGameEnd() {
        victory_points = resource_amount;
        super.onGameEnd();
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

    @Override
    public void setActionToUsed() {
        action_used = true;
    }
}
