package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;

public final class NitriteReducingBacteria extends ResourceCard implements ActionCard {
    public NitriteReducingBacteria(Game game) {
        super(Type.BLUE, game);
        name = "Nitrite reducing bacteria";
        price = 11;
        tags.add(Tag.MICROBE);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        resource_amount += 3;
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        if (resource_amount < 3) {
            onPlayServerHook(owner_player, 0);
            return;
        }
        Context context = GameController.getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Add a microbe or raise TR?");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Microbe");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "TR");
        context.startActivity(intent);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else {
            resource_amount -= 3;
            owner_player.changeTerraformingRating(1);
        }
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }
}
