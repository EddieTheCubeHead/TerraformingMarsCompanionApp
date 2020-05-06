package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

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
        //TODO korttiresurssi UI
        actionServerHook(owner_player);
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
        GameController.getInstance().useAction();
    }
}
