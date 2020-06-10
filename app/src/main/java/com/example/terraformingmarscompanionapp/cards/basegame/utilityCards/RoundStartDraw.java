package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataIntegerEvent;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

public final class RoundStartDraw extends Card {
    public RoundStartDraw(Game game) {
        super(Type.OTHER, game);
        name = "Round start draw";
    }

    @Override
    public void initializePlayEvents(Player player) {

        //Setting the varying parameters of the draw
        String draw_message;
        Integer max_draw_amount;

        if (owner_game.getServerMultiplayer()) {
            draw_message = "Enter the amount of cards you bought:";
        } else {
            draw_message = String.format("%s, enter the amount of cards you bought:", player.getName());
        }

        max_draw_amount = GameController.getGeneration() == 1 ? 10 : 4;

        EventScheduler.addEvent(new MetadataIntegerEvent(draw_message, 0, max_draw_amount, this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHandSize(data);
        player.getResources().setMoney(player.getResources().getMoney() - (data * (3 + player.getModifiers().getCardResearchCostModifier())));
        super.playWithMetadata(player, data);
    }
}
