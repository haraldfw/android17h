package no.hist.itfag.books;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {

    private EditText in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        in = (EditText) findViewById(R.id.input_color);
//        in.setText(PreferenceManager.getDefaultSharedPreferences(this)
//                .getString("backgroundColor", "#ffffff"));
    }

    public void onSavePress(View view) {
//        Log.d("Pref manager: ", "onSavePress: updating background color pref");
//        String sColor = in.getText().toString();
//
//        addPreferencesFromResource(R.xml.preferences);
//
//
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("backgroundColor", sColor);
//        editor.apply();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}
