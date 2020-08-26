package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.game.cardClasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class StandardGreenery extends StandardProject {
    public StandardGreenery() {
        super();
        name = "Standard project: Greenery";
        price = 23;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.GREENERY, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
    }
}
