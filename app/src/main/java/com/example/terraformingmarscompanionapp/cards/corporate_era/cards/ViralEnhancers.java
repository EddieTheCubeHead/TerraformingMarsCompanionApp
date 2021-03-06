package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;

public final class ViralEnhancers extends Card {
    public ViralEnhancers(Game game) {
        super(Type.BLUE, game);
        name = "Viral enhancers";
        price = 9;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        owner_game = game;
    }
    //TODO create functionality with event scheduler, new event and ghost card
}
