package org.haraldfw.oving5.requests;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Harald on 23.11.2017.
 */

public  abstract class BaseRequestTask extends AsyncTask {

    protected static final String GAME_URL = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";

    private WeakReference<TextView> weakStatusView;

    public BaseRequestTask(WeakReference<TextView> reference) {
        weakStatusView = reference;
    }

    @Override
    protected void onPostExecute(Object o) {
        weakStatusView.get().setText((String) o);
    }
}
