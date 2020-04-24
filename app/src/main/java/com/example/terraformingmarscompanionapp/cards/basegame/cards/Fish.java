package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Fish extends ResourceCard implements ActionCard {
    public Fish(Game game) {
        super(Type.BLUE);
        name = "Fish";
        price = 9;
        tags.add(Tag.ANIMAL);
        requirements.setMinTemperature(2);
        resource_type = ResourceType.ANIMAL;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        Integer player_to_take_from = 0;

        //TODO UI kysy keneltä otetaan
        //Tämän voi kutsua suoraan UI:sta
        playWithMetadata(player, player_to_take_from);

        return player_to_take_from;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlantsProduction(1);
        }
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
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
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
