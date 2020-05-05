package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class VestaShipyard extends Card {
    public VestaShipyard(Game game) {
        super(Type.GREEN, game);
        name = "Vesta shipyard";
        price = 15;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }
}
