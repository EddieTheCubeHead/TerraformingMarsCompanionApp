package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;
import com.example.terraformingmarscompanionapp.webSocket.WebSocketHandler;

public class LoginActivity extends AppCompatActivity
{
    EditText username_field;
    EditText password_field;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation);

        username_field = findViewById(R.id.edittext_name_login);
        password_field = findViewById(R.id.edittext_password_login);

        login_button = findViewById(R.id.button_login);

        login_button.setOnClickListener(v -> login());
    }

    private void login()
    {
        String username =  username_field.getText().toString().trim();
        String password = password_field.getText().toString().trim();

        UserActions.loginUser(username, password);

        if (UserActions.successful_login)
            super.onBackPressed(); //ei-overridattu back-napin painallus

        String login_message = UserActions.message;
        toast(login_message);
    }

    private void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void onBackPressed() {} //back -nappi ei tee nyt mitään.
}
