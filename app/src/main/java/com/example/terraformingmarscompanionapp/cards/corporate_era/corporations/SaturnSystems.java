package com.example.terraformingmarscompanionapp.cards.corporate_era.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class SaturnSystems extends Card implements EffectCard {
    public SaturnSystems() {
        super(Type.CORPORATION);
        name = "Saturn systems";
        tags.add(Tag.JOVIAN);
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
