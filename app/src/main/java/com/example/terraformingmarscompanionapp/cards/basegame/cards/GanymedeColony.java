package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class GanymedeColony extends Card {
    public GanymedeColony() {
        super(Type.GREEN);
        name = "Ganymede colony";
        price = 20;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        tags.add(Tag.CITY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.addCity();
        game.update_manager.onVpCardPlayed(player);
        game.tile_handler.placeGanymede(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        victory_points = owner_player.getTags().getJovianTags();
        super.onGameEnd();
    }
}
