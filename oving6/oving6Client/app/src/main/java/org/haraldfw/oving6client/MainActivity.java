package org.haraldfw.oving6client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ClientActivity";

    private TextView inputA;
    private TextView inputB;

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputA = findViewById(R.id.input_a);
        inputB = findViewById(R.id.input_b);

        Log.d(TAG, "onCreate: starting client");
    }

    public void sendQuestion(View view) {
        Log.d(TAG, "sendQuestion: setting up question");
        client.sendQuestion(
                Integer.parseInt(inputA.getText().toString()),
                Integer.parseInt(inputB.getText().toString())
        );
    }

    public void startClient(View view) {
        TextView ans = findViewById(R.id.answer);
        client = new Client(new WeakReference<>(ans));
        client.start();
    }

    public void stopClient(View view) {
        Log.d(TAG, "stopClient: called");
        client.stopClient();
    }
}
