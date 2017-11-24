package org.haraldfw.oving5.requests;

import android.widget.TextView;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.ref.WeakReference;

/**
 * Created by Harald on 23.11.2017.
 */

public class SendGuess extends BaseRequestTask {

    public SendGuess(WeakReference<TextView> reference) {
        super(reference);
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            System.out.println("Sending guess to server");
            return Unirest.get(GAME_URL)
                    .field("tall", objects[0])
                    .asString()
                    .getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
