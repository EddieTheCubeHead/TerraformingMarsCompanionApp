package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MediaArchives extends Card {
    public MediaArchives() {
        super(Type.GREEN);
        name = "Media archives";
        price = 8;
        tags.add(Tag.EARTH);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer event_count = 0;
        for (Player game_player : GameController.getPlayers()) {
            event_count += game_player.getTags().getEventTags();
        }
        player.getResources().setMoney(player.getResources().getMoney() + event_count);
        super.playWithMetadata(player, data);
    }
}
