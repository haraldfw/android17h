package org.haraldfw.oving2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class RandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        oppg b
        int upperlimit = getIntent().getIntExtra("UpperRandLimit", 100);
        int randnum = rand(0, upperlimit);
//        oppg c
        Intent intent = new Intent();
        intent.putExtra("Randomnum", randnum);
        setResult(RESULT_OK, intent);
//        oppg a
//        Toast t = Toast.makeText(this, "Randnum " + randnum, Toast.LENGTH_LONG);
//        t.show();
        finish();
    }

    private int rand(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
