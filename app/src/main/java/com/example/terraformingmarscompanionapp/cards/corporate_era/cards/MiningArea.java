package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MiningArea extends Card {
    public MiningArea(Game game) {
        super(Type.GREEN);
        name = "Mining area";
        price = 4;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Vaatimus että oman laatan vieressä steel tai titanium
        //TODO Productionin muuttaminen
        super.playWithMetadata(player, data);
    }
}
