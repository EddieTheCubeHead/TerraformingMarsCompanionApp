package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Shuttles extends Card {
    public Shuttles() {
        super(Type.BLUE);
        name = "Shuttles";
        price = 10;
        tags.add(Tag.SPACE);
        requirements.setMinOxygen(5);
        requirements.setMinEnergyProduction(1);
        victory_points = 0;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getModifiers().setSpaceTagDiscount(player.getModifiers().getSpaceTagDiscount() + 2);
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
