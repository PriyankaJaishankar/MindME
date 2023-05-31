package com.bezarjmand.bemind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    public RadioGroup moodRadioGroup = findViewById(R.id.moodRadioGroup);
    public Button applyButton = findViewById(R.id.applyButton);
    public TextView resultTextView = findViewById(R.id.resultTextView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = moodRadioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedMood = selectedRadioButton.getText().toString();
                    String message = "You selected: " + selectedMood;
                    resultTextView.setText(message);
                } else {
                    resultTextView.setText("Please select a mood");
                }
            }
        });
    }
}