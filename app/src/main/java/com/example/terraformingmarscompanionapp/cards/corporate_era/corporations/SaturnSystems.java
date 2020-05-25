package com.example.terraformingmarscompanionapp.cards.corporate_era.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SaturnSystems extends Card implements EffectCard {
    public SaturnSystems(Game game) {
        super(Type.CORPORATION, game);
        name = "Saturn systems";
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(42);
        player.changeTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }

        owner_player.changeMoneyProduction(1);
    }
}
