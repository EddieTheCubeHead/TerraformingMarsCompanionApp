package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class AsteroidMiningConsortium extends Card {
    public AsteroidMiningConsortium(Game game) {
        super(Type.GREEN, game);
        name = "Asteroid mining consortium";
        price = 13;
        tags.add(Tag.JOVIAN);
        requirements.setMinEnergyTags(2);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data)
    {
        production_box.setStealTitaniumProduction(data);
        production_box.setTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
