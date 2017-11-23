package org.haraldfw.oving2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "CalculatorActivity";
    private TextView textNum1;
    private TextView textNum2;
    private EditText inputAns;
    private EditText inputLim;
    private Button addBut;
    private Button mulBut;
    private final int requestCode1 = 1;
    private final int requestCode2 = 2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initFields();
        intent = new Intent("org.haraldfw.oving2.RandActivity");
        intent.putExtra("UpperRandLimit", 200);
        addBut.setOnClickListener(this);
        mulBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int in1 = Integer.parseInt(textNum1.getText().toString());
        int in2 = Integer.parseInt(textNum2.getText().toString());

        int correctAns;
        int i = view.getId();
        if (i == R.id.multiplyBut) {
            correctAns = in1 * in2;
        } else if (i == R.id.addBut) {
            correctAns = in1 + in2;
        } else {
            throw new IllegalStateException("Invalid button pressed");
        }

        int input = Integer.parseInt(inputAns.getText().toString());
        Log.i(TAG, "Input: " + input);
        String toastText;
        if (input == correctAns) {
            toastText = getString(R.string.correct);
        } else {
            toastText = getString(R.string.incorrect) + " " + correctAns;
        }
        Toast.makeText(getBaseContext(), toastText, Toast.LENGTH_LONG).show();
        intent.putExtra("UpperRandLimit", Integer.parseInt(inputLim.getText().toString()));
        updateFields(intent);
    }

    private void updateFields(Intent intent) {
        startActivityForResult(intent, requestCode1);
        startActivityForResult(intent, requestCode2);
    }

    private void initFields() {
        textNum1 = findViewById(R.id.textNum1);
        textNum1.setEnabled(true);
        textNum2 = findViewById(R.id.textNum2);
        textNum2.setEnabled(true);
        inputAns = findViewById(R.id.editAns);
        inputAns.setEnabled(true);
        inputLim = findViewById(R.id.editLim);
        inputLim.setEnabled(true);
        addBut = findViewById(R.id.addBut);
        addBut.setEnabled(true);
        mulBut = findViewById(R.id.multiplyBut);
        mulBut.setEnabled(true);
        TextView ansOut = findViewById(R.id.ledeAns);
        ansOut.setEnabled(true);
        TextView limOut = findViewById(R.id.ledeLim);
        limOut.setEnabled(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == requestCode1) {
                textNum1.setText(Integer.toString(data.getIntExtra("Randomnum", -1)));
            }
            if (requestCode == requestCode2) {
                textNum2.setText(Integer.toString(data.getIntExtra("Randomnum", -1)));
            }
        }
    }
}
