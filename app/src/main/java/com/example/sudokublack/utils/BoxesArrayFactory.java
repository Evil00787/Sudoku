package com.example.sudokublack.utils;

import android.content.Context;

import androidx.core.util.Pair;

import com.example.sudokublack.models.Box;

import java.util.ArrayList;
import java.util.Random;

public class BoxesArrayFactory{
    private static DifficultyLevels level;
    private static String sudokuSolution;
    private static String sudoku;

    public static void init(Context context) {
        CsvReader.init(context);
    }

    public static ArrayList<Box> getBoxArrayList(Context context) {
        Pair<String, String> values = CsvReader.getSudoku();
        sudoku = values.first;
        sudokuSolution = values.second;

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
        return true;
    }
}
