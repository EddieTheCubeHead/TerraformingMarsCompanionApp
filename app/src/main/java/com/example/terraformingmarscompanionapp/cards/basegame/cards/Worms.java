package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Worms extends Card {
    public Worms(Game game) {
        super(Type.GREEN, game);
        name = "Worms";
        price = 8;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction((player.getMicrobeTags()+1)/2);
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        production_box.setPlantsProduction(owner_player.getMicrobeTags()/2);
        super.playProductionBox();
    }
}
