package org.haraldfw.sudoq;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String langCode = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("language", "en");
        if (!langCode.equals("system")) {
            Locale locale = new Locale(langCode);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }

        setContentView(R.layout.activity_main);

        if(fileList().length == 0) {
            saveJsonFile(R.raw.d0_0, "0-0.json");
            saveJsonFile(R.raw.d1_0, "1-0.json");
            saveJsonFile(R.raw.d2_0, "2-0.json");
        }
    }

    private void saveJsonFile(int fileId, String filename) {
        String json = "{}";
        try {
            InputStream fis = getResources().openRawResource(fileId);
            json = new BufferedReader(new InputStreamReader(fis)).lines().collect(Collectors.joining("\n"));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "saveJsonFile: Saved file named " + filename);
    }

    public void startCreateBoard(View view) {
        Intent intent = new Intent(".BoardCreateActivity");
        startActivity(intent);
    }

    public void startSettings(View view) {
        Intent intent = new Intent(".SettingsActivity");
        startActivity(intent);
    }

    public void startSolve(View view) {
        Intent intent = new Intent(".board.BoardSolveActivity");
        startActivity(intent);
    }

    public void startInstructions(View view) {
        Intent intent = new Intent(".InstructionsActivity");
        startActivity(intent);
    }
}
