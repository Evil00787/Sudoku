package com.example.sudokublack.utils;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.core.util.Pair;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class CsvReader {
    private static final TreeMap<String, String> sudokus = new TreeMap<>();
    private static final ArrayList<String> sudokuKeys = new ArrayList<>();

    public static void init(Context context) {
        getCsv(context);
    }

    private static void getCsv(Context context) {
        String csvfileString = "sudoku_0.csv";
        AssetManager assetManager = context.getAssets();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(assetManager.open(csvfileString)));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                sudokus.put(nextLine[0], nextLine[1]);
            }
            sudokuKeys.addAll(sudokus.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pair<String, String> getSudoku() {
        String key = sudokuKeys.get(new Random().nextInt(100));
        return new Pair<>(key, sudokus.get(key));
    }
}
