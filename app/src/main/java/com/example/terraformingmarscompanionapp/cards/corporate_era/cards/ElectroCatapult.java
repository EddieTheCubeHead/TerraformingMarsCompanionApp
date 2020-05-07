package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;

public final class ElectroCatapult extends Card implements ActionCard {
    public ElectroCatapult(Game game) {
        super(Type.BLUE, game);
        name = "Electro catapult";
        price = 17;
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(8);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    public void cardAction() {
        if (owner_player.getSteel() < 1) {
            GameController.getInstance().useAction();
            actionServerHook(owner_player, 0);
            return;
        } else if (owner_player.getPlants() < 1) {
            GameController.getInstance().useAction();
            actionServerHook(owner_player, 1);
            return;
        }
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Spend plants or steel?");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Plants");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Steel");
        context.startActivity(intent);
    }


    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            owner_player.takePlants(1);
        } else {
            owner_player.takeSteel(1);
        }
        owner_player.changeMoney(7);
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionValidity() {
        return (action_used || (owner_player.getSteel() < 1 && owner_player.getPlants() < 1));
    }
}
