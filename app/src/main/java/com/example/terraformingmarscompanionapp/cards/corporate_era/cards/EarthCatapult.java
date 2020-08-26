package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class EarthCatapult extends Card {
    public EarthCatapult() {
        super(Type.BLUE);
        name = "Earth catapult";
        price = 23;
        tags.add(Tag.EARTH);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        player.getModifiers().setCardDiscount(player.getModifiers().getCardDiscount() + 2);
        super.playWithMetadata(player, data);
    }
}
