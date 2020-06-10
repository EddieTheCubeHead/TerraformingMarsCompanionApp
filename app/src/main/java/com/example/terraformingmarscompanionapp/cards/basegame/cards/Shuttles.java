package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Shuttles extends Card {
    public Shuttles(Game game) {
        super(Type.BLUE, game);
        name = "Shuttles";
        price = 10;
        tags.add(Tag.SPACE);
        requirements.setMinOxygen(5);
        requirements.setMinEnergyProduction(1);
        victory_points = 0;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getModifiers().setSpaceTagDiscount(player.getModifiers().getSpaceTagDiscount() + 2);
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
