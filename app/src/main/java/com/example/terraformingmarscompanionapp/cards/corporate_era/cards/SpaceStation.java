package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class SpaceStation extends Card {
    public SpaceStation() {
        super(Type.BLUE);
        name = "Space station";
        price = 10;
        tags.add(Tag.SPACE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        player.getModifiers().setSpaceTagDiscount(player.getModifiers().getSpaceTagDiscount() + 2);
        super.playWithMetadata(player, data);
    }
}
