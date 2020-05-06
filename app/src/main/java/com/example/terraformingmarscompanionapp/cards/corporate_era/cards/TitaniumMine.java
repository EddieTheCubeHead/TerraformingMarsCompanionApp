package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TitaniumMine extends Card {
    public TitaniumMine(Game game) {
        super(Type.GREEN, game);
        name = "Titanium mine";
        price = 7;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }
}
