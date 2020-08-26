package com.example.terraformingmarscompanionapp.ui.playDialogues;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
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

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.RoboticWorkforcePlayEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;

import java.util.ArrayList;

/**
 * A simple activity for choosing the target player for a card.
 */
public class ChoiceDialog {

    public enum UseCase {
        PLAYER,
        VIRUS,
        ROBOTIC_WORKFORCE,
        GENERAL,
        RAIDERS_STEEL,
        RAIDERS_MONEY,
        SABOTAGE_TITANIUM,
        SABOTAGE_STEEL,
        SABOTAGE_MONEY
    }

    public static void displayDialog(Context context, String message, UseCase use_case, ArrayList<String> targets, Card card, Player player)
    {
        if (use_case.equals(UseCase.PLAYER)) {
            targets.add("nobody");
        }

        // Building the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        // Visual edits
        view.setBackgroundColor(Color.TRANSPARENT);

        LinearLayout linearLayout = view.findViewById(R.id.root_linearlayout);

        linearLayout.removeView(view.findViewById(R.id.title2));
        linearLayout.removeView(view.findViewById(R.id.spinner2));

        // findViewByIds
        TextView title = view.findViewById(R.id.title1);
        Spinner spinner = view.findViewById(R.id.spinner1);

        title.setText(message);

        // Making the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, targets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        // Setting the size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(2 * width / 3, WindowManager.LayoutParams.WRAP_CONTENT);

        title.setText(message);

        view.findViewById(R.id.button_confirm).setOnClickListener(v -> {
            Integer target_index;
            if (!use_case.equals(UseCase.GENERAL)) {
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
                    EventScheduler.addEvent(new RoboticWorkforcePlayEvent(card, player, target_index));
                    EventScheduler.playNextEvent(context);
                    break;

                case VIRUS:
                    card.onPlayServerHook(player, target_index + 2);
                    break;

                // These work the same way. The card first gets an integer through usecase GENERAl
                // then onPlayServerHook method opens another dialogue with one of these usecases and the
                // multiples of five in data are used to differentiate between the first choices made
                case SABOTAGE_MONEY:
                    if (target_index != 0) {
                        target_index += 5;
                    }
                case SABOTAGE_STEEL:
                case RAIDERS_MONEY:
                    if (target_index != 0) {
                        target_index += 5;
                    }
                case SABOTAGE_TITANIUM:
                case RAIDERS_STEEL:
                    card.playWithMetadata(player, target_index + 3);
                    break;

                case GENERAL:
                case PLAYER:

                    // See if dialog called from onPlay or action. Call methods in card accordingly
                    if (card.getOwner() == null) {
                        card.onPlayServerHook(player, target_index);
                    } else if (card instanceof ActionCard) {
                        Log.i("ChoiceDialog", "Using action with metadata " + target_index);
                        ((ActionCard) card).actionServerHook(player, target_index);
                    } else {
                        Log.i("Choice dialog Error", "Choice dialog called from an owned card that is not an action card.");
                    }
                    break;
                default:
                    Log.i("Choice dialog", "Error in use case");
            }
            dialog.dismiss();
        });
    }
}
