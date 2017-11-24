package org.haraldfw.oving6server;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Harald on 23.11.2017.
 */

public class Server extends Thread {
    private final static String TAG = "Server";
    private final static int PORT = 12345;

    ServerSocket ss = null;

    Socket s = null;
    PrintWriter out = null;
    BufferedReader in = null;

    private final AtomicReference<Thread> currentThread = new AtomicReference<>();

    private WeakReference<TextView> statusField;

    public Server(WeakReference<TextView> statusField) {
        this.statusField = statusField;
    }

    public void run() {
        currentThread.set(Thread.currentThread());


        try {
            log("starting server....");
            ss = new ServerSocket(PORT);
            log("serversocket created, waiting for client....");
            while (!Thread.currentThread().isInterrupted()) {
                s = ss.accept();
                log("client connected...");
                out = new PrintWriter(s.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));

                log("run: sending welcome msg");
                out.println("Welcome client...");

                while (true) {
                    if (in.ready()) {
                        log("Listening for client input");
                        String req = in.readLine();
                        log("Message from client: " + req);
                        if (req == null) {
                            continue;
                        }
                        if (req.contains("q")) {
                            break;
                        }
                        String[] nums = req.split(",");
                        int response = Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]);
                        log("Returning to client: " + response);
                        out.println(response);
                    }
                }
                log("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            log("Closing");
            out.close();
            in.close();
            s.close();
            ss.close();
            log("Closed");
        } catch (Exception e) {
            log("Exception: " + e.getMessage());
        }
    }

    public void stopServer() {
        log("Stopping");
        closeAll();
        currentThread.get().interrupt();
        log("Stopped");
    }

    private void log(final String msg) {
        statusField.get().post(new Runnable() {
            @Override
            public void run() {
                statusField.get().setText(msg);
            }
        });
        Log.d(TAG, msg);
    }
}

