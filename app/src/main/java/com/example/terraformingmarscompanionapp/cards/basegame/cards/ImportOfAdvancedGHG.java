package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ImportOfAdvancedGHG extends Card {
    public ImportOfAdvancedGHG(Game game) {
        super(Type.RED, game);
        name = "Import of advanced GHG";
        price = 9;
        tags.add(Tag.SPACE);
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(2);
        super.playWithMetadata(player, data);
    }
}
