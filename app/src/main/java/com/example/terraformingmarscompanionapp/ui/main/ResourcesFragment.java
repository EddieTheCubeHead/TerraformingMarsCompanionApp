package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.terraformingmarscompanionapp.R;

public class ResourcesFragment extends Fragment {

    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
            )
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_resources, container, false);

        return rootView;
    }
}
