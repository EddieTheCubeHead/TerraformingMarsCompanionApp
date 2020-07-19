package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class PhoboLog extends Card {
    public PhoboLog(Game game) {
        super(Type.CORPORATION, game);
        name = "PhoboLog";
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getModifiers().setTitaniumValueModifier(1);
        player.getResources().setMoney(23);
        player.getResources().setTitanium(10);
        super.playWithMetadata(player, data);
    }
}
