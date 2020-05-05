package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ImmigrantCity extends Card implements EffectCard {
    public ImmigrantCity(Game game) {
        super(Type.GREEN);
        name = "Immigrant city";
        price = 13;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CITY, owner_game));
        player.addCity();
        player.changeMoneyProduction(-2);
        player.changeEnergyProduction(-1);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-2);
        player.changeEnergyProduction(-1);
        super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.changeMoneyProduction(1);
    }
}
