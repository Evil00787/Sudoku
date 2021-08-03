package com.example.sudokublack.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.sudokublack.R;
import com.example.sudokublack.models.Box;
import com.example.sudokublack.utils.TouchCallbackInterface;

import java.util.List;

public class Board extends View {
    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private static final Paint thickLinePaint = new Paint();
    private static final Paint thinLinePaint = new Paint();
    private static final Paint textPaint = new Paint();
    private static TouchCallbackInterface callbackInterface;
    private static List<Box> boxes = null;

    private float cellSize = 0F;

    public static void init(Context context, TouchCallbackInterface callback) {
        thickLinePaint.setStyle(Paint.Style.STROKE);
        thickLinePaint.setColor(context.getResources().getColor(R.color.accent_color));
        thickLinePaint.setStrokeWidth(5F);

        thinLinePaint.setStyle(Paint.Style.STROKE);
        thinLinePaint.setColor(context.getResources().getColor(R.color.accent_color));
        thinLinePaint.setStrokeWidth(2F);

        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setColor(context.getResources().getColor(R.color.accent_color));
        textPaint.setTextSize(30F);
        callbackInterface = callback;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int boardSize = Math.min(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(boardSize, boardSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        cellSize = (float)(getWidth() / 9);

        canvas.drawRect(0F, 0F, getWidth(), getBottom(), thickLinePaint);
        drawLines(canvas);
        if(boxes != null) drawNumbers(canvas);
    }

    private void drawLines(Canvas canvas) {
        for (int i=1; i<=9;i++) {
            Paint paint;
            if (i%3==0) {
                paint = thickLinePaint;
            } else {
                paint = thinLinePaint;
            }

            canvas.drawLine(i * cellSize, 0F, i * cellSize, (float) getHeight(), paint);
            canvas.drawLine(0F, i * cellSize, (float) getWidth(), i * cellSize, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            handleTouch(event.getX(), event.getY());
            return true;
        }
        return false;
    }

    private void handleTouch(float x, float y) {
        int selectedRow =(int) (y/cellSize);
        int selectedColumn =(int) (x/cellSize);
        callbackInterface.callback(selectedColumn, selectedRow, false);
    }

    public void setBoxes(List<Box> newBoxes) {
        boxes = newBoxes;
        invalidate();
    }

    private void drawNumbers(Canvas canvas) {
        for(Box b: boxes) {
            int value = b.getValue();
            if (value == 0)
                continue;
            int row = b.getRow();
            int column = b.getColumn();
            Rect textBounds = new Rect();
            String valueString = String.valueOf(value);
            textPaint.getTextBounds(valueString, 0, valueString.length(), textBounds);
            float textW = textPaint.measureText(valueString);
            float textH = textBounds.height();

            canvas.drawText(valueString, (column * cellSize) + cellSize / 2 - textW / 2,
                    (row * cellSize) + cellSize / 2 - textH / 2, textPaint);
        }
    }
}
