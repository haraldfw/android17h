package org.haraldfw.sudo_ku.board;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.haraldfw.sudo_ku.R;
import org.haraldfw.sudo_ku.board.components.BoardLayout;
import org.haraldfw.sudo_ku.board.components.NumberPickerFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class BoardSolveActivity extends AppCompatActivity implements NumberPickerFragment.OnFragmentInteractionListener {

    private static final String TAG = "BoardSolve";
    private BoardLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_solve);
        board = findViewById(R.id.sudoku_board);

        NumberPickerFragment numberPicker =
                (NumberPickerFragment) getFragmentManager().findFragmentById(R.id.numberpicker);

        board.setNumberPicker(numberPicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_difficulty);
        builder.setItems(R.array.difficulty_names, (dialog, which) -> onDifficultySelected(which));
        builder.show();
    }

    private void onDifficultySelected(int which) {
        String[] fileList = fileList();
        Log.d(TAG, "onDifficultySelected: " + Arrays.toString(fileList));
        fileList = Arrays.stream(fileList).filter(s -> String.valueOf(which).equals(Character.toString(s.charAt(0)))).toArray(String[]::new);
        String filename = fileList[new Random().nextInt(fileList.length)];
        String json = "{}";
        try {
            FileInputStream fis = openFileInput(filename);
            json = new BufferedReader(new InputStreamReader(fis)).lines().collect(Collectors.joining("\n"));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
        ObjectMapper mapper = new ObjectMapper();

        Map map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        board.setTilesFromJson(map);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
