package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class IoMiningIndustries extends Card {
    public IoMiningIndustries(Game game) {
        super(Type.GREEN, game);
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
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
        super.onGameEnd();
    }
}
