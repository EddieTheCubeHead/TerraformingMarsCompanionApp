package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Insects extends Card {
    public Insects(Game game) {
        super(Type.GREEN);
        name = "Insects";
        price = 9;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(6);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlantsProduction(player.getPlantTags());
        return super.onPlay(player);
    }
}
