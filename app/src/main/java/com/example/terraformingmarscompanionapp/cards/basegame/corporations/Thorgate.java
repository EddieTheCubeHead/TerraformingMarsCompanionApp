package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Thorgate extends Card {
    public Thorgate(Game game) {
        super(Type.CORPORATION, game);
        name = "Thorgate";
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(48);
        production_box.setEnergyProduction(1);
        player.getModifiers().setEnergyTagDiscount(3);
        super.playWithMetadata(player, data);
    }
}
