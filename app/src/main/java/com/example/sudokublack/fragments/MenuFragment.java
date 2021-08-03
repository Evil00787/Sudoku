package com.example.sudokublack.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sudokublack.R;
import com.example.sudokublack.utils.FragmentChangeListener;

public class MenuFragment extends Fragment {

    private static final String TAG = "MenuFragment";

    @Override
    public String toString() {
        return TAG;
    }

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_menu, container, false);
        Button mButton = (Button) rootView.findViewById(R.id.gameButton);
        mButton.setOnClickListener(v ->
               showBoardFragment());
        return rootView;
    }

    public void showBoardFragment()
    {
        Fragment fr= new BoardFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }
}