package com.example.terraformingmarscompanionapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.TileHandler;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.TileEventPacket;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TilePlacementActivity extends AppCompatActivity {

    View view;

    ImageMap mImageMap;
    Integer x = null;
    Integer y = null;
    Boolean is_valid = false;
    Placeable tile = null;
    TileHandler handler;

    Context self;

    ImageView tileicon;

    public static final String TILE = "tile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_placement);

        Toast.makeText(getApplicationContext(), "Please place your tile", Toast.LENGTH_SHORT).show();

        mImageMap = findViewById(R.id.map);
        mImageMap.setImageResource(R.drawable.hexamap);

        /*
            //chaging the size:
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

        mImageMap.setLayoutParams(new ViewGroup.LayoutParams(width, width));

         */

        tileicon = findViewById(R.id.imageView_tiletype);

        tile = Placeable.valueOf(getIntent().getStringExtra("tile"));
        handler = GameController.getGame().tile_handler;

        //There can only be nine oceans:
        if (tile == Placeable.OCEAN && GameController.getGame().getOceansPlaced() == 9) {
            exit(view);
        }

        // changing the icon depending the tile being placed
        switch (tile) {

            case CITY:

            case OCEAN:

            case MOHOLE:

            case NOCTIS:

            case CAPITAL:

            case GREENERY:

            case LAVA_FLOW:

            case LAND_OCEAN:

            case FLOOD_OCEAN:

            case MINING_AREA:

            case NUCLEAR_ZONE:

            case MINING_RIGHTS:

            case LAND_CLAIM:

            case VOLCANIC_CITY:

            case OCEAN_GREENERY:

            case URBANIZED_AREA:

            case ECOLOGICAL_ZONE:

            case NATURAL_RESERVE:

            case RESTRICTED_AREA:

            case RESEARCH_OUTPOST:

            case COMMERCIAL_DISTRICT:

                tileicon.setImageResource(R.drawable.ic_ph);
                break;


        }


        // add a click handler to react when areas are tapped
        mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler()
        {
            @Override
            public void onImageMapClicked(int id, ImageMap imageMap)
            {
                // when the area is tapped, show the name in a
                // text bubble
                String text;
                x = Integer.valueOf(mImageMap.getAreaAttribute(id, "coordinates").split(Pattern.quote(";"))[0]);
                y = Integer.valueOf(mImageMap.getAreaAttribute(id, "coordinates").split(Pattern.quote(";"))[1]);
                is_valid = handler.checkPlacementValidity(tile, x, y);
                if (!is_valid) {
                    text = "Position is not valid";
                    mImageMap.showBubble(text, id);
                }
                else {
                    text = "Position is valid";

                }
                mImageMap.showBubble(text, id);

            }

            @Override
            public void onBubbleClicked(int id)
            {
                // react to info bubble for area being tapped
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_tile_placement, container, false);

        return view;
    }

    public void placeTile(View view) {
        if (!is_valid) {
            Toast.makeText(getApplicationContext(), "Current placement is not valid!", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.i("TilePlacementActivity", "Calling handler.placeTile");
            ArrayList<String> flood_targets = handler.placeTile(GameController.getCurrentPlayer(), handler.getTile(x, y), tile);

            Log.i("TilePlacementActivity", "Finishing tile placement");
            if (flood_targets != null) {
                playFlooding(flood_targets);
            } else {
                exit(view);
            }
        }
    }

    public void exit(View view) {
        Intent intent = new Intent(this, InGameUI.class);
        intent.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(intent);
    }

    public void playFlooding(ArrayList<String> targets) {
        Card flooding = GameController.getGame().getDeck().get("Flooding");
        if (targets.size() == 0) {
            EventScheduler.addEvent(new PlayCardEvent(flooding, GameController.getCurrentPlayer(), 0));
        } else {
            EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:", targets, flooding, ChoiceDialog.USE_CASE.PLAYER));
        }
        exit(view);
    }
}
