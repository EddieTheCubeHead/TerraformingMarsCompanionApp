package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;
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
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;

import java.util.ArrayList;
import java.util.Arrays;

public class CardsBoughtActivity extends AppCompatActivity {

    GameController controller;
    Game game;
    Player player;

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
        ArrayList<Integer> range = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        if (controller.getGeneration() == 1)
            range.addAll(Arrays.asList(5, 6, 7, 8, 9, 10));

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
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, range);
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

        title.setText("Amount of cards bought:");

        view.findViewById(R.id.button_confirm).setOnClickListener(v -> {
            //TODO tähän
            dialog.dismiss();
            super.onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
