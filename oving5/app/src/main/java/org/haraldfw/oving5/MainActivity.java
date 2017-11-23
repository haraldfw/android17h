package org.haraldfw.oving5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    EditText editCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.input_name);
        editCard = findViewById(R.id.input_card);
    }

    public void onStartClick(View view) {
        Intent intent = new Intent(".GuessActivity");
        intent.putExtra("name", editName.getText().toString());
        intent.putExtra("card", editCard.getText().toString());
        startActivity(intent);
    }
}
