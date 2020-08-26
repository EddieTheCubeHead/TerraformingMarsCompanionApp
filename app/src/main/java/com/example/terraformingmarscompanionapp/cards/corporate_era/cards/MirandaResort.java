package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MirandaResort extends Card {
    public MirandaResort() {
        super(Type.GREEN);
        name = "Miranda resort";
        price = 12;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        production_box.setMoneyProduction(player.getTags().getEarthTags());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() throws InvalidResourcesException {
        production_box.setMoneyProduction(owner_player.getTags().getEarthTags());
        super.playProductionBox();
    }
}
