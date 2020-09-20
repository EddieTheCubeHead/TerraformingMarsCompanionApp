package com.example.terraformingmarscompanionapp.ui.main.tilemap;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TileMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//Fragment for the map UI element rewrite
public class TileMapFragment extends Fragment {
    private GLSurfaceView gLView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TileMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TileMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TileMapFragment newInstance(String param1, String param2) {
        TileMapFragment fragment = new TileMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        TileMapSurfaceView gLView = new TileMapSurfaceView(getActivity());
        return gLView;
    }

    class TileMapSurfaceView extends GLSurfaceView {

        private final TileMapRender renderer;

        public TileMapSurfaceView(Context context){
            super(context);

            // Create an OpenGL ES 2.0 context
            setEGLContextClientVersion(2);
            renderer = new TileMapRender(context);

            // Set the Renderer for drawing on the GLSurfaceView
            setRenderer(renderer);
            setRenderMode(TileMapSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
    }
}
