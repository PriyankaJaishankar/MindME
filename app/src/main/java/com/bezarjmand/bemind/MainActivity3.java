package com.bezarjmand.bemind;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private RadioGroup moodRadioGroup;
    private Button applyButton;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        moodRadioGroup = findViewById(R.id.moodRadioGroup);
        applyButton = findViewById(R.id.applyButton);
        resultTextView = findViewById(R.id.resultTextView);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = moodRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                if (selectedRadioButton != null) {
                    String optionText = selectedRadioButton.getText().toString();
                    displayInfo(optionText);
                }
            }
        });
    }

    private void displayInfo(String optionText) {
        String info = "Happy"; // Add your information here based on the selected optionText
        resultTextView.setText(info);
        resultTextView.setVisibility(View.VISIBLE);
    }
}
