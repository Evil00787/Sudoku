package com.example.sudokublack.utils;

import android.content.Context;

import androidx.core.util.Pair;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class CsvReader {
    private static CsvReader instance;
    private static final TreeMap<String, String> sudokus = new TreeMap<>();
    private static final ArrayList<String> sudokuKeys = new ArrayList<>();

    public static CsvReader getInstance(Context context) {
        getCsv(context);
        return instance;
    }

    private static void getCsv(Context context) {
        String csvfileString =
                context.getApplicationInfo().dataDir + File.separatorChar + "sudoku_0.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(csvfileString));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                sudokus.put(nextLine[0], nextLine[1]);
            }
            sudokuKeys.addAll(sudokus.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pair<String, String> getSudoku() {
        String key = sudokuKeys.get(new Random().nextInt(10000));
        return new Pair<>(key, sudokus.get(key));
    }
}
