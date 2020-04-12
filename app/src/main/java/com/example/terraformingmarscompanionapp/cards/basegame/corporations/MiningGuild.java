package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class MiningGuild extends Card implements EffectCard {
    public MiningGuild(Game game) {
        super("corporation");
        name = "Mining guild";
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addBuildingTag();
        player.changeMoney(30);
        player.changeSteel(5);
        player.changeSteelProduction(1);
        player.setCorporation(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.changeSteelProduction(1);
    }
}
