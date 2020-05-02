package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MarsUniverse extends Card implements EffectCard {
    public MarsUniverse(Game game) {
        super(Type.BLUE);
        name = "Mars universe";
        price = 8;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        //TODO science tagin pelattua kortin vaihto kädestä (sisältyy tämä kortti)
    }
}
