package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.FirstAction;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class TharsisRepublic extends Card implements EffectCard, FirstAction {
    private Boolean first_action_used = false;

    public TharsisRepublic(Game game) {
        super(Type.CORPORATION, game);
        name = "Tharsis republic";
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        EventScheduler.addEvent(new PlayCardEvent(owner_game.getGhosts().get("Tharsis republic ghost"), player, 0));

        player.getResources().setMoney(40);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null && owner_player == player) {
            owner_player.getResources().setMoney(owner_player.getResources().getMoney() + 3);
        }
    }

    @Override
    public void firstAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.CITY, owner_game));
        EventScheduler.playNextEvent(GameController.getContext());
        first_action_used = true;
    }

    @Override
    public Boolean firstActionUsed() {
        return first_action_used;
    }
}
