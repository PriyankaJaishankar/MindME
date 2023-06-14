package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private TextView selectedMoodTextView;
    private TextView moodInfoTextView;
    private List<String> selectedMoods;

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views by their IDs
        selectedMoodTextView = findViewById(R.id.selectedMoodTextView);
        moodInfoTextView = findViewById(R.id.moodInfoTextView);
        Button applyButton = findViewById(R.id.applyButton);
        Button viewHistoryButton = findViewById(R.id.viewHistoryButton);

        // Initialize the selected moods list
        selectedMoods = new ArrayList<>();

        // Set click listeners for the mood buttons
        findViewById(R.id.happyButton).setOnClickListener(this);
        findViewById(R.id.sadButton).setOnClickListener(this);
        findViewById(R.id.normalEmotionButton).setOnClickListener(this);
        findViewById(R.id.satisfiedButton).setOnClickListener(this);
        findViewById(R.id.calmButton).setOnClickListener(this);
        findViewById(R.id.frustratedButton).setOnClickListener(this);
        findViewById(R.id.anxiousButton).setOnClickListener(this);
        findViewById(R.id.angryButton).setOnClickListener(this);
        findViewById(R.id.lonelyButton).setOnClickListener(this);
        findViewById(R.id.tiredButton).setOnClickListener(this);
        findViewById(R.id.boredButton).setOnClickListener(this);
        findViewById(R.id.sleepyButton).setOnClickListener(this);

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected mood
                String selectedMood = selectedMoodTextView.getText().toString();

                // Add the selected mood to the list
                selectedMoods.add(selectedMood);
                // Insert the selected mood into the database
                databaseHelper.insertMood(selectedMood);

                // Display the corresponding message for the selected mood
                switch (selectedMood) {
                    case "Glücklich\uD83D\uDE04":
                        moodInfoTextView.setText("Happiness is a choice, so choose to be happy!");
                        break;
                    case "Traurig\uD83D\uDE22":
                        moodInfoTextView.setText("Every storm eventually runs out of rain!");
                        break;
                    case "Normal\uD83D\uDE10":
                        moodInfoTextView.setText("Embrace the ordinary moments, for they make up the fabric of life!");
                        break;
                    case "Zufrieden\uD83D\uDE0A":
                        moodInfoTextView.setText("Satisfaction is the stepping stone to contentment!");
                        break;
                    case "Ruhig\uD83D\uDE0C":
                        moodInfoTextView.setText("Find calmness within yourself amidst the chaos!");
                        break;
                    case "Frustriert\uD83D\uDE20":
                        moodInfoTextView.setText("Take a deep breath, step back, and find a new perspective!");
                        break;
                    case "Ängstlich\uD83D\uDE1F":
                        moodInfoTextView.setText("Acknowledge your anxiety and take small steps towards managing it!");
                        break;
                    case "Wütend\uD83D\uDE21":
                        moodInfoTextView.setText("Anger is temporary; let it pass and choose peace!");
                        break;
                    case "Einsam\uD83D\uDE14":
                        moodInfoTextView.setText("Reach out to loved ones and build connections!");
                        break;
                    case "Müde\uD83D\uDE2B":
                        moodInfoTextView.setText("Rest, rejuvenate, and bounce back stronger!");
                        break;
                    case "Langweilig\uD83D\uDE12":
                        moodInfoTextView.setText("Discover new hobbies and ignite your curiosity!");
                        break;
                    case "Schläfrig\uD83D\uDE34":
                        moodInfoTextView.setText("Get some quality sleep and wake up refreshed!");
                        break;
                    default:
                        moodInfoTextView.setText("");
                }
            }
        });

        // Set click listener for the "View History" button
        viewHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the HistoryActivity to display the mood history
                Intent intent = new Intent(MainActivity3.this, ViewHistory.class);
                intent.putStringArrayListExtra("moodList", (ArrayList<String>) selectedMoods);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Get the text of the clicked button
        String selectedMood = ((Button) v).getText().toString();

        // Update the selected mood TextView
        selectedMoodTextView.setText(selectedMood);
    }
}