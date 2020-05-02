package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TollStation extends Card {
    public TollStation(Game game) {
        super(Type.GREEN);
        name = "Toll station";
        price = 12;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Raha productionin nosto yhden verran jokaista vastustajien space tagia kohden
        super.playWithMetadata(player, data);
    }
}
