package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Decomposers extends ResourceCard implements EffectCard {
    public Decomposers(Game game) {
        super(Type.BLUE, game);
        name = "Decomposers";
        price = 5;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(3);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null && owner_player == player) {
            resource_amount++;
        }
    }

    @Override
    public void onGameEnd() {
        victory_points = resource_amount/3;
        super.onGameEnd();
    }
}
