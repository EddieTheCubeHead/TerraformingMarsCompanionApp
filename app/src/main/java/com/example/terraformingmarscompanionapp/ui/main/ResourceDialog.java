package com.example.terraformingmarscompanionapp.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

public class ResourceDialog
{
    Dialog resource_dialog;

    RecyclerView popup_recyclerview;
    private ResourceChangerAdapter popup_adapter;

    //ui luokan konteksti

    public ResourceDialog (Context context, View view)
    {
        //dialogiin ja sen recyclerviewhin liittyvät
        popup_recyclerview = view.findViewById(R.id.popup_recyclerview);
        popup_recyclerview.setHasFixedSize(true);

        RecyclerView.LayoutManager popup_layout_manager = new LinearLayoutManager(context);
        popup_recyclerview.setLayoutManager(popup_layout_manager);

        view.findViewById(R.id.resource_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_adapter.confirmPurchase();
            }
        });

        resource_dialog = new Dialog(context);
        resource_dialog.setContentView(R.layout.activity_resource_dialog);
        resource_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void show(com.example.terraformingmarscompanionapp.cardSubclasses.Card card)
    {
        //TODO tähän recyclerviewn loaduppaaminen.
        //TODO kortin ostaminen monilla erilaisilla resursseilla
        //resourcepackage tai joku?

        //todo tähän juttujen listaesineiden laittaminen

        //noin vähäkoodisin tapa ladata popupin tiedot uudelleen jos laskee adapterin koodin.
        popup_adapter = new ResourceChangerAdapter(card);
        popup_recyclerview.setAdapter(popup_adapter);

        resource_dialog.show();
    }

}
