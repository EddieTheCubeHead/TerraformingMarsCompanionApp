package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ImmigrantCity extends Card implements EffectCard {
    public ImmigrantCity() {
        super(Type.GREEN);
        name = "Immigrant city";
        price = 13;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
        requirements.setMinMoneyProduction(-4);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.CITY, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(-1);
        production_box.setEnergyProduction(-1);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.getResources().setMoneyProduction(owner_player.getResources().getMoneyProduction() + 1);
    }
}
