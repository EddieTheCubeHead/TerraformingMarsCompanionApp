package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class RoboticWorkforce extends Card {
    public RoboticWorkforce(Game game) {
        super(Type.GREEN);
        name = "Robotic workforce";
        price = 9;
        tags.add(Tag.SCIENCE);
        requirements.setMinBuildingTags(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {

        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Building kortin production boxin kopiointi
        super.playWithMetadata(player, data);
    }
}