package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class MarsUniverse extends Card implements EffectCard {
    public MarsUniverse() {
        super(Type.BLUE);
        name = "Mars universe";
        price = 8;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }

        if (game.getServerMultiplayer()) {
            if (owner_player != GameController.getSelfPlayer()) {
                return;
            }
        }

        EventScheduler.addEvent(new PromptEvent("Please swap a card"));
        EventScheduler.playNextEvent(GameController.getContext());
    }
}
