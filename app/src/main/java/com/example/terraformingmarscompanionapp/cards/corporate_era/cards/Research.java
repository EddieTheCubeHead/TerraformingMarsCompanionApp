package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Research extends Card {
    public Research(Game game) {
        super(Type.GREEN, game);
        name = "Research";
        price = 11;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SCIENCE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        //TODO UI-prompt, nosta kaksi korttia
        player.changeHandSize(2);
        super.playWithMetadata(player, data);
    }
}
