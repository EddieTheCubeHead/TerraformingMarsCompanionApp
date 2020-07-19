package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ImmigrationShuttles extends Card {
    public ImmigrationShuttles(Game game) {
        super(Type.GREEN, game);
        name = "Immigration shuttles";
        price = 31;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        victory_points = (owner_game.getCitiesInSpace() + owner_game.getCitiesOnMars()) / 3;
        super.onGameEnd();
    }
}
