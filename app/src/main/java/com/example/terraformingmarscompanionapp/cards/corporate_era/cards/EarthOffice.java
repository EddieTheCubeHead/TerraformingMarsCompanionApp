package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EarthOffice extends Card {
    public EarthOffice(Game game) {
        super(Type.BLUE);
        name = "Earth Office";
        price = 1;
        tags.add(Tag.EARTH);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEarthTagDiscount(3);
        return super.onPlay(player);
    }
}
