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

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

import java.util.HashMap;

/**
 * A dialogue for a "boolean" decision made by a player when playing a card. Can be extended to
 * 3 or 4 choices, but looks horrible with 3 options
 */
public class BooleanDialogActivity extends AppCompatActivity {

    AlertDialog dialog;

    public static final String FALSE_TEXT = "false_text";
    public static final String TRUE_TEXT = "true_text";
    public static final String TITLE_TEXT = "title";
    public static final String CARD_NAME = "card";
    public static final String EXTRA_TEXT_1 = "extra1";
    public static final String EXTRA_TEXT_2 = "extra2";

    private String false_text;
    private String true_text;
    private String title_text;
    private String extra_text_1;
    private String extra_text_2;
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
        extra_text_1 = intent.getStringExtra(EXTRA_TEXT_1);
        extra_text_2 = intent.getStringExtra(EXTRA_TEXT_2);

        card = deck.get(intent.getStringExtra(CARD_NAME));

        //For building the layout of the dialogue
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.dialog_integer, null);

        //All required findViewByIds
        TextView title = view.findViewById(R.id.title);
        EditText number_field = view.findViewById(R.id.edittext_number);

        title.setText(title_text);
        Button button_negative = view.findViewById(R.id.button_negative);
        Button button_positive = view.findViewById(R.id.button_positive);
        Button extra_first = view.findViewById(R.id.button_negative_hidden);
        Button extra_second = view.findViewById(R.id.button_positive_hidden);

        LinearLayout root = view.findViewById(R.id.button_frame_lower);

        if (extra_text_1 != null) {
            extra_first.setText(extra_text_1);
            extra_first.setOnClickListener(v -> {
                executeWithData(2);
            });
        } else {
            root.removeView(root.findViewById(R.id.button_negative_hidden));
        }

        if (extra_text_2 != null) {
            extra_second.setText(extra_text_2);
            extra_second.setOnClickListener(v -> {
                executeWithData(3);
            });
        }else {
            root.removeView(root.findViewById(R.id.button_positive_hidden));
        }

        //Calling and showing the dialogue
            //transparent corners
        view.setBackgroundColor(Color.TRANSPARENT);

            //nappien tekstit
        button_negative.setText(false_text);
        button_positive.setText(true_text);

        ((LinearLayout) view.findViewById(R.id.root_linearlayout)).removeView(number_field);

        dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //Setting the size of the dialogue
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(  4*width / 5, WindowManager.LayoutParams.WRAP_CONTENT);

        //listeners
        button_negative.setOnClickListener(v -> {
            executeWithData(0);
        });

        button_positive.setOnClickListener(v -> {
            executeWithData(1);
        });
    }

    private void executeWithData(Integer data) {
        card.overridePlayActionCall();
        if (card.getOwner() == null) {
            card.onPlayServerHook(GameController.getInstance().getCurrentPlayer(), data);
        } else {
            if (card instanceof ActionCard) {
                ((ActionCard) card).actionServerHook(card.getOwner(), data);
            }
        }
        exit();
    }

    private void exit() {
        dialog.dismiss();
        Intent inGameUi = new Intent(this, InGameUI.class);
        inGameUi.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(inGameUi);
    }
}
