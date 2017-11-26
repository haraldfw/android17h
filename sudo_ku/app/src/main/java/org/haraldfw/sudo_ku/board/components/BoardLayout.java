package org.haraldfw.sudo_ku.board.components;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Harald on 25.11.2017.
 */

public class BoardLayout extends GridLayout {

    private static final String TAG = "BoardLayout";

    public TileView[][] tileViews;

    public BoardLayout(Context context) {
        super(context);
        init(context);
    }

    public BoardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BoardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public BoardLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        Log.d(TAG, "init: called");
        setColumnCount(9);
        setRowCount(9);

        tileViews = new TileView[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileView tile = new TileView(context, i, j, null);
                tileViews[i][j] = tile;
                addView(tile);
            }
        }
    }

    public void setTilesFromJson(Map map) {
        List tiles = (List) map.get("tiles");

        for (Object t :
                tiles) {
            Map tile = (Map) t;
            int col = (Integer) tile.get("column");
            int row = (Integer) tile.get("row");
            boolean locked = (Boolean) tile.get("locked");
            String value = (String) tile.get("value");
            String correct = (String) tile.get("correct");
            tileViews[row][col].setValues(value, locked, correct);
        }
    }

    public void lockTiles(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tileViews[i][j].lock();
            }
        }
    }

    public void setNumberPicker(NumberPickerFragment numberPicker) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tileViews[i][j].setNumberPicker(numberPicker);
            }
        }
    }

    public String toJson(int difficulty) {
        Map<String, Object> map = new HashMap<>();

        map.put("difficulty", difficulty);

        List<Map> tiles = new ArrayList<>();
        map.put("tiles", tiles);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileView t = tileViews[i][j];

                Map m = t.toJson();
                if(m != null) {
                    tiles.add(m);
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isSolved() {
        boolean solved = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileView t = tileViews[i][j];
                if (!t.isSolved()) {
                    solved = false;
                }
            }
        }
        return solved;
    }
}
