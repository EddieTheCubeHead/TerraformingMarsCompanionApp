package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class OptimalAerobraking extends Card implements EffectCard {
    public OptimalAerobraking() {
        super(Type.BLUE);
        name = "Optimal aerobraking";
        price = 7;
        tags.add(Tag.SPACE);
    }

    @Override
    public void cardEffect(Player player) throws InvalidResourcesException {
        if (owner_player == null) {
            return;
        } else if (owner_player != player){
            return;
        }
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() + 3);
        owner_player.getResources().setHeat(owner_player.getResources().getHeat() + 3);
    }
}
