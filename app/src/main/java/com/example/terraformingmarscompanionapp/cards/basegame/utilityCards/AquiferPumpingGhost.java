package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AquiferPumpingGhost extends Card {
    public AquiferPumpingGhost(Game game) {
        super(Type.OTHER, game);
        tags.add(Tag.BUILDING);
        name = "Aquifer pumping ghost";
        price = 8;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        overridePlayActionCall();
        super.playWithMetadata(player, data);
    }
}
