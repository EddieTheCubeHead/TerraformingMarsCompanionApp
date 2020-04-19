package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ImportedNitrogen extends Card {
    public ImportedNitrogen(Game game) {
        super(Type.RED);
        name = "Imported nitrogen";
        price = 23;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeTerraformingRating(1);
        player.changePlants(4);
        //TODO lisää 3 mikrobia ja kaksi eläintä
        return super.onPlay(player);
    }
}
