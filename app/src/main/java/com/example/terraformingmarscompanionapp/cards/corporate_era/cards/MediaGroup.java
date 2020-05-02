package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MediaGroup extends Card implements EffectCard {
    public MediaGroup(Type card_type) {
        super(card_type);
    }

    @Override
    public Integer onPlay(Player player) {
        return super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player != player){
            return;
        }
        owner_player.changeMoney(3);
    }
}
