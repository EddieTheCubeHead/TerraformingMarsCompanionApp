package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class CallistoPenalMines extends Card {
    public CallistoPenalMines() {
        super(Type.GREEN);
        name = "Callisto penal mines";
        price = 24;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setMoneyProduction(3);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
