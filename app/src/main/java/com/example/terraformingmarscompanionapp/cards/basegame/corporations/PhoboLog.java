package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PhoboLog extends Card {
    public PhoboLog(Game game) {
        super(Type.CORPORATION, game);
        name = "PhoboLog";
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeTitaniumValueModifier(1);
        player.changeMoney(23);
        player.changeTitanium(10);
        super.playWithMetadata(player, data);
    }
}
