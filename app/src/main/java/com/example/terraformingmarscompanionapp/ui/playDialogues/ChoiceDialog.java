package com.example.terraformingmarscompanionapp.ui.playDialogues;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
public class ChoiceDialog {

    public enum USE_CASE {
        PLAYER,
        VIRUS,
        ROBOTIC_WORKFORCE,
        GENERAL
    }

    public static void displayDialog(Context context, String message, USE_CASE use_case, ArrayList<String> targets, Card card, Player player)
    {
        if (use_case.equals(USE_CASE.PLAYER)) {
            targets.add("nobody");
        }

        //Building the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //Visual edits
        view.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout linearLayout = view.findViewById(R.id.root_linearlayout);

        linearLayout.removeView(view.findViewById(R.id.title2));
        linearLayout.removeView(view.findViewById(R.id.spinner2));

        //findViewByIds
        TextView title = view.findViewById(R.id.title1);
        Spinner spinner = view.findViewById(R.id.spinner1);

        title.setText(message);

        //Making the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, targets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //Setting the size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(2 * width / 3, WindowManager.LayoutParams.WRAP_CONTENT);

        title.setText("Choose your target");

        view.findViewById(R.id.button_confirm).setOnClickListener(v -> {
            Integer target_index;
            if (!use_case.equals(USE_CASE.GENERAL)) {
                if (spinner.getSelectedItem() == "nobody") {
                    target_index = 0;
                } else {
                    target_index = GameController.getPlayerIndex((String) spinner.getSelectedItem());
                }
            } else {
                target_index = spinner.getSelectedItemPosition();
            }
            switch (use_case) {
                case ROBOTIC_WORKFORCE:
                    card.getProductionBox().playProductionBox(player, target_index);
                    break;
                case VIRUS:
                    card.onPlayServerHook(player, target_index + 2);
                    break;
                case GENERAL:
                case PLAYER:
                    card.onPlayServerHook(player, target_index);
                    break;
                default:
                    Log.i("Choice dialog", "Error in use case");
            }
            dialog.dismiss();
            //TODO next action in event stack
        });
    }
}
