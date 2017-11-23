package org.haraldfw.oving1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String value = "Default/Harald";
                switch (checkedId) {
                    case R.id.radioButton1:
                        value = "Harald";
                        break;
                    case R.id.radioButton2:
                        value = "Wilhelmsen";
                        break;
                }
                System.out.println(value);
            }
        });
    }
}
