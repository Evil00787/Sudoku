package com.example.sudokublack.viewmodels;





import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sudokublack.models.Box;
import com.example.sudokublack.repositories.BoxRepository;
import com.example.sudokublack.utils.BoxState;

import java.util.List;

public class BoardFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Box>> mBoxes;
    private BoxRepository mRepo;

    public void init(Context context) {
        if(mBoxes == null) {
            return;
        }
        mRepo = BoxRepository.getInstance();
        mBoxes = mRepo.getBoxes(context);
    }

    private void changeBoxValue(int column, int row, int value, BoxState state, List<Box> currentBoxes) {
        if (currentBoxes.get(column + row * 9).getState() != BoxState.initialBase)
            currentBoxes.set(column + row*9, new Box(column, row, value, state));
    }

    public void onBoardTouch(int column, int row, int value, boolean isNote) {
        List<Box> currentBoxes = mBoxes.getValue();
        assert currentBoxes != null;
        if (currentBoxes.get(column + row * 9).getState() != BoxState.initialBase) {
            BoxState state;
            if (value == 0) {
                state = BoxState.empty;
            } else if (isNote) {
                state = BoxState.note;
            } else state = BoxState.initialBase;
            changeBoxValue(column, row, value, state, currentBoxes);
        }
    }

    public LiveData<List<Box>> getBoxes() {
        return mBoxes;
    }
}
