package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class WavePower extends Card {
    public WavePower() {
        super(Type.GREEN);
        name = "Wave power";
        price = 8;
        tags.add(Tag.ENERGY);
        requirements.setMinOceans(3);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
