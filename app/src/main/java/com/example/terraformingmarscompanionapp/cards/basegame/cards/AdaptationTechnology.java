package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.GameplayException;
import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class AdaptationTechnology extends Card {
    public AdaptationTechnology() {
        super(Type.BLUE);
        name = "Adaptation technology";
        price = 12;
        tags.add(Tag.SCIENCE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getModifiers().setBaseTrRequirementDiscount(player.getModifiers().getBaseTrRequirementDiscount() + 2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
