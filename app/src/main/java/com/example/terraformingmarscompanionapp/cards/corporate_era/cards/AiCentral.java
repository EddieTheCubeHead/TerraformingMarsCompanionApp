package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class AiCentral extends Card implements ActionCard {
    public AiCentral(Game game) {
        super(Type.BLUE, game);
        name = "AI centeral";
        price = 21;
        requirements.setMinScienceTags(3);
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeEnergyProduction(-1);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        defaultEvents(owner_player);
        EventScheduler.addEvent(new PromptEvent("Please draw 2 cards"));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeHandSize(2);
        EventScheduler.playNextEvent(GameController.getContext());
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
