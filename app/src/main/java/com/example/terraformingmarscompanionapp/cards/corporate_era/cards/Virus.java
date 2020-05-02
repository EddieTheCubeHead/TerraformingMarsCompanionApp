package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Virus extends Card {
    public Virus(Game game) {
        super(Type.RED);
        name = "Virus";
        price = 1;
        tags.add(Tag.MICROBE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {

        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Poista eläin tai kasvi toiselta ja valinta
        super.playWithMetadata(player, data);
    }
}
