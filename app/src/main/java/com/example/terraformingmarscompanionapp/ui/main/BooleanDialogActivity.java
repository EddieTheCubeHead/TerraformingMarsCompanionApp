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

public class BooleanDialogActivity extends AppCompatActivity {

    AlertDialog dialog;

    public static final String FALSE_TEXT = "false_text";
    public static final String TRUE_TEXT = "true_text";
    public static final String TITLE_TEXT = "title";
    public static final String CARD_NAME = "card";

    private String false_text;
    private String true_text;
    private String title_text;
    private Card card;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        HashMap<String, Card> deck = game.getAllCards();

        Intent intent = getIntent();

        false_text = intent.getStringExtra(FALSE_TEXT);
        true_text = intent.getStringExtra(TRUE_TEXT);
        title_text = intent.getStringExtra(TITLE_TEXT);

        card = deck.get(intent.getStringExtra(CARD_NAME));

        //dialogin layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.dialog_integer, null);

        //findviewbyid't
        TextView title = view.findViewById(R.id.title);
        EditText number_field = view.findViewById(R.id.edittext_number);

        title.setText(title_text);
        Button button_negative = view.findViewById(R.id.button_negative);
        Button button_positive = view.findViewById(R.id.button_positive);

        //dialogin rakentaminen ja näyttäminen
            //kulmien läpinäkyvyys
        view.setBackgroundColor(Color.TRANSPARENT);

            //nappien tekstit
        button_negative.setText(false_text);
        button_positive.setText(true_text);

        //textviewn poisto
        ((LinearLayout) view.findViewById(R.id.root_linearlayout)).removeView(number_field);

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

        window.setLayout(  4*width / 5, WindowManager.LayoutParams.WRAP_CONTENT);

        //listenerit
        button_negative.setOnClickListener(v -> {
            card.playWithMetadata(controller.getCurrentPlayer(), 0);
            exit();
        });

        button_positive.setOnClickListener(v -> {
            card.playWithMetadata(controller.getCurrentPlayer(), 1);
            exit();
        });
    }

    private void exit() {
        dialog.dismiss();
        BooleanDialogActivity.super.onBackPressed();
    }
}
