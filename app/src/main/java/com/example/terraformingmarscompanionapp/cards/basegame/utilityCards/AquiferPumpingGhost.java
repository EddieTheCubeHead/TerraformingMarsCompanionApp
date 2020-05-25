package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class AquiferPumpingGhost extends Card {
    public AquiferPumpingGhost(Game game) {
        super(Type.OTHER, game);
        tags.add(Tag.BUILDING);
        name = "Aquifer pumping ghost";
        price = 8;
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.playNextEvent(context);
    }
}
