package com.example.terraformingmarscompanionapp.cards.prelude.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class HousePrinting extends Card {
    public HousePrinting() {
        super(Type.GREEN);
        name = "House printing";
        price = 10;
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setSteelProduction(player.getResources().getSteelProduction() + 1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
