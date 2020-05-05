package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AsteroidMining extends Card {
    public AsteroidMining(Game game) {
        super(Type.GREEN, game);
        name = "Asteroid mining";
        price = 30;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeTitaniumProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
