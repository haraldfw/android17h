package org.haraldfw.oving5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.haraldfw.oving5.requests.SendGuess;
import org.haraldfw.oving5.requests.SendStart;

import java.lang.ref.WeakReference;

public class GuessActivity extends AppCompatActivity {

    private String name;
    private String card;

    private TextView gameStatusView;
    private EditText inputGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        name = getIntent().getStringExtra("name");
        card = getIntent().getStringExtra("card");

        gameStatusView = findViewById(R.id.game_status);
        inputGuess = findViewById(R.id.input_guess);

        sendGetStart();
    }

    private void sendGetStart() {
        new SendStart(new WeakReference<>(gameStatusView)).execute(name, card);
    }

    public void onClickSendGuess(View view) {
        new SendGuess(new WeakReference<>(gameStatusView)).execute(inputGuess.getText().toString());
    }

    public void startOver(View view) {
        finish();
    }
}
