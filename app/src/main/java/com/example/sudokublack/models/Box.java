package com.example.sudokublack.models;


import androidx.annotation.NonNull;

import com.example.sudokublack.utils.BoxState;

public class Box {

    private int column;
    private int row;
    private int value;
    private @NonNull BoxState state;

    public Box(int column, int row, int value, @NonNull BoxState state) {
        this.column = column;
        this.row = row;
        this.value = value;
        this.state = state;
    }

    public @NonNull BoxState getState() {
        return state;
    }

    public void setState(@NonNull BoxState state) {
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
