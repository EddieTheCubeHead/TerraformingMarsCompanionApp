package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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

    public void cardEffect(Player player) {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.changeSteelProduction(1);
    }
}
