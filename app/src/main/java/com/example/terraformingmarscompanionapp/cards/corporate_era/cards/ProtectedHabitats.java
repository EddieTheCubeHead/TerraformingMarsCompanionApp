package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ProtectedHabitats extends Card {
    public ProtectedHabitats() {
        super(Type.BLUE);
        name = "Protected habitats";
        price = 5;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getModifiers().setOrganicsProtected(true);
        super.playWithMetadata(player, data);
    }
}
