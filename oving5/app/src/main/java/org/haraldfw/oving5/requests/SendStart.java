package org.haraldfw.oving5.requests;

import android.widget.TextView;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.ref.WeakReference;

/**
 * Created by Harald on 23.11.2017.
 */

public class SendStart extends BaseRequestTask {

    public SendStart(WeakReference<TextView> reference) {
        super(reference);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            return Unirest.get(GAME_URL)
                    .field("navn", objects[0])
                    .field("kortnummer", objects[1])
                    .asString()
                    .getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
