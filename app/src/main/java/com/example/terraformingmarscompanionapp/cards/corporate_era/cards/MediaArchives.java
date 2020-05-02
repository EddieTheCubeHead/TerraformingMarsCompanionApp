package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MediaArchives extends Card {
    public MediaArchives(Game game) {
        super(Type.GREEN);
        name = "Media archives";
        price = 8;
        tags.add(Tag.EARTH);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoney(owner_player.getEventTags());
        return super.onPlay(player);
    }
}
