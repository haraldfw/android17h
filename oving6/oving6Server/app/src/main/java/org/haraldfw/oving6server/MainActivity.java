package org.haraldfw.oving6server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Server server;
    TextView statusField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusField = findViewById(R.id.status_view);
    }

    public void startServer(View view) {
        try {
            server.stopServer();
        } catch (Exception e) {
        }
        server = new Server(new WeakReference<>(statusField));
        server.start();
    }

    public void stopServer(View view) {
        server.stopServer();
    }
}
