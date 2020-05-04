package com.example.terraformingmarscompanionapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

public class LoginActivity extends AppCompatActivity
{
    EditText username_field;
    EditText password_field;
    Button login_button;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_field = findViewById(R.id.edittext_name_login);
        password_field = findViewById(R.id.edittext_password_login);

        Intent intent = getIntent();
        state = intent.getStringExtra(TitleScreen.EXTRA_MESSAGE);

        login_button = findViewById(R.id.button_login);
        login_button.setText(state);
    }

    public void loginFromData(View view)
    {
        String username =  username_field.getText().toString().trim();
        String password = password_field.getText().toString().trim();

        if (state.equals("Log in")) {
            UserActions.loginUser(username, password);
        } else {
            UserActions.createUser(username, password);
        }

        if (UserActions.successful_login)
            super.onBackPressed(); //ei-overridattu back-napin painallus

        String login_message = UserActions.message;
        toast(login_message);

        Intent intent = new Intent(this, TitleScreen.class);
        startActivity(intent);
    }

    private void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void onBackPressed() {} //back -nappi ei tee nyt mitään.
}
