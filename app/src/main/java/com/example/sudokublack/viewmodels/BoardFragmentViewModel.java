package com.example.sudokublack.viewmodels;





import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sudokublack.models.Box;
import com.example.sudokublack.repositories.BoxRepository;
import com.example.sudokublack.repositories.SelectedValueRepository;
import com.example.sudokublack.utils.BoxState;

import java.util.List;

public class BoardFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Box>> mBoxes;
    private BoxRepository mRepo;
    private MutableLiveData<Integer> mSelectedValue;
    private SelectedValueRepository mRepoSel;
    private int prevValue = 0;

    public void init(Context context) {
        if(mBoxes != null) {
            return;
        }
        mRepoSel = SelectedValueRepository.getInstance();
        mSelectedValue = mRepoSel.getSelectedValue();
        mRepo = BoxRepository.getInstance();
        mBoxes = mRepo.getBoxes(context);
    }

    public void onBoardTouch(int column, int row, boolean isNote) {
        List<Box> currentBoxes = mBoxes.getValue();
        assert currentBoxes != null;
        if (currentBoxes.get(column + row * 9).getState() != BoxState.initialBase) {
            BoxState state;
            if (getSelectedValue().getValue() == 0) {
                state = BoxState.empty;
            } else if (isNote) {
                state = BoxState.note;
            } else state = BoxState.initialBase;
            changeBoxValue(column, row, state, currentBoxes);
        }
    }

    public void onButtonClick(int value) {
        prevValue = mSelectedValue.getValue();
        mSelectedValue.postValue(value);
    }

    public LiveData<List<Box>> getBoxes() {
        return mBoxes;
    }

    public LiveData<Integer> getSelectedValue() {
        return mSelectedValue;
    }

    public int getPrevValue() {
        return prevValue;
    }

    private void changeBoxValue(int column, int row, BoxState state, List<Box> currentBoxes) {
        if (currentBoxes.get(column + row * 9).getState() != BoxState.initialBase) {
            currentBoxes.set(column + row * 9,
                    new Box(column, row, getSelectedValue().getValue(), state));
            mBoxes.postValue(currentBoxes);
        }

    }
}
