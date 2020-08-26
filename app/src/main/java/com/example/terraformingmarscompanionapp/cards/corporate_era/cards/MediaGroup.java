package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MediaGroup extends Card implements EffectCard {
    public MediaGroup() {
        super(Type.BLUE);
        name = "Media group";
        price = 6;
        tags.add(Tag.EARTH);
    }

    @Override
    public void cardEffect(Player player) throws InvalidResourcesException {
        if (owner_player == null) {
            return;
        } else if (owner_player != player){
            return;
        }
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() + 3);
    }
}
