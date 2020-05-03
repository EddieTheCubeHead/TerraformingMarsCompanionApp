package com.example.terraformingmarscompanionapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;
import com.example.terraformingmarscompanionapp.webSocket.WebSocketHandler;

import java.util.ArrayList;


public class GameCreationActivity extends AppCompatActivity {
    ArrayList<String> player_names = new ArrayList<>();
    boolean hellas_elysium = false;
    boolean corporate_era = false;
    boolean prelude = false;
    boolean colonies = false;
    boolean venus = false;
    boolean turmoil = false;
    boolean extra_corporations = false;
    Integer map = 0;

    EditText edittext_name;
    TextView textview_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation);


        WebSocketHandler.createWebSocketClient();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserActions.loginUser("Eddie", "test_pass");


        //textview
        textview_names = findViewById(R.id.name_textview);

        //EditText
        edittext_name = findViewById(R.id.name_edittext);

        //Switchit
        Switch switch_hellas_elysiym = findViewById(R.id.switch_elysium);
        Switch switch_corporate_era = findViewById(R.id.switch_corporate_era);
        Switch switch_prelude = findViewById(R.id.switch_prelude);
        Switch switch_colonies = findViewById(R.id.switch_colonies);
        Switch switch_venus = findViewById(R.id.switch_venus);
        Switch switch_turmoil = findViewById(R.id.switch_turmoil);
        Switch switch_extra_corporations = findViewById(R.id.switch_extra_corporations);

        //switchien listenerit, vaihtaa booleaneja
        switch_hellas_elysiym.setOnCheckedChangeListener((buttonView, isChecked) -> hellas_elysium = isChecked);
        switch_corporate_era.setOnCheckedChangeListener((buttonView, isChecked) -> corporate_era = isChecked);
        switch_prelude.setOnCheckedChangeListener((buttonView, isChecked) -> prelude = isChecked);
        switch_colonies.setOnCheckedChangeListener((buttonView, isChecked) -> colonies = isChecked);
        switch_venus.setOnCheckedChangeListener((buttonView, isChecked) -> venus = isChecked);
        switch_turmoil.setOnCheckedChangeListener((buttonView, isChecked) -> turmoil = isChecked);
        switch_extra_corporations.setOnCheckedChangeListener((buttonView, isChecked) -> extra_corporations = isChecked);

        //buttonit
        ImageButton button_add = findViewById(R.id.imageButton);
        Button button_start = findViewById(R.id.button_start);

        //nappien listenerit
        button_add.setOnClickListener(v -> enterName());
        button_start.setOnClickListener(v -> startInGameUI());

        //näppäimistön enterin listener
        //TODO korjaa tää
        edittext_name.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                enterName();
                return true;
            }
            return false;
        });
    }

    private void enterName() {
        String name = edittext_name.getText().toString().trim();
        if (name.length() == 0)
            return;

        if (player_names.contains(name)) {
            Toast.makeText(getApplicationContext(), "Use unique names!", Toast.LENGTH_SHORT).show();
            return;
        }

        player_names.add(name);
        textview_names.append("\n" + name);
        edittext_name.setText("");
    }

    public void startInGameUI() {
        if (player_names.size() == 0) {
            Toast.makeText(getApplicationContext(), "Enter names first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Game game = new Game(player_names,
                corporate_era, prelude, colonies, venus, turmoil, extra_corporations, false, false, true, false,
                map);

        GameController.makeInstance(game);

        //piilottaa näppäimistön
        Context c = getBaseContext();
        View v = edittext_name.findFocus();
        if (v == null)
            return;
        InputMethodManager inputManager = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        ////

        Intent intent = new Intent(this, InGameUI.class);
        startActivity(intent);
    }


}
