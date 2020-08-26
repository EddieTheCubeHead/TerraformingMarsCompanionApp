package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;

public final class AquiferPumpingGhost extends Card {
    public AquiferPumpingGhost() {
        super(Type.OTHER);
        tags.add(Tag.BUILDING);
        name = "Aquifer pumping ghost";
        price = 8;
    }
}
