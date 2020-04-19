package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Worms extends Card {
    public Worms(Game game) {
        super(Type.GREEN);
        name = "Worms";
        price = 8;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlantsProduction(player.getMicrobeTags()/2);
        return super.onPlay(player);
    }
}
