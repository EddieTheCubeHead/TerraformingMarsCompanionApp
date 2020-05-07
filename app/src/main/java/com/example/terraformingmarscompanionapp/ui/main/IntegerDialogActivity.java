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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.RoundStartDraw;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

import java.util.HashMap;

public class IntegerDialogActivity extends AppCompatActivity {

    AlertDialog dialog;

    public static final String INTEGER_MIN = "min";
    public static final String INTEGER_MAX = "max";
    public static final String CARD_NAME = "card";
    public static final String TITLE = "title";

    private Integer result;
    private Integer min;
    private Integer max;
    private Card card;
    private String dialog_title;

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

        Intent intent = getIntent();
        min = intent.getIntExtra(INTEGER_MIN, 0);
        max = intent.getIntExtra(INTEGER_MAX, 10);

        dialog_title = intent.getStringExtra(TITLE);

        card = GameController.getInstance().getGame().getAllCards().get(intent.getStringExtra(CARD_NAME));

        //findviewbyid't
        TextView title = view.findViewById(R.id.title);
        EditText number_field = view.findViewById(R.id.edittext_number);

        title.setText(dialog_title);

        Button button_negative = view.findViewById(R.id.button_negative);
        Button button_positive = view.findViewById(R.id.button_positive);

        LinearLayout root = view.findViewById(R.id.button_frame_lower);

        root.removeView(root.findViewById(R.id.button_negative_hidden));
        root.removeView(root.findViewById(R.id.button_positive_hidden));


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

        window.setLayout(  4*width / 5, WindowManager.LayoutParams.WRAP_CONTENT);

        //listenerit
        button_negative.setOnClickListener(v -> {
            exit();
        });

        button_positive.setOnClickListener(v -> {
            if (number_field.getText().toString().equals("")) {
                return;
            }

            result = Integer.parseInt(number_field.getText().toString().trim());

            if (result < min) {
                Toast.makeText(getApplicationContext(), "Number too small, minimum: " + min, Toast.LENGTH_SHORT).show();
                return;
            } else if (result > max) {
                Toast.makeText(getApplicationContext(), "Number too big, maximum: " + max, Toast.LENGTH_SHORT).show();
                return;
            }
            card.playServerConnection(GameController.getInstance().getCurrentPlayer(), result);
            exit();
        });
    }

    private void exit() {
        dialog.dismiss();
        Intent inGameUi = new Intent(this, InGameUI.class);
        inGameUi.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        if (!(card instanceof RoundStartDraw)) {
            card.overridePlayActionCall();
        }
        startActivity(inGameUi);
    }
}
