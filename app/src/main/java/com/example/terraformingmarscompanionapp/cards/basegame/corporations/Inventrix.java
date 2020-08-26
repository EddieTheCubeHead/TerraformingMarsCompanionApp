package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.FirstAction;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class Inventrix extends Card implements FirstAction {
    private Boolean first_action_used = false;

    public Inventrix() {
        super(Type.CORPORATION);
        name = "Inventrix";
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(45);
        player.getModifiers().setBaseTrRequirementDiscount(2);

        player.changeHandSize(3);
        super.playWithMetadata(player, data);
    }

    @Override
    public void firstAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new PromptEvent("Please draw 3 cards"));
        owner_player.changeHandSize(3);
        EventScheduler.playNextEvent(GameController.getContext());
        first_action_used = true;
    }

    @Override
    public Boolean firstActionUsed() {
        return first_action_used;
    }
}
