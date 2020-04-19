package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Windmills extends Card {
    public Windmills(Game game) {
        super(Type.GREEN);
        name = "Windmills";
        price = 6;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinOxygen(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSteelProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
