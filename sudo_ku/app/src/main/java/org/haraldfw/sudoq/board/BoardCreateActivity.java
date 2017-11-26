package org.haraldfw.sudoq.board;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.haraldfw.sudoq.R;
import org.haraldfw.sudoq.board.components.BoardLayout;
import org.haraldfw.sudoq.board.components.NumberPickerFragment;

import java.io.FileOutputStream;
import java.io.IOException;

public class BoardCreateActivity extends AppCompatActivity implements NumberPickerFragment.OnFragmentInteractionListener {

    private static final String TAG = "BoardCreateActivity";
    private BoardLayout board;

    private boolean initialValuesFilled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_create);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        board = findViewById(R.id.sudoq_board);

        NumberPickerFragment numberPicker =
                (NumberPickerFragment) getFragmentManager().findFragmentById(R.id.numberpicker);

        board.setNumberPicker(numberPicker);
        board.setCheckForSolution(false);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void saveBoard(View view) {
        if (!initialValuesFilled) {
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

    public void difficultySelected(int which) {
        if (!board.isFilled()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Fill out all fields before saving", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        String boardJson = board.toJson();
        saveFile(String.valueOf(which), boardJson);
        Log.i(TAG, "saveBoard: " + boardJson);
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
