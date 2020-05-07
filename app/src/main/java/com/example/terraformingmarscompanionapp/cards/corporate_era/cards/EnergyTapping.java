package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EnergyTapping extends Card {
    public EnergyTapping(Game game) {
        super(Type.GREEN, game);
        name = "Energy tapping";
        price = 3;
        tags.add(Tag.ENERGY);
        victory_points = -1;
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeEnergyProduction(1);
        //TODO vie 1 energia tuotanto
        super.playServerConnection(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
    }
}
