package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class AdaptedLichen extends Card {
    public AdaptedLichen(Game game) {
        super(Type.GREEN, game);
        name = "Adapted lichen";
        price = 9;
        tags.add(Tag.PLANT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
