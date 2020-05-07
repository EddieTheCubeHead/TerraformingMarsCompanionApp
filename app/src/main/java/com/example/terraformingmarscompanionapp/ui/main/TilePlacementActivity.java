package com.example.terraformingmarscompanionapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.TileHandler;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;

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
            //koon vaihtaminen
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;

        mImageMap.setLayoutParams(new ViewGroup.LayoutParams(width, width));

         */

        tileicon = findViewById(R.id.imageView_tiletype);

        tile = Placeable.valueOf(getIntent().getStringExtra("tile"));
        handler = GameController.getInstance().getGame().tile_handler;

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

            case RESERVED_AREA:

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
            GameController controller = GameController.getInstance();
            if (controller.getGame().getServerMultiplayer()) {
                GameActions.sendTileEvent(new TileEventPacket(tile, controller.getCurrentPlayer().getName(), x, y));
            }
            ArrayList<String> flood_targets = handler.placeTile(controller.getCurrentPlayer(), handler.getTile(x, y), tile);

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
        if (targets.size() == 0) {
            GameController.getInstance().getGame().getDeck().get("Flooding").playServerConnection(GameController.getInstance().getCurrentPlayer(), 0);
            exit(view);
            return;
        }
        String[] targets_array = targets.toArray(new String[0]);
        Intent intent = new Intent(this, PlayerChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(PlayerChoiceActivity.CARD_INTENT, "Flooding");
        intent.putExtra(PlayerChoiceActivity.TARGETS, targets_array);
        this.startActivity(intent);
    }
}
