package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class IoMiningIndustries extends Card {
    public IoMiningIndustries() {
        super(Type.GREEN);
        name = "Io mining indrustries";
        price = 41;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(2);
        production_box.setTitaniumProduction(2);
        super.playWithMetadata(player, data);
    }

    @Override
    public void onGameEnd() {
        victory_points = owner_player.getTags().getJovianTags();
        super.onGameEnd();
    }
}
