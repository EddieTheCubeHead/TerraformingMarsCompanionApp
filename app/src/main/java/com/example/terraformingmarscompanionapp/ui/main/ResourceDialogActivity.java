package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;

/**
 * Resurssienvaihtopopupin logiikka
 */
public class ResourceDialogActivity extends AppCompatActivity
{
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_dialog);

        findViewById(R.id.resource_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button clicked.");
            }
        });

        recycler_view = findViewById(R.id.popup_recyclerview);

    }
}
