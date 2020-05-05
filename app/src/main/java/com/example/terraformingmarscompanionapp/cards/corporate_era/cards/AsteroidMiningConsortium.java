package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AsteroidMiningConsortium extends Card {
    public AsteroidMiningConsortium(Game game) {
        super(Type.GREEN, game);
        name = "Asteroid mining consortium";
        price = 13;
        tags.add(Tag.JOVIAN);
        requirements.setMinTitaniumProduction(1);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player) {
        //TODO pelaajan valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data)
    {
        player.changeTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }


}
