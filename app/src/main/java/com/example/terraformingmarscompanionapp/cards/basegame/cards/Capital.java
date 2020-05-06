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
        super(Type.GREEN, game);
        name = "Capital";
        price = 26;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMinOceans(4);
        requirements.setMinEnergyProduction(2);
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CAPITAL, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-2);
        production_box.setMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
