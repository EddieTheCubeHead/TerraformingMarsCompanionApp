package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Archaebacteria extends Card {
    public Archaebacteria(Game game) {
        super(Type.GREEN, game);
        name = "Archaebacteria";
        price = 6;
        tags.add(Tag.MICROBE);
        requirements.setMaxTemperature(-18);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
