package org.haraldfw.sudoq.board.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import org.haraldfw.sudoq.R;
import org.haraldfw.sudoq.board.BoardFileContants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Harald on 25.11.2017.
 */

public class TileView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    private static final String TAG = "TileView";
    private final BoardLayout board;
    private String correctValue;
    private int row;
    private int column;
    private boolean locked = false;
    private boolean colored = false;

    private static final int margin = 5;

    private int defColor;

    private NumberPickerFragment numberPicker;

    public TileView(Context context, int row, int column, String value, BoardLayout board) {
        super(context);
        this.board = board;
        defColor = getTextColors().getDefaultColor();
        if (value != null && !value.isEmpty()) {
            setText(value);
            lock();
        }
        init(row, column);
    }

    public void setValues(String value, boolean locked) {
        if (locked) {
            setText(value);
            lock();
        }
        this.correctValue = value;
    }

    private void init(int row, int column) {
        this.row = row;
        this.column = column;
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                GridLayout.spec(row, 1f),
                GridLayout.spec(column, 1f)
        );
        params.setGravity(Gravity.FILL);
        params.setMargins(margin, margin, margin, margin);
        params.width = 0;
        setLayoutParams(params);
        setBackgroundColor(Color.WHITE);
        setGravity(Gravity.CENTER);
        setTextSize(30f);
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setOnClickListener(this);
        setBackground(getResources().getDrawable(R.drawable.rounded_corners, getContext().getTheme()));
    }

    public boolean isSolved() {
        return getText().toString().equals(correctValue);
    }

    public void setNumberPicker(NumberPickerFragment numberPicker) {
        this.numberPicker = numberPicker;
    }

    @Override
    public void onClick(View view) {
        if (locked) {
            return;
        }
        CharSequence nVal = numberPicker.getSelectedAction();
        if (nVal != null) {
            if (nVal.equals(getResources().getString(R.string.label_radio_erase))) {
                setText("");
            } else if (nVal.equals(getResources().getString(R.string.label_radio_color))) {
                toggleColor();
            } else {
                setText(nVal);
                board.checkForSolved();
            }
        }
    }

    private void toggleColor() {
        if (colored) {
            setTextColor(defColor);
        } else {
            setTextColor(Color.BLUE);
        }
        colored = !colored;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put(BoardFileContants.ROW, row);
        map.put(BoardFileContants.COLUMN, column);
        map.put(BoardFileContants.VALUE, Integer.parseInt(getText().toString()));
        map.put(BoardFileContants.LOCKED, locked);
        return map;
    }

    public void lock() {
        if (!getText().toString().isEmpty()) {
            locked = true;
            this.correctValue = getText().toString();
            setTypeface(null, Typeface.BOLD);
        }
    }

    public boolean isFilled() {
        return !getText().toString().isEmpty();
    }
}

