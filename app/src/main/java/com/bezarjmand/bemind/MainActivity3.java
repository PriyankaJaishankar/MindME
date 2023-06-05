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

    private RadioGroup radioGroup;
    private Button applyButton;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        radioGroup = findViewById(R.id.radioGroup);
        applyButton = findViewById(R.id.applyButton);
        resultTextView = findViewById(R.id.resultTextView);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                if (selectedRadioButton != null) {
                    String optionText = selectedRadioButton.getText().toString();
                    displayInfo(optionText);
                }
            }
        });
    }

    private void displayInfo(String optionText) {
        String info;
        switch (optionText) {
            case "happy":
                info = "Happiness is a choice, so choose to be happy.";
                break;
            case "sad":
                info = "Every storm eventually runs out of rain.";
                break;
            case "normal":
                info = "Embrace the ordinary moments, for they make up the fabric of life.";
                break;
            case "satisfied":
                info = "Satisfaction comes from within, celebrate your accomplishments.";
                break;
            case "calm":
                info = "In the midst of chaos, find your inner peace.";
                break;
            case "frustrated":
                info = "Obstacles are opportunities in disguise, keep pushing forward.";
                break;
            case "anxious":
                info = "Don't let worry steal your present, focus on the present moment.";
                break;
            case "angry":
                info = "Choose forgiveness over anger, and watch your burdens disappear.";
                break;
            case "lonely":
                info = "Reach out, for there are people who care and want to be there for you.";
                break;
            case "tired":
                info = "Rest when you're weary, but never give up on your dreams.";
                break;
            default:
                info = "Select an Option!";
                break;
        }

        resultTextView.setText(info);
        resultTextView.setVisibility(View.VISIBLE);
    }
}