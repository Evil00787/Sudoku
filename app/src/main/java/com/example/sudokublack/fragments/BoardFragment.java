package com.example.sudokublack.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sudokublack.R;
import com.example.sudokublack.models.Box;
import com.example.sudokublack.viewmodels.BoardFragmentViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFragment extends Fragment {

    private static final String TAG = "BoardFragment";

    private BoardFragmentViewModel mBoardFragmentViewModel;



    public BoardFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1
     * @return A new instance of fragment BoardFragment.
     */
    public static BoardFragment newInstance(String param1, String param2) {
        BoardFragment fragment = new BoardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        final ViewModelProvider viewModelProvider =
                new ViewModelProvider(this, factory);
        mBoardFragmentViewModel = viewModelProvider.get(BoardFragmentViewModel.class);

        mBoardFragmentViewModel.init(getContext());

        mBoardFragmentViewModel.getBoxes().observe(this, new Observer<List<Box>>() {
            @Override
            public void onChanged(List<Box> boxes) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }
}