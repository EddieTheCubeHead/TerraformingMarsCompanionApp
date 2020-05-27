package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

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
