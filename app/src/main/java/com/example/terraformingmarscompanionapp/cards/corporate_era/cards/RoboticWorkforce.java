package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;


//TODO system to play this fluidly
public final class RoboticWorkforce extends Card {
    public RoboticWorkforce(Game game) {
        super(Type.GREEN, game);
        name = "Robotic workforce";
        price = 9;
        tags.add(Tag.SCIENCE);
        requirements.setMinBuildingTags(1);
    }

    @Override
    public void onPlay(Player player, Context context) {

    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
    }
}
