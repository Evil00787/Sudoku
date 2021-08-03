package com.example.sudokublack.fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sudokublack.R;
import com.example.sudokublack.models.Box;
import com.example.sudokublack.ui.Board;
import com.example.sudokublack.viewmodels.BoardFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFragment extends Fragment {

    @Override
    public String toString() {
        return TAG;
    }

    private static final String TAG = "BoardFragment";

    private BoardFragmentViewModel mBoardFragmentViewModel;
    private Button[] buttons;
    private Board board = null;



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
        Board.init(getContext(), (int c, int r, boolean note) -> mBoardFragmentViewModel.onBoardTouch(c,r,note));

        mBoardFragmentViewModel.init(getContext());

        mBoardFragmentViewModel.getBoxes().observe(this, new Observer<List<Box>>() {
            @Override
            public void onChanged(List<Box> boxes) {
                if(board != null) {
                    board.setBoxes(boxes);
                }
            }
        });

        mBoardFragmentViewModel.getSelectedValue().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                buttons[mBoardFragmentViewModel.getPrevValue()].setBackgroundColor(getContext().
                        getResources().getColor(R.color.accent_color));
                buttons[integer].setBackgroundColor(getContext().
                        getResources().getColor(R.color.dark_color));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);
        initButtons(viewGroup);
        board = viewGroup.findViewById(R.id.board);
        board.setBoxes(mBoardFragmentViewModel.getBoxes().getValue());
        return viewGroup;
    }

    private void initButtons(ViewGroup viewGroup) {
        buttons = new Button[]{
                (Button) viewGroup.findViewById(R.id.mButtonX),
                (Button) viewGroup.findViewById(R.id.mButton1),
                (Button) viewGroup.findViewById(R.id.mButton2),
                (Button) viewGroup.findViewById(R.id.mButton3),
                (Button) viewGroup.findViewById(R.id.mButton4),
                (Button) viewGroup.findViewById(R.id.mButton5),
                (Button) viewGroup.findViewById(R.id.mButton6),
                (Button) viewGroup.findViewById(R.id.mButton7),
                (Button) viewGroup.findViewById(R.id.mButton8),
                (Button) viewGroup.findViewById(R.id.mButton9)
        };
        for (Button b: buttons) {
            b.setOnClickListener(v -> onButtonClicked(b.getText().toString()));
        }
    }

    private void onButtonClicked(String text) {
        int value;
        if (text.equals("X")) value = 0;
        else value = Integer.parseInt(text);
        mBoardFragmentViewModel.onButtonClick(value);
    }
}