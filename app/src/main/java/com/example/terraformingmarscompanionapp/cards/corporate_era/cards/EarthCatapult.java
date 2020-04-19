package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EarthCatapult extends Card {
    public EarthCatapult(Game game) {
        super(Type.BLUE);
        name = "Earth catapult";
        price = 23;
        tags.add(Tag.EARTH);
        owner_game = game;
        victory_points = 2;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeCardDiscount(2);
        return super.onPlay(player);
    }
}
