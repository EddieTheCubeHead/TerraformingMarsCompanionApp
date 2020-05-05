package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Herbivores extends ResourceCard implements EffectCard {
    public Herbivores(Game game) {
        super(Type.BLUE, game);
        name = "Herbivores";
        price = 12;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(8);
        resource_type = ResourceType.ANIMAL;
    }

    @Override
    public void onPlay(Player player) {
        //TODO pelaajan valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlantsProduction(1);
        }
        resource_amount++;
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player != player) {
            return;
        }
        resource_amount++;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }
}
