package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ImmigrationShuttles extends Card {
    public ImmigrationShuttles() {
        super(Type.GREEN);
        name = "Immigration shuttles";
        price = 31;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setMoneyProduction(5);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        victory_points = (game.getCitiesInSpace() + game.getCitiesOnMars()) / 3;
        super.onGameEnd();
    }
}
