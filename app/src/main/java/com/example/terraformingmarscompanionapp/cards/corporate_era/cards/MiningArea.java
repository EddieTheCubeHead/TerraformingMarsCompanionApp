package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class MiningArea extends Card {
    public MiningArea(Game game) {
        super(Type.GREEN, game);
        name = "Mining area";
        price = 4;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.MINING_AREA, owner_game));
    }

    /**
     * A method to set the type of production this card gains for the purposes of playing the dreaded
     * {@link RoboticWorkforce}
     *
     * @param is_steel {@link Boolean} whether the placement bonus gained from placing the tile was steel
     */
    public void setPlacementBonus(Boolean is_steel) {
        if (is_steel) {
            production_box.setSteelProduction(1);
        } else {
            production_box.setTitaniumProduction(1);
        }
    }
}
