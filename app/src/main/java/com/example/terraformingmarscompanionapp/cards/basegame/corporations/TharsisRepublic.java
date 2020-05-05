package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.FirstAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

public final class TharsisRepublic extends Card implements EffectCard, FirstAction {
    public TharsisRepublic(Game game) {
        super(Type.CORPORATION, game);
        name = "Tharsis republic";
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.playCard(owner_game.getGhosts().get("Tharsis republic ghost"), new CardCostPacket(GameController.getInstance().getCurrentPlayer().getName(), 0, 0, 0, 0, 0, 0));
        player.changeMoney(40);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null && owner_player == player) {
            owner_player.changeMoney(3);
        }
    }

    @Override
    public void firstAction() {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        owner_player.addCity();
        GameController.getInstance().executeNextEvent();
    }
}
