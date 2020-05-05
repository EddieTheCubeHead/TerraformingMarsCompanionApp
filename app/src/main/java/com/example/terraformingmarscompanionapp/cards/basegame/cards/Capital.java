package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Capital extends Card {
    public Capital(Game game) {
        super(Type.GREEN);
        name = "Capital";
        price = 26;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMinOceans(4);
        requirements.setMinEnergyProduction(2);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CAPITAL, owner_game));
        player.addCity();
        player.changeEnergyProduction(-2);
        player.changeMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeEnergyProduction(-2);
        player.changeMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
