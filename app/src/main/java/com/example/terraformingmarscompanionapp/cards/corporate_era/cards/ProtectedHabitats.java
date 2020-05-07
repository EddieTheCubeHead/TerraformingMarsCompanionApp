package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ProtectedHabitats extends Card implements EffectCard {
    public ProtectedHabitats(Game game) {
        super(Type.BLUE, game);
        name = "Protected habitats";
        price = 5;
        owner_game = game;
    }

    @Override
    public void cardEffect(Player player) {
        //todo ei voi poistaa kasveja eiläimiä tai bakteerejaa
    }
}
