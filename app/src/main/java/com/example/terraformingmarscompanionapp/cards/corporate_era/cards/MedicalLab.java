package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MedicalLab extends Card {
    public MedicalLab() {
        super(Type.GREEN);
        name = "Medical lab";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }



    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        player.getResources().setMoneyProduction(player.getResources().getMoney() + player.getTags().getBuildingTags()/2);
        super.playWithMetadata(player, data);
    }
}
