package com.example.terraformingmarscompanionapp.cards.corporate_era.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Teractor extends Card {
    public Teractor() {
        super(Type.CORPORATION);
        name = "Teractor";
        tags.add(Tag.EARTH);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(60);
        player.getModifiers().setEarthTagDiscount(3);
        super.playWithMetadata(player, data);
    }
}
