package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class RoverConstruction extends Card implements EffectCard {
    public RoverConstruction(Game game) {
        super(Type.BLUE, game);
        name = "Rover construction";
        price = 8;
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null) {
            owner_player.getResources().setMoney(owner_player.getResources().getMoney() + 2);
        }
    }
}
