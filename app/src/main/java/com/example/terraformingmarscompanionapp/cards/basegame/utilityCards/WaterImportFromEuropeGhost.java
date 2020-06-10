package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class WaterImportFromEuropeGhost extends Card {
    public WaterImportFromEuropeGhost(Game game) {
        super(Type.OTHER, game);
        name = "Water import from europe ghost";
        price = 12;
        tags.add(Tag.SPACE);
    }
}
