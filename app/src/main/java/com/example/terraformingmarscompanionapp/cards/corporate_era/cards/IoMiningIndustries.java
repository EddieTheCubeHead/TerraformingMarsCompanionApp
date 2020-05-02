package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class IoMiningIndustries extends Card {
    public IoMiningIndustries(Game game) {
        super(Type.GREEN);
        name = "Io mining indrustries";
        price = 41;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoneyProduction(2);
        player.changeTitaniumProduction(2);
        return super.onPlay(player);
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
        super.onGameEnd();
    }
}
