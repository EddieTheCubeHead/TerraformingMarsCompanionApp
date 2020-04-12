package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.FirstAction;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TharsisRepublic extends Card implements EffectCard, FirstAction {
    public TharsisRepublic(Game game) {
        super("corporation");
        name = "Tharsis republic";
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.playCard(owner_game.getDeck().get("Tharsis republic ghost"), player);
        player.changeMoney(40);
        super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null && owner_player == player) {
            owner_player.changeMoney(3);
        }
    }

    @Override
    public void firstAction() {
        owner_game.tile_handler.placeCity(owner_player);
    }
}
