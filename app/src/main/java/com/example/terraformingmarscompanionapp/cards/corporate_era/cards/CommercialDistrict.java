package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CommercialDistrict extends Card {
    public CommercialDistrict(Game game) {
        super(Type.GREEN);
        name = "Commercial district";
        price = 16;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(4);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {

        //TODO Euron merkki laatan laitto
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        //TODO Pisteiden lasku
        super.onGameEnd();
    }
}
