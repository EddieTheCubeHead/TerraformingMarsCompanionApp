package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class HeatTrappers extends Card {
    public HeatTrappers(Game game) {
        super(Type.GREEN);
        name = "Heat trappers";
        price = 6;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        Integer player_to_take_from = 0;
        //TODO UI kysy keneltä viedään
        //Tämäm voi kutsua suoraan UI:sta
        playWithMetadata(player, player_to_take_from);

        return player_to_take_from;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takeHeatProduction(2);
        }
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
