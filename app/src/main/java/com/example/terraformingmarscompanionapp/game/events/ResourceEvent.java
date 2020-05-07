package com.example.terraformingmarscompanionapp.game.events;

import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.ActivityDialogSearch;

/**
 * Prompt the player to decide on which card to put resources to
 */
public final class ResourceEvent extends GameEvent {
    private ResourceCard.ResourceType resource_type = ResourceCard.ResourceType.MICROBE;
    private Player player;
    private Integer amount = 0;
    private String special_case = "";
    private Boolean owner_required = false;

    public ResourceEvent(ResourceCard.ResourceType resource_type, Player player, Integer amount) {
        this.resource_type = resource_type;
        this.player = player;
        this.amount = amount;
    }

    public ResourceEvent(ResourceCard.ResourceType resource_type, Player player, Integer amount, Boolean owner_required) {
        this.resource_type = resource_type;
        this.player = player;
        this.amount = amount;
        this.owner_required = true;
    }

    public ResourceEvent(String special_case, Player player) {
        this.special_case = special_case;
        this.player = player;
    }

    @Override
    public void playEvent() {
        Intent intent = new Intent(GameController.getInstance().getContext(), ActivityDialogSearch.class);
        intent.putExtra(ActivityDialogSearch.RESOURCE_TYPE, resource_type.toString());
        intent.putExtra(ActivityDialogSearch.AMOUNT, amount);
        intent.putExtra(ActivityDialogSearch.SPECIAL_CASE, special_case);
        intent.putExtra(ActivityDialogSearch.OWNER_ONLY, owner_required);
        intent.putExtra(ActivityDialogSearch.PLAYER_NAME, player.getName());
        GameController.getInstance().getContext().startActivity(intent);
    }
}
