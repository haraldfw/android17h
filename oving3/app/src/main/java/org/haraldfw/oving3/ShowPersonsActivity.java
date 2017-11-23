package org.haraldfw.oving3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ShowPersonsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_persons);
    }

    public void onNewUserButtonClicked(View view) {
        EditText editTextName = findViewById(R.id.editText_person_name);
        EditText editTextBirthdate = findViewById(R.id.editText_person_birthdate);
        String name = editTextName.getText().toString();
        String birthdate = editTextBirthdate.getText().toString();

        if (name.isEmpty() || birthdate.isEmpty()) {
            Toast.makeText(this, "Enter info", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("newName", name);
            intent.putExtra("newBirthdate", birthdate);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onCancelButtonClicked(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
