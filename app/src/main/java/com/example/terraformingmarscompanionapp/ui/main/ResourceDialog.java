package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceDialog
{
    //context oli joka tapauksessa aikaisemmin "this" ingameui:ssa
    public void DisplayDialog(Context context, Card card)
    {
        GameController controller = GameController.getInstance();
        Game game = controller.getGame();

        List<Player> players = controller.getPlayers();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_resource, null);

        //visuaalinen muokkaus
        //view.setBackgroundColor(Color.TRANSPARENT);

            //linearlayoutin juttujen poistaminen
        LinearLayout root = view.findViewById(R.id.dialog_root_layout);

        //TODO, eetu: ehdot poistoille

        //root.removeView(root.findViewById(R.id.dialog_credit_layout));

        if(!card.getTags().contains(Tag.BUILDING))
            root.removeView(root.findViewById(R.id.dialog_steel_layout));

        if(!card.getTags().contains(Tag.SPACE))
            root.removeView(root.findViewById(R.id.dialog_titanium_layout));

        if(!controller.getCurrentPlayer().getHeatIsMoney())
            root.removeView(root.findViewById(R.id.dialog_heat_layout));

        //root.removeView(root.findViewById(R.id.dialog_plant_layout));

        //root.removeView(root.findViewById(R.id.dialog_floater_layout));

        //findviewbyid't
            //
            //

        AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();

        dialog.show();

        view.findViewById(R.id.resource_decline_button).setOnClickListener(v -> {
            dialog.dismiss();
        });

        view.findViewById(R.id.resource_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO oston tarkistus
                //TODO oston vahvistus
            }
        });
    }


    /*
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
        resource_dialog.setContentView(R.layout.dialog_resource);
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

     */
}
