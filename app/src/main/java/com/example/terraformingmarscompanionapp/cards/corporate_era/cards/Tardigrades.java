package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Tardigrades extends ResourceCard implements ActionCard {
    public Tardigrades(Game game) {
        super(Type.BLUE, game);
        name = "Tardigrades";
        price = 4;
        tags.add(Tag.MICROBE);
        owner_game = game;
        resource_type = ResourceCard.ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        if (owner_player == null) {
            return;
        }
        owner_player.changeVictoryPoints(resource_amount/4);
        super.onGameEnd();
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            resource_amount++;
            action_used = true;
            return 0;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
