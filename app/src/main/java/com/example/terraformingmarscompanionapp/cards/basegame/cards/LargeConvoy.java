package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

public final class LargeConvoy extends Card {
    public LargeConvoy(Game game) {
        super(Type.RED, game);
        name = "Large convoy";
        price = 36;
        tags.add(Tag.SPACE);
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        victory_points = 2;
    }

    @Override
    public void onPlay(Player player) {
        //TODO boolean valinta UI
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getName(), player.getName(), data));
        }
        if (data == 1) {
            //TODO korttiresurssi UI
        }
        playWithMetadata(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.changePlants(5);
        }
        GameController.getInstance().addUiEvent(new PromptEvent("Please draw 2 cards"));
        player.changeHandSize(2);
        super.playWithMetadata(player, data);
    }
}
