package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Credicor extends Card implements EffectCard {
    public Credicor() {
        super(Type.CORPORATION);
        name = "Credicor";
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setMoney(57);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) throws InvalidResourcesException {
        if (owner_player != null && owner_player == player) {
            player.getResources().setMoney(player.getResources().getMoney() + 4);
        }
    }
}
