package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ResearchOutpost extends Card {
    public ResearchOutpost() {
        super(Type.BLUE);
        name = "Research outpost";
        price = 18;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.RESEARCH_OUTPOST, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getModifiers().setCardDiscount(player.getModifiers().getCardDiscount() + 1);
        super.playWithMetadata(player, data);
    }
}
