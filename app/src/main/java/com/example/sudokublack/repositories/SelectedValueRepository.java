package com.example.sudokublack.repositories;

import androidx.lifecycle.MutableLiveData;


public class SelectedValueRepository {
    private static SelectedValueRepository instance;

    private Integer selectedValue;

    public static SelectedValueRepository getInstance() {
        if(instance == null)
            instance = new SelectedValueRepository();
        return instance;
    }

    public MutableLiveData<Integer> getSelectedValue() {
        setSelectedValue();
        MutableLiveData<Integer> data = new MutableLiveData<>();
        data.setValue(selectedValue);
        return data;
    }

    private void setSelectedValue() {
        selectedValue = 0;
    }
}
