package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Thorgate extends Card {
    public Thorgate(Game game) {
        super(Type.CORPORATION, game);
        name = "Thorgate";
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(48);
        production_box.setEnergyProduction(1);
        player.changeEnergyTagDiscount(3);
        super.playWithMetadata(player, data);
    }
}
