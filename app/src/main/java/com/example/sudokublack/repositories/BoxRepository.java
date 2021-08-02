package com.example.sudokublack.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.sudokublack.models.Box;
import com.example.sudokublack.utils.BoxesArrayFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton pattern
 */

public class BoxRepository {
    private static BoxRepository instance;

    private ArrayList<Box> dataset = new ArrayList<>();
    public static BoxRepository getInstance() {
        if(instance == null)
            instance = new BoxRepository();
        return instance;

    }

    public MutableLiveData<List<Box>> getBoxes(Context context) {
        setBoxes(context);
        MutableLiveData<List<Box>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setBoxes(Context context) {
        dataset.addAll(BoxesArrayFactory.getBoxArrayList(context));
    }
}
