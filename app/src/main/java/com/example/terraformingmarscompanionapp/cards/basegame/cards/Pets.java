package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Pets extends ResourceCard implements EffectCard {
    public Pets(Game game) {
        super(Type.BLUE);
        name = "Pets";
        price = 10;
        tags.add(Tag.EARTH);
        tags.add(Tag.ANIMAL);
        resource_type = ResourceType.PET;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        resource_amount++;
        super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        resource_amount++;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }

}
