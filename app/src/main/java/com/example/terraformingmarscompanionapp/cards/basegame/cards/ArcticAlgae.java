package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ArcticAlgae extends Card implements EffectCard {
    public ArcticAlgae(Game game) {
        super(Type.BLUE, game);
        name = "Arctic algae";
        price = 12;
        tags.add(Tag.PLANT);
        requirements.setMaxTemperature(-12);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setPlants(player.getResources().getPlants() + 1);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.getResources().setPlants(owner_player.getResources().getPlants() + 2);
    }
}
