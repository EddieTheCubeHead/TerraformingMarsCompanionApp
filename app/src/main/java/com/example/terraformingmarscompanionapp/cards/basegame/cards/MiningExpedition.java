package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MiningExpedition extends Card {
    public MiningExpedition(Game game) {
        super(Type.RED);
        name = "Mining expedition";
        price = 12;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        Integer player_to_take_from = 0;
        //TODO UI kysy keneltä poistetaan
        playWithMetadata(player, player_to_take_from);

        return player_to_take_from;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlants(2);
        }
        player.changeSteel(2);
        owner_game.raiseOxygen(player);
        super.onPlay(player);
    }
}
