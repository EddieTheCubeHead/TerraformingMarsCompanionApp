package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Helion extends Card {
    public Helion(Game game) {
        super(Type.CORPORATION, game);
        name = "Helion";
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(42);
        production_box.setHeatProduction(3);
        player.getModifiers().setHeatIsMoney(true);
        super.playWithMetadata(player, data);
    }
}
