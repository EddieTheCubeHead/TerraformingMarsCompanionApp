package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class AntiGravityTechnology extends Card {
    public AntiGravityTechnology(Game game) {
        super(Type.BLUE, game);
        name = "Anti-gravity technology";
        price = 14;
        tags.add(Tag.SCIENCE);
        requirements.setMinScienceTags(7);
        victory_points = 3;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data){
        player.getModifiers().setCardDiscount(player.getModifiers().getCardDiscount() + 2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
