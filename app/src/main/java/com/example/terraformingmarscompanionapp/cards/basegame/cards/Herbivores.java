package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class Herbivores extends ResourceCard implements EffectCard {
    public Herbivores() {
        super(Type.BLUE);
        name = "Herbivores";
        price = 12;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(8);
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
        resource_amount++;
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player != player) {
            return;
        }
        resource_amount++;
    }

    @Override
    public void onGameEnd() {
        victory_points = resource_amount / 2;
        super.onGameEnd();
    }
}
