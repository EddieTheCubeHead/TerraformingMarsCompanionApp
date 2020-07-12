package com.example.terraformingmarscompanionapp.ui.playDialogues;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.util.Log;
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
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Represents a numeric decision made by a player while playing a card.
 */
public class IntegerDialog extends AppCompatActivity {

    public static final String INTEGER_MIN = "min";
    public static final String INTEGER_MAX = "max";
    public static final String CARD_NAME = "card";
    public static final String TITLE = "title";

    private Integer result;

    static WeakReference<View> view;

    public static void displayDialog(Context context, Card card, Integer min, Integer max, String dialog_title)
    {

        //Building the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        view = new WeakReference<>(inflater.inflate(R.layout.dialog_integer, null));

        //findViewByIds
        TextView title = view.get().findViewById(R.id.title);
        EditText number_field = view.get().findViewById(R.id.edittext_number);

        title.setText(dialog_title);

        Button button_positive = view.get().findViewById(R.id.button_positive);

        LinearLayout root = view.get().findViewById(R.id.button_frame_lower);


        //Build and show the dialogue
        view.get().setBackgroundColor(Color.TRANSPARENT);


        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view.get())
                .setCancelable(false)
                .create();

        dialog.show();

        //Setting size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(  4*width / 5, WindowManager.LayoutParams.WRAP_CONTENT);

        button_positive.setOnClickListener(v -> {
            if (number_field.getText().toString().equals("")) {
                return;
            }

            Integer result = Integer.parseInt(number_field.getText().toString().trim());

            if (result < min) {
                Toast.makeText(context, "Number too small, minimum: " + min, Toast.LENGTH_SHORT).show();
                return;
            } else if (result > max) {
                Toast.makeText(context, "Number too big, maximum: " + max, Toast.LENGTH_SHORT).show();
                return;
            }
            dialog.dismiss();
            Log.i("IntegerDialog", "Dialogue dismissed and calling next event");
            card.onPlayServerHook(GameController.getCurrentPlayer(), result);
        });
    }
}
