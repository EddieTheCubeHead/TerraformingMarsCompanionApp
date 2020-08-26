package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class PhobosSpaceHaven extends Card {
    public PhobosSpaceHaven() {
        super(Type.GREEN);
        name = "PhobosSpaceHaven";
        price = 25;
        tags.add(Tag.SPACE);
        tags.add(Tag.CITY);
        victory_points = 3;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setTitaniumProduction(1);
        game.update_manager.onVpCardPlayed(player);
        game.tile_handler.placePhobos(player);
        super.playWithMetadata(player, data);
    }
}
