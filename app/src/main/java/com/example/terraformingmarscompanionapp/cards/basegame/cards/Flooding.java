package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Flooding extends Card {
    public Flooding() {
        super(Type.RED);
        name = "Flooding";
        price = 7;
        tags.add(Tag.EVENT);
        victory_points = -1;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.FLOOD_OCEAN, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (!(data == 0)) {
            Player target = GameController.getPlayer(data);
            target.getResources().setMoney(target.getResources().getMoney() - 4);
        }
    }
}
