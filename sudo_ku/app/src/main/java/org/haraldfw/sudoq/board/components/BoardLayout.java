package org.haraldfw.sudoq.board.components;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.haraldfw.sudoq.R;
import org.haraldfw.sudoq.board.BoardFileContants;

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

    private boolean checkForSolution = true;

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
        setBackground(new GridBackground(this));
        Log.d(TAG, "init: called");
        setColumnCount(9);
        setRowCount(9);

        tileViews = new TileView[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileView tile = new TileView(context, i, j, null, this);
                tileViews[i][j] = tile;
                addView(tile);
            }
        }
    }

    public void setTilesFromJson(Map map) {
        List tiles = (List) map.get(BoardFileContants.TILES);

        for (Object t :
                tiles) {
            Map tile = (Map) t;
            int col = (Integer) tile.get(BoardFileContants.COLUMN);
            int row = (Integer) tile.get(BoardFileContants.ROW);
            boolean locked = (Boolean) tile.get(BoardFileContants.LOCKED);
            String value = String.valueOf(tile.get(BoardFileContants.VALUE));
            tileViews[row][col].setValues(value, locked);
        }
    }

    public void lockTiles() {
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

    public String toJson() {
        Map<String, Object> map = new HashMap<>();
        List<Map> tiles = new ArrayList<>();
        map.put("tiles", tiles);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileView t = tileViews[i][j];

                Map m = t.toJson();
                if (m != null) {
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

    public boolean isFilled() {
        boolean filled = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TileView t = tileViews[i][j];
                if (!t.isFilled()) {
                    filled = false;
                }
            }
        }
        return filled;
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

    public void checkForSolved() {
        if (checkForSolution) {
            if (!isFilled()) {
                return;
            }
            int msgId;
            int titleId;
            if (isSolved()) {
                titleId = R.string.win_title;
                msgId = R.string.win_msg;
            } else {
                titleId = R.string.incorrect_title;
                msgId = R.string.incorrect_msg;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(titleId);
            builder.setMessage(msgId);
            builder.show();
        }
    }

    public void setCheckForSolution(boolean checkForSolution) {
        this.checkForSolution = checkForSolution;
    }
}
