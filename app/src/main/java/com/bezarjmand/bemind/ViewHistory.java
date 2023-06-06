package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);

        // Get the mood list from the intent
        List<String> moodList = getIntent().getStringArrayListExtra("moodList");

        // Find the TextView for displaying the mood history
        TextView moodHistoryTextView = findViewById(R.id.moodHistoryTextView);

        // Create a StringBuilder to build the history string
        StringBuilder historyBuilder = new StringBuilder();

        // Iterate through the mood list and append each mood to the history string
        for (String mood : moodList) {
            historyBuilder.append(mood).append("\n");
        }

        // Set the history string to the TextView
        moodHistoryTextView.setText(historyBuilder.toString());

        // Find the "Further" button
        Button furtherToMainButton = findViewById(R.id.furtherToMainButton);

        //Set click listener for the further button
        furtherToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity2
                Intent intent = new Intent(ViewHistory.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // Find the "Back" button
        Button backToMoodSelectionButton = findViewById(R.id.backToMoodSelectionButton);

        // Set click listener for the "Back" button
        backToMoodSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity3
                onBackPressed();
            }
        });
    }
}

