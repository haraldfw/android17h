package org.haraldfw.sudo_ku;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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

        for (String s :
                fileList()) {
            deleteFile(s);
        }
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
}
