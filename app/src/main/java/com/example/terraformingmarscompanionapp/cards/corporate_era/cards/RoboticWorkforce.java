package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.RoboticWorkforceChoiceEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;


//TODO system to play this fluidly
public final class RoboticWorkforce extends Card {
    public RoboticWorkforce() {
        super(Type.GREEN);
        name = "Robotic workforce";
        price = 9;
        tags.add(Tag.SCIENCE);
        requirements.setMinBuildingTags(1);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new RoboticWorkforceChoiceEvent(player));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
    }
}
