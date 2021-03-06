package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MethaneFromTitan extends Card {
    public MethaneFromTitan(Game game) {
        super(Type.GREEN, game);
        name = "Methane from titan";
        price = 28;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        requirements.setMinOxygen(2);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(2);
        production_box.setPlantsProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
