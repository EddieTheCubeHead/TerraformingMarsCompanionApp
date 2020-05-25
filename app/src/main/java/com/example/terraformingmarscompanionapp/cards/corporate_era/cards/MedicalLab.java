package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MedicalLab extends Card {
    public MedicalLab(Game game) {
        super(Type.GREEN, game);
        name = "Medical lab";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }



    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeMoneyProduction(player.getBuildingTags()/2);
        super.playWithMetadata(player, data);
    }
}
