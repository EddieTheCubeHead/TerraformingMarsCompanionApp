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
        super(Type.BLUE);
        name = "Research outpost";
        price = 18;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.addCity();
        player.changeCardDiscount(1);
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.RESEARCH_OUTPOST, owner_game));
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeCardDiscount(1);
        super.onPlay(player);
    }
}
