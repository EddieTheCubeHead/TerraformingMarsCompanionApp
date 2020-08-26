package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TitaniumMine extends Card {
    public TitaniumMine() {
        super(Type.GREEN);
        name = "Titanium mine";
        price = 7;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }
}
