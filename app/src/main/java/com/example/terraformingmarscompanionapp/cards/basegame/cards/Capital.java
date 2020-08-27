package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Capital extends Card {
    public Capital() {
        super(Type.GREEN);
        name = "Capital";
        price = 26;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMinOceans(4);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.CAPITAL, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setEnergyProduction(-2);
        production_box.setMoneyProduction(5);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
