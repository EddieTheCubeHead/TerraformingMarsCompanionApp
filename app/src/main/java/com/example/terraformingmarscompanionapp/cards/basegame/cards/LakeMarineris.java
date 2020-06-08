package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class LakeMarineris extends Card {
    public LakeMarineris(Game game) {
        super(Type.GREEN, game);
        name = "Lake marineris";
        price = 18;
        requirements.setMinTemperature(0);
        victory_points = 2;
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new TileEvent(Placeable.OCEAN, owner_game));
        EventScheduler.addEvent(new TileEvent(Placeable.OCEAN, owner_game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
