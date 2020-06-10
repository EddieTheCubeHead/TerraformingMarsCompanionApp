package com.example.terraformingmarscompanionapp.cards.corporate_era.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class SaturnSystems extends Card implements EffectCard {
    public SaturnSystems(Game game) {
        super(Type.CORPORATION, game);
        name = "Saturn systems";
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(42);
        player.getResources().setTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }

        owner_player.getResources().setMoneyProduction(owner_player.getResources().getMoneyProduction() + 1);
    }
}
