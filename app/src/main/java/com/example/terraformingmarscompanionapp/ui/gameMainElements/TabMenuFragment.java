package com.example.terraformingmarscompanionapp.ui.gameMainElements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;

/**
 * A fragment that houses the main tabview UI of the game. Composed of code from the original
 * activity responsible from this created by Aleksanteri Reijo. Transformed into a fragment and optimized
 * by Eetu Asikainen
 *
 * @author Aleksanteri Reijo, Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class TabMenuFragment extends Fragment {

    InGameActivity parent;
    Game game;
    SectionsPagerAdapter sections_pager_adapter;
    ViewPager view_pager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof InGameActivity) {
            parent = (InGameActivity) getActivity();
        } else {
            Log.i("TabMenuFragment", "Called from an activity that is not an instance of InGameActivity");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main_view = inflater.inflate(R.layout.fragment_tab_menu, container, false);game = GameController.getGame();

        sections_pager_adapter = new SectionsPagerAdapter(parent, getChildFragmentManager());
        view_pager = main_view.findViewById(R.id.view_pager);
        view_pager.setAdapter(sections_pager_adapter);

        TabLayout tabs = main_view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(view_pager);

        // Setting the onClickListeners for the buttons
        main_view.findViewById(R.id.undoButton).setOnClickListener(view -> {

            Log.i("TabMenuFragment", "Undo pressed");

            // Temporary undo button
            try {
                GameController.loadGame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Hex-ui testing code commented out

            /*2TileMapFragment map_fragment = new TileMapFragment(/*add data here/);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction map_transaction = manager.beginTransaction();
            map_transaction.replace(R.id.main_layout, map_fragment, "map");
            state = State.MAP;
            map_transaction.commit();*/
        });

        main_view.findViewById(R.id.mapButton).setOnClickListener(v -> {
            if (!GameController.checkTurnEligibility()) {
                Toast.makeText(parent, "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            } else if (GameController.getGreeneryRound()) {
                Toast.makeText(parent, "Can only build greeneries!", Toast.LENGTH_SHORT).show();
            }

            Log.i("TabMenuFragment", "Open map UI clicked");
            // TODO replace with the hex UI fragment
            // startTestingActivity();
        });

        main_view.findViewById(R.id.searchButton).setOnClickListener(view ->  {
            if (!GameController.checkTurnEligibility()) {
                Toast.makeText(parent, "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            } else if (GameController.getGreeneryRound()) {
                Toast.makeText(parent, "Can only build greeneries!", Toast.LENGTH_SHORT).show();
            }

            Log.i("TabMenuFragment", "Open search UI clicked");
            parent.startSearchFragment();
        });

        main_view.findViewById(R.id.passButton).setOnClickListener(view -> {
            if (!GameController.checkTurnEligibility()) {
                Toast.makeText(parent, "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.i("TabMenuFragment", "Pass turn clicked");

            GameController.endTurn(parent);
            if (game.getServerMultiplayer()) {
                GameActions.sendActionUse(new ActionUsePacket(true, false));
            }
        });

        return main_view;
    }
}