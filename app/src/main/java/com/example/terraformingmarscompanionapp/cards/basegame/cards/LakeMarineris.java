package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class LakeMarineris extends Card {
    public LakeMarineris() {
        super(Type.GREEN);
        name = "Lake marineris";
        price = 18;
        requirements.setMinTemperature(0);
        victory_points = 2;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, game));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
