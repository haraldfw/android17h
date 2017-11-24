package org.haraldfw.oving6client;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

public class Client extends Thread {
    private final static String TAG = "Client";
    private final static String IP = "localhost";
    private final static int PORT = 12345;


    private WeakReference<TextView> answerView;

    private Socket s = null;
    private PrintWriter out = null;

    private final AtomicReference<Thread> currentThread = new AtomicReference<>();

    public Client(WeakReference<TextView> answerView) {
        this.answerView = answerView;
    }

    public void run() {

        currentThread.set(Thread.currentThread());
        BufferedReader in = null;

        try {
            s = new Socket(IP, PORT);
            log("run: C: Connected to server: " + s.toString());
            out = new PrintWriter(s.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));

//            out.println("PING to server from client");

            while (!Thread.currentThread().isInterrupted()) {
                if (in.ready()) {
                    String res = in.readLine();
                    log(String.valueOf(res));
                }
            }
            log("run: finished, stopping naturally");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
                s.close();
            } catch (Exception e) {
            }
        }
    }

    public void sendQuestion(int a, int b) {
        if (out == null) {
            log("Error, connection not started");
            return;
        }
        final String req = a + "," + b;
//        log("Sending question to server: " + req);
        new Thread(new Runnable() {
            @Override
            public void run() {
                out.println(req);
            }
        }).start();

    }

    public void stopClient() {
        new Thread() {
            @Override
            public void run() {

                out.println("q");
                log("stopServer: Stopping");
                try {
                    s.close();
                } catch (Exception e) {
                    log("stopServer: " + e.getMessage());
                }
                currentThread.get().interrupt();
                log("stopServer: Stopped");
            }
        }.start();
    }

    private void log(final String msg) {
        answerView.get().post(new Runnable() {
            @Override
            public void run() {
                answerView.get().setText(msg);
            }
        });
        Log.d(TAG, msg);
    }
}
