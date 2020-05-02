package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class InventorsGuild extends Card implements ActionCard {
    public InventorsGuild(Game game) {
        super(Type.GREEN);
        name = "Inventors' guild";
        price = 9;
        tags.add(Tag.SCIENCE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
        //TODO Kortin nosto tähän?
    }

    @Override
    public Integer getPrice() {
        return super.getPrice();
    }

    @Override
    public Boolean getHasRequirement() {
        return super.getHasRequirement();
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            //TODO Kortin nosto ja valinta ostaako
            action_used = true;
            return 0;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}

