package org.haraldfw.oving2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";
    private final int REQUEST_CODE = 1;
    private int randval;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.textView);
        view.setEnabled(true);
        Intent intent = new Intent("org.haraldfw.oving2.RandActivity");
        intent.putExtra("UpperRandLimit", 200);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            randval = data.getIntExtra("Randomnum", -1);
            view.setText("Random verdi: " + randval);
        }
    }
}
