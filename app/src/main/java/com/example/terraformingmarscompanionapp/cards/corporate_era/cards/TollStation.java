package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TollStation extends Card {
    public TollStation(Game game) {
        super(Type.GREEN, game);
        name = "Toll station";
        price = 12;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer tag_amount = 0;
        for (Player opponent : GameController.getPlayers()) {
            if (opponent == player) {
                continue;
            }
            tag_amount += opponent.getTags().getSpaceTags();
        }
        production_box.setMoneyProduction(tag_amount);
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        Integer tag_amount = 0;
        for (Player opponent : GameController.getPlayers()) {
            if (opponent == owner_player) {
                continue;
            }
            tag_amount += opponent.getTags().getSpaceTags();
        }
        production_box.setMoneyProduction(tag_amount);
        super.playProductionBox();
    }
}
