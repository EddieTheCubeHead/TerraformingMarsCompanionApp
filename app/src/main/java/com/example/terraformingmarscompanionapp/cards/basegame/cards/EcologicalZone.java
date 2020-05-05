package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class EcologicalZone extends ResourceCard implements EffectCard {
    public EcologicalZone(Game game) {
        super(Type.GREEN);
        name = "Ecological zone";
        price = 12;
        tags.add(Tag.ANIMAL);
        tags.add(Tag.PLANT);
        requirements.setMinPersonalGreeneries(1);
        resource_type = ResourceType.ANIMAL;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.ECOLOGICAL_ZONE, owner_game));

        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player == player) {
            resource_amount++;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }
}
