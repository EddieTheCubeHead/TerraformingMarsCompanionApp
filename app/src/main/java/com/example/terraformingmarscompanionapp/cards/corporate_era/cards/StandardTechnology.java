package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StandardTechnology extends Card implements EffectCard {
    public StandardTechnology(Game game) {
        super(Type.BLUE, game);
        name = "Standard technology";
        price = 6;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void cardEffect(Player player) {
        //TODO Kun pelaaja pelaa standard projectin hän saa 3 euroa
    }
}
