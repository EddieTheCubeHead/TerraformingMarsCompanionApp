package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Thorgate extends Card {
    public Thorgate(Game game) {
        super(Type.CORPORATION);
        name = "Thorgate";
        tags.add(Tag.ENERGY);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoney(48);
        player.changeEnergyProduction(1);
        player.changeEnergyTagDiscount(3);
        return super.onPlay(player);
    }
}
