package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;

public final class Virus extends Card {
    public Virus(Game game) {
        super(Type.RED, game);
        name = "Virus";
        price = 1;
        tags.add(Tag.MICROBE);
        tags.add(Tag.EVENT);
        owner_game = game;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, PlayerChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(PlayerChoiceActivity.CARD_INTENT, this.getName());
        context.startActivity(intent);
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (data == 0) {
            GameController.getInstance().addUiEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, -2));
        } else if (data == 1) {
            Context context = GameController.getInstance().getContext();
            Intent intent = new Intent(context, PlayerChoiceActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra(PlayerChoiceActivity.CARD_INTENT, this.getName());
            intent.putExtra(PlayerChoiceActivity.SPECIAL_CASE, PlayerChoiceActivity.CASE_VIRUS);
            context.startActivity(intent);
            return;
        } else {
            data -= 2;
        }
        super.playServerConnection(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data > 0) {
            GameController.getInstance().getPlayer(data).takePlants(5);
        }
        super.playWithMetadata(player, data);
    }
}
