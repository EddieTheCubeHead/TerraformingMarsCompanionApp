package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AdaptationTechnology extends Card {
    public AdaptationTechnology(Game game) {
        super(Type.BLUE);
        name = "Adaptation technology";
        price = 12;
        tags.add(Tag.SCIENCE);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeBaseTrRequirementDiscount(2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
