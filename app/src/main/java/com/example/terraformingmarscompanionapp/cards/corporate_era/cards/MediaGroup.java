package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MediaGroup extends Card implements EffectCard {
    public MediaGroup(Type card_type, Game game) {
        super(Type.BLUE, game);
        name = "Media group";
        price = 6;
        tags.add(Tag.EARTH);
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
