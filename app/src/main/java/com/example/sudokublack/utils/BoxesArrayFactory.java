package com.example.sudokublack.utils;

import android.content.Context;

import androidx.core.util.Pair;

import com.example.sudokublack.R;
import com.example.sudokublack.models.Box;

import java.util.ArrayList;
import java.util.Random;

public class BoxesArrayFactory{
    private static DifficultyLevels level;
    private static BoxesArrayFactory instance;
    private static String sudokuSolution;
    private static String sudoku;

    public static BoxesArrayFactory getInstance(Context context) {
        CsvReader.getInstance(context);
        return instance;
    }

    public static ArrayList<Box> getBoxArrayList(Context context) {
        Pair<String, String> values = CsvReader.getSudoku();
        sudoku = values.first;
        sudokuSolution = values.second;
        if (level == null)
            try {
                throw new Exception("BoxesArrayFactory difficulty level not set.");
            } catch (Exception e) {
                e.printStackTrace();
                level = DifficultyLevels.easy;
            }
        ArrayList<Box> boxArrayList = new ArrayList<>();
        for(int i=0; i<9;i++) {
            for (int j=0; j<9;j++) {
                boxArrayList.add(getBox(i, j, sudoku.charAt(i+ 9*j)));
            }
        }
        return boxArrayList;
    }

    private static Box getBox(int column, int row, char value) {
        int valueInt = Character.getNumericValue(value);
        BoxState state;

        if (valueInt == 0) state = BoxState.empty;
        else state = BoxState.initialBase;
        return new Box(column, row, valueInt, state);
    }


    //TODO: add difficulty level
    private static boolean shouldAddNumber(Context context) {
        int random = new Random().nextInt(101);
        int id;
        if (level == DifficultyLevels.easy)
            id = R.array.easy;
        else if (level == DifficultyLevels.medium)
            id = R.array.medium;
        else if (level == DifficultyLevels.hard)
            id = R.array.hard;
        else
            id = R.array.master;
        int[] vals = context.getResources().getIntArray(id);
        return random < vals[0];
    }
}
