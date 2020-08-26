package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class VestaShipyard extends Card {
    public VestaShipyard() {
        super(Type.GREEN);
        name = "Vesta shipyard";
        price = 15;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        production_box.setTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }
}
