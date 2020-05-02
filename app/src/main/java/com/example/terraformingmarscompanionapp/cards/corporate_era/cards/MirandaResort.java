package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MirandaResort extends Card {
    public MirandaResort(Game game) {
        super(Type.GREEN);
        name = "Miranda resort";
        price = 12;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeMoneyProduction(owner_player.getEarthTags());
        return super.onPlay(player);
    }
}
