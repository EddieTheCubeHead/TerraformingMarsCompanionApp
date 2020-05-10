package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class AerobrakedAmmoniaAsteroid extends Card {
    public AerobrakedAmmoniaAsteroid(Game game) {
        super(Type.RED, game);
        name = "Aerobraked ammonia asteroid";
        price = 26;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new ResourceEvent(ResourceCard.ResourceType.MICROBE, player, 2));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(3);
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
