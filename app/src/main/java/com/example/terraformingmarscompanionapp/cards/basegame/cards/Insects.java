package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Insects extends Card {
    public Insects(Game game) {
        super(Type.GREEN, game);
        name = "Insects";
        price = 9;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(6);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(player.getTags().getPlantTags());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        production_box.setPlantsProduction(owner_player.getTags().getPlantTags());
        super.playProductionBox();
    }
}
