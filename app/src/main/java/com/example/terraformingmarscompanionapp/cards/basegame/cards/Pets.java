package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Pets extends ResourceCard implements EffectCard {
    public Pets(Game game) {
        super(Type.BLUE, game);
        name = "Pets";
        price = 10;
        tags.add(Tag.EARTH);
        tags.add(Tag.ANIMAL);
        resource_type = ResourceType.PET;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        resource_amount++;
        super.playWithMetadata(player, data);
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
        victory_points = resource_amount / 2;
        super.onGameEnd();
    }

}
