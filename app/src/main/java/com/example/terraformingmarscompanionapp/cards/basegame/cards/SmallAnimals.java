package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SmallAnimals extends ResourceCard implements ActionCard {
    public SmallAnimals(Game game) {
        super(Type.BLUE);
        name = "Small animals";
        price = 6;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(6);
        resource_type = ResourceType.ANIMAL;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        //TODO toiselta pelaajalta kasvien poistaminen
        owner_player = player;
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            resource_amount++;
            action_used = true;
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
