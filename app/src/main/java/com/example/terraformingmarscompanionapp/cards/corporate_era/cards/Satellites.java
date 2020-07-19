package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Satellites extends Card {
    public Satellites(Game game) {
        super(Type.GREEN, game);
        name = "Satellites";
        price = 10;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(player.getTags().getSpaceTags());
        super.playWithMetadata(player, data);
    }
}
