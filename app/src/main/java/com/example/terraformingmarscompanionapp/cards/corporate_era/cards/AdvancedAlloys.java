package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class AdvancedAlloys extends Card {
    public AdvancedAlloys(Game game) {
        super(Type.BLUE, game);
        name = "Advanced alloys";
        price = 9;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getModifiers().setSteelValueModifier(player.getModifiers().getSteelValueModifier() + 1);
        player.getModifiers().setTitaniumValueModifier(player.getModifiers().getTitaniumValueModifier() + 1);
        super.playWithMetadata(player, data);
    }
}