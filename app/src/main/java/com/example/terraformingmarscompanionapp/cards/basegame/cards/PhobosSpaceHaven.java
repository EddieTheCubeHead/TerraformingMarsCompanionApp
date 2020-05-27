package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PhobosSpaceHaven extends Card {
    public PhobosSpaceHaven(Game game) {
        super(Type.GREEN, game);
        name = "PhobosSpaceHaven";
        price = 25;
        tags.add(Tag.SPACE);
        tags.add(Tag.CITY);
        victory_points = 3;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        owner_game.tile_handler.placePhobos(player);
        super.playWithMetadata(player, data);
    }
}
