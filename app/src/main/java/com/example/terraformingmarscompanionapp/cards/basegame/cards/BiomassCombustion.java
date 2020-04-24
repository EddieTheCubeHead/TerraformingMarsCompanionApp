package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BiomassCombustion extends Card {
    public BiomassCombustion(Game game) {
        super(Type.GREEN);
        name = "Biomass combustion";
        price = 4;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinOxygen(6);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        Integer player_to_take_from = 0;
        //TODO UI kysy keneltä viedään
        playWithMetadata(player, player_to_take_from);

        return player_to_take_from;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlantsProduction(1);
        }
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
