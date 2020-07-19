package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;

public final class WaterImportFromEuropeGhost extends Card {
    public WaterImportFromEuropeGhost(Game game) {
        super(Type.OTHER, game);
        name = "Water import from europe ghost";
        price = 12;
        tags.add(Tag.SPACE);
    }
}
