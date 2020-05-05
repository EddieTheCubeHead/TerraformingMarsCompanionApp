package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.FirstAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Inventrix extends Card implements FirstAction {
    public Inventrix(Game game) {
        super(Type.CORPORATION, game);
        name = "Inventrix";
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void onPlay(Player player) {
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(45);
        player.changeBaseTrRequirementDiscount(2);

        player.changeHandSize(3);
        super.playWithMetadata(player, data);
    }

    @Override
    public void firstAction() {
        //TODO UI-prompti ota kolme korttia
        owner_player.changeHandSize(3);
    }
}
