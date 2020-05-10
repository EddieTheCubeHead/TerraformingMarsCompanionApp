package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class EonChasmaNationalPark extends Card {
    public EonChasmaNationalPark(Game game) {
        super(Type.GREEN, game);
        name = "Eon chasma national park";
        price = 16;
        tags.add(Tag.PLANT);
        tags.add(Tag.BUILDING);
        requirements.setMinTemperature(-12);
        victory_points = 1;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, 1, true));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(3);
        production_box.setMoneyProduction(2);
        super.playWithMetadata(player, data);
    }
}
