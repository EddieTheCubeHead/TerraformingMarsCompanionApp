package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.CardCostEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class WaterImportFromEurope extends Card implements ActionCard {
    public WaterImportFromEurope() {
        super(Type.BLUE);
        name = "Water import from europe";
        price = 25;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, game));
        EventScheduler.addEvent(new CardCostEvent(game.getDeck().get("Water import from europe ghost")));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public void onGameEnd() {
        victory_points = owner_player.getTags().getJovianTags();
        super.onGameEnd();
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || ((owner_player.getResources().getMoney() + owner_player.getResources().getTitanium() * (3 + owner_player.getModifiers().getTitaniumValueModifier())) < 12));
    }

    @Override
    public void actionWithMetadata(Integer data) {EventScheduler.playNextEvent(GameController.getContext());}
}
