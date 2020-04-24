package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.ServerGameController;

import java.util.ArrayList;

public final class Flooding extends Card {
    public Flooding(Game game) {
        super(Type.RED);
        name = "Flooding";
        price = 7;
        tags.add(Tag.EVENT);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        ArrayList<String> eligible_to_take;
        Integer player_to_take_from = 0;
        while (true) {
            eligible_to_take = owner_game.tile_handler.placeFloodOcean(player);
            if (eligible_to_take != null && !eligible_to_take.get(1).equals("E")) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        //TODO poista toiselta neljä rahaa UI eligible_to_take listan pohjalta
        //Tämän voi kutsua suoraan UI:sta
        playWithMetadata(player, player_to_take_from);

        return player_to_take_from;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (!(data == 0)) {
            owner_game.getPlayer(ServerGameController.getPlayerName(data)).takeMoney(4);
        }
        super.onPlay(player);
    }
}
