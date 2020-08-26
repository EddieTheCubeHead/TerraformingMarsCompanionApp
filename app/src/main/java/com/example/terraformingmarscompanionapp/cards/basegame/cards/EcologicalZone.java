package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class EcologicalZone extends ResourceCard implements EffectCard {
    public EcologicalZone() {
        super(Type.GREEN);
        name = "Ecological zone";
        price = 12;
        tags.add(Tag.ANIMAL);
        tags.add(Tag.PLANT);
        requirements.setMinPersonalGreeneries(1);
        resource_type = ResourceType.ANIMAL;
        Card.game = game;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.ECOLOGICAL_ZONE, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null && owner_player == player) {
            resource_amount++;
        }
    }

    @Override
    public void onGameEnd() {
        victory_points = resource_amount/2;
        super.onGameEnd();
    }
}
