package org.haraldfw.sudo_ku.board;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.haraldfw.sudo_ku.R;
import org.haraldfw.sudo_ku.board.components.BoardLayout;
import org.haraldfw.sudo_ku.board.components.NumberPickerFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class BoardCreateActivity extends AppCompatActivity implements NumberPickerFragment.OnFragmentInteractionListener {

    private static final String TAG = "BoardCreateActivity";
    private BoardLayout board;

    private boolean initialValuesFilled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_create);
        board = findViewById(R.id.sudoku_board);

        NumberPickerFragment numberPicker =
                (NumberPickerFragment) getFragmentManager().findFragmentById(R.id.numberpicker);

        board.setNumberPicker(numberPicker);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void saveBoard(View view) {
        if(!initialValuesFilled) {
            initialValuesFilled = true;
            board.lockTiles();
            Button button = findViewById(R.id.save_button);
            button.setText(R.string.label_button_save);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_difficulty);
        builder.setItems(R.array.difficulty_names, (dialog, which) -> difficultySelected(which));
        builder.show();
    }

    public void difficultySelected(int which){
        String boardJson = board.toJson(which);
        saveFile(String.valueOf(which), boardJson);
        Log.i(TAG, "saveBoard: " + boardJson);
        Log.i(TAG, "difficultySelected: " + Arrays.toString(fileList()));
        finish();
    }

    public void cancelBoard(View view) {
        finish();
    }

    private void saveFile(String fileNamePrefix, String contents) {
        String filename =
                fileNamePrefix + "-" + String.valueOf(System.currentTimeMillis()) + ".json";
        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(contents.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
