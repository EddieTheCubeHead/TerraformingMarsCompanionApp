package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ResearchOutpost extends Card {
    public ResearchOutpost(Game game) {
        super(Type.BLUE, game);
        name = "Research outpost";
        price = 18;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.RESEARCH_OUTPOST, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeCardDiscount(1);
        super.playWithMetadata(player, data);
    }
}
