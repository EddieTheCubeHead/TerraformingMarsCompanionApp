package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class SpecialDesign extends Card {
    public SpecialDesign() {
        super(Type.RED);
        name = "Special design";
        price = 4;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getModifiers().setSpecialDesignEffect(true);
        player.getModifiers().setBaseTrRequirementDiscount(player.getModifiers().getBaseTrRequirementDiscount() + 2);
        super.playWithMetadata(player, data);
    }
}
