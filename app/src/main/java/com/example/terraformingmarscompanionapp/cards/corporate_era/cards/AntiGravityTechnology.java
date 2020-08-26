package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class AntiGravityTechnology extends Card {
    public AntiGravityTechnology() {
        super(Type.BLUE);
        name = "Anti-gravity technology";
        price = 14;
        tags.add(Tag.SCIENCE);
        requirements.setMinScienceTags(7);
        victory_points = 3;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getModifiers().setCardDiscount(player.getModifiers().getCardDiscount() + 2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
