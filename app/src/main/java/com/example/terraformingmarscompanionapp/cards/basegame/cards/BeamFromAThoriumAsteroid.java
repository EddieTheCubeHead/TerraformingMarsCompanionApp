package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class BeamFromAThoriumAsteroid extends Card {
    public BeamFromAThoriumAsteroid(Game game) {
        super(Type.GREEN, game);
        name = "Beam from a thorium asteroid";
        price = 32;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        tags.add(Tag.ENERGY);
        requirements.setMinJovianTags(1);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        production_box.setHeatProduction(3);
        production_box.setEnergyProduction(3);
        super.playWithMetadata(player, data);
    }
}
