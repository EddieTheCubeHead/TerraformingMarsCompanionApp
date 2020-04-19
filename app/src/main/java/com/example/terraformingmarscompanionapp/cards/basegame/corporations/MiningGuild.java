package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MiningGuild extends Card implements EffectCard {
    public MiningGuild(Game game) {
        super(Type.CORPORATION);
        name = "Mining guild";
        tags.add(Tag.BUILDING);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoney(30);
        player.changeSteel(5);
        player.changeSteelProduction(1);
        return super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.changeSteelProduction(1);
    }
}
