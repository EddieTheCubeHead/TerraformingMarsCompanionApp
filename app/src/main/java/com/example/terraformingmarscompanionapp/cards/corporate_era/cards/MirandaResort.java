package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MirandaResort extends Card {
    public MirandaResort(Game game) {
        super(Type.GREEN, game);
        name = "Miranda resort";
        price = 12;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        production_box.setMoneyProduction(player.getTags().getEarthTags());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        production_box.setMoneyProduction(owner_player.getTags().getEarthTags());
        super.playProductionBox();
    }
}
