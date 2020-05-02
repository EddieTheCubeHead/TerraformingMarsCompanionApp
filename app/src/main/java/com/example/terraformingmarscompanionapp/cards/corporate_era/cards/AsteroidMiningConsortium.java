package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AsteroidMiningConsortium extends Card {
    public AsteroidMiningConsortium(Game game) {
        super(Type.GREEN);
        name = "Asteroid mining consortium";
        price = 13;
        tags.add(Tag.JOVIAN);
        requirements.setMinTitaniumProduction(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data)
    {
        //TODO Vie toisen pelaajan tititanium production 1
        super.playWithMetadata(player, data);
    }


}
