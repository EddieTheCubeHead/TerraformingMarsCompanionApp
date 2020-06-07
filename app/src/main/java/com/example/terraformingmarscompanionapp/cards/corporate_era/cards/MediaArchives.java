package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MediaArchives extends Card {
    public MediaArchives(Game game) {
        super(Type.GREEN, game);
        name = "Media archives";
        price = 8;
        tags.add(Tag.EARTH);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer event_count = 0;
        for (Player game_player : GameController.getPlayers()) {
            event_count += game_player.getEventTags();
        }
        player.changeMoney(event_count);
        super.playWithMetadata(player, data);
    }
}
