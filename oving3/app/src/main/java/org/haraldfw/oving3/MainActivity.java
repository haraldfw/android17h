package org.haraldfw.oving3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.haraldfw.oving3.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        persons.add(new Person("Harald Wilhe", "19. Apr 1995"));
        persons.add(new Person("Epoch", "1. Jan 1970"));
        init();
    }

    void init() {
        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, persons);
        Spinner spinner = findViewById(R.id.spinner_persons);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewName = findViewById(R.id.text_view_name);
                textViewName.setText(persons.get(position).getName());
                TextView textViewBirthdate = findViewById(R.id.text_view_birthdate);
                textViewBirthdate.setText(persons.get(position).getBirthdate());

                EditText editTextName = findViewById(R.id.editText_name);
                editTextName.setText("");
                EditText editTextBirthdate = findViewById(R.id.editText_birthdate);
                editTextBirthdate.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("newName");
            String birthdate = data.getStringExtra("newBirthdate");
            persons.add(new Person(name, birthdate));
        }
    }

    public void onAddPersonButtonClick(View view) {
        Intent intent = new Intent("org.haraldfw.oving3.ShowPersonsActivity");
        startActivityForResult(intent, 1);
    }

    public void onEditPersonButtonClick(View view) {
        findViewById(R.id.grid_layout_change_person).setVisibility(View.VISIBLE);
        findViewById(R.id.button_dismiss_changes).setVisibility(View.VISIBLE);
        findViewById(R.id.button_change_person).setVisibility(View.INVISIBLE);
    }

    public void onSaveChangesButtonClick(View view) {
        Spinner spinner = findViewById(R.id.spinner_persons);
        Person selectedUser = (Person) spinner.getSelectedItem();
        EditText editTextName = findViewById(R.id.editText_name);
        EditText editTextBirthdate = findViewById(R.id.editText_birthdate);

        String newName = editTextName.getText().toString();
        if (!newName.isEmpty()) {
            selectedUser.setName(newName);
            TextView textViewName = findViewById(R.id.text_view_name);
            textViewName.setText(newName);
        }

        String newBirthDate = editTextBirthdate.getText().toString();
        if (!newBirthDate.isEmpty()) {
            selectedUser.setBirthdate(newBirthDate);
            TextView textViewBirthdate = findViewById(R.id.text_view_birthdate);
            textViewBirthdate.setText(newBirthDate);
        }

        findViewById(R.id.grid_layout_change_person).setVisibility(View.INVISIBLE);
        findViewById(R.id.button_dismiss_changes).setVisibility(View.INVISIBLE);
        findViewById(R.id.button_change_person).setVisibility(View.VISIBLE);

        Toast.makeText(this, "Changes saved", Toast.LENGTH_LONG).show();
    }

    public void onDismissChangesButtonClick(View view) {
        EditText editTextName = findViewById(R.id.editText_name);
        editTextName.setText("");
        EditText editTextBirthdate = findViewById(R.id.editText_birthdate);
        editTextBirthdate.setText("");

        findViewById(R.id.grid_layout_change_person).setVisibility(View.INVISIBLE);
        findViewById(R.id.button_dismiss_changes).setVisibility(View.INVISIBLE);
        findViewById(R.id.button_change_person).setVisibility(View.VISIBLE);
    }
}
