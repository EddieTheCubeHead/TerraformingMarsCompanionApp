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

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

import java.util.ArrayList;

public class PlayerChoiceActivity extends AppCompatActivity {

    GameController controller;
    Game game;
    Player player;

    public static final String CARD_INTENT = "card";
    public static final String TARGETS = "targets";
    public static final String SPECIAL_CASE = "case";
    public static final String CASE_PRODCUTION = "production";

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

        if (!(intent.getStringArrayExtra(TARGETS) == null)) {
            for (String target : intent.getStringArrayExtra(TARGETS)) {
                targets.add(game.getPlayer(target));
            }
        } else {
            targets.addAll(controller.getPlayers());
        }

        Card card = game.getDeck().get(intent.getStringExtra(CARD_INTENT));

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //visuaalinen muokkaus
        view.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout linearLayout = view.findViewById(R.id.root_linearlayout);

        linearLayout.removeView(view.findViewById(R.id.title2));
        linearLayout.removeView(view.findViewById(R.id.spinner2));

        //findviewbyid't
        TextView title = view.findViewById(R.id.title1);
        Spinner spinner = view.findViewById(R.id.spinner1);

        //spinnerin valmistaminen
        //arrayadapter kutsuu toString -metodia kortissa.
        ArrayAdapter<Player> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, targets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //dialogin koon muuttaminen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(2 * width / 3, WindowManager.LayoutParams.WRAP_CONTENT);

        title.setText("Choose your target");

        view.findViewById(R.id.button_confirm).setOnClickListener(v -> {
            Integer target_index = GameController.getInstance().getPlayerIndex((Player)spinner.getSelectedItem());
            if (special_case != null) {
                switch (special_case) {
                    case CASE_PRODCUTION:
                        card.getProductionBox().playProductionBox(player, target_index);
                }
            } else {
                card.playServerConnection(player, target_index);
            }
            dialog.dismiss();
            PlayerChoiceActivity.super.onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
