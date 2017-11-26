package org.haraldfw.sudo_ku.board.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import org.haraldfw.sudo_ku.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Harald on 25.11.2017.
 */

public class TileView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {

    private String correctValue;
    private int row;
    private int column;
    private boolean locked = false;

    private static final int margin = 10;

    private NumberPickerFragment numberPicker;

    public TileView(Context context, int row, int column, String value) {
        super(context);
        if (value != null && !value.isEmpty()) {
            setText(value);
            lock();
        }
        init(row, column);
    }

    public void setValues(String value, boolean locked, String correctValue) {
        setText(value);
        this.locked = locked;
        if(locked){
            lock();
        }
        this.correctValue = correctValue;
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
        setBackgroundColor(Color.WHITE);
        setLayoutParams(params);
        setGravity(Gravity.CENTER);
        setTextSize(25f);
        setOnClickListener(this);
        setBackground(getResources().getDrawable(R.drawable.rounded_corners, getContext().getTheme()));
    }

    public boolean isSolved() {
        return getText().equals(correctValue);
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
        setText(nVal == null ? "" : nVal.equals("E") ? "" : nVal);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TileView)) {
            return false;
        }
        TileView t = (TileView) obj;
        return t.getText().toString().equals(t.getText().toString())
                && correctValue.equals(t.correctValue);
    }

    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        String val = getText().toString();
        map.put("row", row);
        map.put("column", column);
        map.put("value", val);
        map.put("correct", val);
        map.put("locked", locked);
        return map;
    }

    public void lock() {
        if (!getText().toString().isEmpty()) {
            locked = true;
            this.correctValue = getText().toString();
            setTypeface(null, Typeface.BOLD);
        }
    }

}

