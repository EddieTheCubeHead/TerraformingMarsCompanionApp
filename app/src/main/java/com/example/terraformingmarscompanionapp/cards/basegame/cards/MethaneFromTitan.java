package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MethaneFromTitan extends Card {
    public MethaneFromTitan(Game game) {
        super(Type.GREEN, game);
        name = "Methane from titan";
        price = 10;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        requirements.setMinOxygen(2);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHeatProduction(2);
        player.changePlantsProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
