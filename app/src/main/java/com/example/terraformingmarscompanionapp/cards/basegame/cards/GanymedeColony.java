package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class GanymedeColony extends Card {
    public GanymedeColony(Game game) {
        super(Type.GREEN, game);
        name = "Ganymede colony";
        price = 20;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        tags.add(Tag.CITY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.addCity();
        owner_game.update_manager.onVpCardPlayed(player);
        owner_game.tile_handler.placeGanymede(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
    }
}
