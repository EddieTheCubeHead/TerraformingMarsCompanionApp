package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class Virus extends Card {
    public Virus(Game game) {
        super(Type.RED, game);
        name = "Virus";
        price = 1;
        tags.add(Tag.MICROBE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        //TODO boolean valinta UI
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (data == 0) {
            GameController.getInstance().addUiEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, owner_game, -2));
        } else if (data == 1) {
            //TODO pelaajan valinta UI
            return;
        } else {
            data -= 2;
        }
        super.playServerConnection(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data > 0) {
            GameController.getInstance().getPlayer(data).takePlants(5);
        }
        super.playWithMetadata(player, data);
    }
}
