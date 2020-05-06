package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

import java.util.HashMap;

public class IntegerDialogActivity extends AppCompatActivity {

    AlertDialog dialog;

    //todo tarkat valid types

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        HashMap<String, Card> deck = game.getDeck();

        //dialogin layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.dialog_integer, null);

        //findviewbyid't
        TextView title = view.findViewById(R.id.title);
        EditText number_field = view.findViewById(R.id.edittext_number);

        Button button_negative = view.findViewById(R.id.button_negative);
        Button button_positive = view.findViewById(R.id.button_positive);

        //dialogin rakentaminen ja näyttäminen
        view.setBackgroundColor(Color.TRANSPARENT);

            //negatiivisen napin poisto
        button_negative.setClickable(false);
        button_negative.setAlpha(0);

        dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //dialogin koon muuttaminen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(  2*width / 3, WindowManager.LayoutParams.WRAP_CONTENT);

        //listenerit
        button_negative.setOnClickListener(v -> {
            finish();
        });

        button_positive.setOnClickListener(v -> {
            //Integer.parseInt(number_field.getText().toString().trim());
            //todo tähän asioita
            finish();
        });
    }
}
