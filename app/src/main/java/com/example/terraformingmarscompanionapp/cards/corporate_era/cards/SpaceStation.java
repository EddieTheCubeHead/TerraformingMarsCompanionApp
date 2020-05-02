package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SpaceStation extends Card {
    public SpaceStation(Game game) {
        super(Type.BLUE);
        name = "Space station";
        price = 10;
        tags.add(Tag.SPACE);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeSpaceTagDiscount(2);
        return super.onPlay(player);
    }
}
