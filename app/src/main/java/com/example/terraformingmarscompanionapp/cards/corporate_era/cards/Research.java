package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Research extends Card {
    public Research(Game game) {
        super(Type.GREEN);
        name = "Research";
        price = 11;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SCIENCE);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Vedä 2 korttia
        //TODO Toimii kuin pelattaisiin 2 Science tag korttia (eli toiminnot jotka aktivoituu science kortin pelatessa 2x)
        super.playWithMetadata(player, data);
    }
}
