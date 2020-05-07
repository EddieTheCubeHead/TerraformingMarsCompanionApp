package com.example.terraformingmarscompanionapp.cards.corporate_era.cards.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Teractor extends Card {
    public Teractor(Game game) {
        super(Type.CORPORATION, game);
        name = "Teractor";
        tags.add(Tag.EARTH);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(60);
        player.changeEarthTagDiscount(3);
        super.playWithMetadata(player, data);
    }
}
