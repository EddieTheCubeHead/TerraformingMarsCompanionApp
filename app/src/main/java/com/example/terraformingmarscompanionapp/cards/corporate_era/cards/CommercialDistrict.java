package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class CommercialDistrict extends Card {
    public CommercialDistrict(Game game) {
        super(Type.GREEN, game);
        name = "Commercial district";
        price = 16;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.COMMERCIAL_DISTRICT, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(4);
        super.playWithMetadata(player, data);
    }
}
