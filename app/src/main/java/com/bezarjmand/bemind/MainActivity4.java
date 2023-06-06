package com.bezarjmand.bemind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

    Button backToMainButton= findViewById(R.id.backToMainButton);

    // Set click listener for the "Back" button
       backToMainButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Navigate back to MainActivity3
            onBackPressed();
        }
    });
    }
}