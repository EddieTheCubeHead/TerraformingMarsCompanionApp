package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class NoctisFarming extends Card {
    public NoctisFarming(Game game) {
        super(Type.GREEN, game);
        name = "Noctis farming";
        price = 10;
        tags.add(Tag.PLANT);
        tags.add(Tag.BUILDING);
        requirements.setMinTemperature(-20);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(1);
        player.getResources().setPlants(player.getResources().getPlants() + 2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
