package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class PowerGrid extends Card {
    public PowerGrid(Game game) {
        super(Type.GREEN, game);
        name = "Power grid";
        price = 18;
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(player.getEnergyTags());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        production_box.setEnergyProduction(owner_player.getEnergyTags());
        super.playProductionBox();
    }
}
