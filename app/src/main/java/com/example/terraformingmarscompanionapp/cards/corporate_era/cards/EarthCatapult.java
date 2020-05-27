package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EarthCatapult extends Card {
    public EarthCatapult(Game game) {
        super(Type.BLUE, game);
        name = "Earth catapult";
        price = 23;
        tags.add(Tag.EARTH);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeCardDiscount(2);
        super.playWithMetadata(player, data);
    }
}
