package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class ImportedNitrogen extends Card {
    public ImportedNitrogen(Game game) {
        super(Type.RED, game);
        name = "Imported nitrogen";
        price = 23;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new ResourceEvent(ResourceCard.ResourceType.MICROBE, player, 3, true));
        GameController.addUiEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, 2, true));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeTerraformingRating(1);
        player.changePlants(4);
        super.playWithMetadata(player, data);
    }
}
