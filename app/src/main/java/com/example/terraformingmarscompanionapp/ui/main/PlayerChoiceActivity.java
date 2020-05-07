package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple activity for choosing the target player for a card.
 */
public class PlayerChoiceActivity extends AppCompatActivity {

    GameController controller;
    Game game;
    Player player;

    public static final String CARD_INTENT = "card";
    public static final String TARGETS = "targets";
    public static final String SPECIAL_CASE = "case";
    public static final String CASE_PRODCUTION = "production";
    public static final String CASE_VIRUS = "virus";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        controller = GameController.getInstance();
        game = controller.getGame();
        player = controller.getCurrentPlayer();

        displayDialog();
    }

    private void displayDialog()
    {
        ArrayList<Player> targets = new ArrayList<>();

        Intent intent = getIntent();

        String special_case = intent.getStringExtra(SPECIAL_CASE);

        Player nobody = new Player(game, "(nobody)");

        targets.add(nobody);

        System.out.println(Arrays.toString(intent.getStringArrayExtra(TARGETS)));

        if (!(intent.getStringArrayExtra(TARGETS) == null)) {
            for (String target : intent.getStringArrayExtra(TARGETS)) {
                targets.add(game.getPlayer(target));
            }
        } else {
            targets.addAll(controller.getPlayers());
        }

        Card card = game.getDeck().get(intent.getStringExtra(CARD_INTENT));

        //Building the layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //Visual edits
        view.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout linearLayout = view.findViewById(R.id.root_linearlayout);

        linearLayout.removeView(view.findViewById(R.id.title2));
        linearLayout.removeView(view.findViewById(R.id.spinner2));

        //findViewByIds
        TextView title = view.findViewById(R.id.title1);
        Spinner spinner = view.findViewById(R.id.spinner1);

        //Making the spinner
        ArrayAdapter<Player> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, targets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //Setting the size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(2 * width / 3, WindowManager.LayoutParams.WRAP_CONTENT);

        title.setText("Choose your target");

        view.findViewById(R.id.button_confirm).setOnClickListener(v -> {
            Integer target_index;
            if (spinner.getSelectedItem() == nobody) {
                target_index = 0;
            } else {
                target_index = GameController.getInstance().getPlayerIndex((Player) spinner.getSelectedItem());
            }

            if (special_case != null) {
                switch (special_case) {
                    case CASE_PRODCUTION:
                        card.getProductionBox().playProductionBox(player, target_index);
                    case CASE_VIRUS:
                        card.onPlayServerHook(player, target_index + 2);
                }
            } else {
                card.onPlayServerHook(player, target_index);
            }
            dialog.dismiss();
            exit();
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void exit() {
        Intent inGameUi = new Intent(this, InGameUI.class);
        inGameUi.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(inGameUi);
    }
}
