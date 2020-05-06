package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class LandClaim extends Card {
    public LandClaim(Game game) {
        super(Type.RED, game);
        name = "Asteroid mining consortium";
        price = 1;
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player)
    {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.RESERVED_AREA, owner_game));
        super.onPlay(player);
    }
}
