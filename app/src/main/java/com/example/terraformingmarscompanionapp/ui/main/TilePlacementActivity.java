package com.example.terraformingmarscompanionapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.game.tileSystem.TileHandler;

import java.util.regex.Pattern;

public class TilePlacementActivity extends AppCompatActivity {

    View view;

    ImageMap mImageMap;
    Integer x = null;
    Integer y = null;
    Boolean is_valid = false;
    Placeable tile = null;
    TileHandler handler;

    ImageView tileicon;

    public static final String TILE = "tile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_placement);

        Toast.makeText(getApplicationContext(), "Please place your tile", Toast.LENGTH_SHORT).show();

        mImageMap = findViewById(R.id.map);
        mImageMap.setImageResource(R.drawable.hexamap);

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
            handler.placeTile(GameController.getInstance().getCurrentPlayer(), handler.getTile(x, y), tile);
            exit(view);
        }
    }

    public void exit(View view) {
        Intent intent = new Intent(this, InGameUI.class);
        intent.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(intent);
    }
}
