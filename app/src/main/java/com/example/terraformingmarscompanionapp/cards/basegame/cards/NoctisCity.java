package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NoctisCity extends Card {
    public NoctisCity(Game game) {
        super(Type.GREEN);
        name = "Noctis city";
        price = 18;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeNoctis(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        player.addCity();
        return super.onPlay(player);
    }
}
