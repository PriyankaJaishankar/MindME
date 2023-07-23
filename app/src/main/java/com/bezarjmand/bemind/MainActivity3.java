package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private TextView selectedMoodTextView;
    private TextView moodInfoTextView;
    private List<String> selectedMoods;

    private DatabaseHelper databaseHelper;
    private SessionManager sessionManager;
    private String loggedInUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize the SessionManager
        sessionManager = new SessionManager(this);

        // Retrieve the logged-in username from the session
        loggedInUsername = sessionManager.getLoggedInUsername();

        if (loggedInUsername != null && !loggedInUsername.isEmpty()) {
            // User is logged in, do the required actions here
            Toast.makeText(this, "Welcome, " + loggedInUsername, Toast.LENGTH_SHORT).show();
        } else {
            // User is not logged in, handle the scenario accordingly
            // For example, you can redirect the user to the login screen
            Intent intent = new Intent(MainActivity3.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity3 to prevent going back to it after login
        }

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
                String selectedMood = selectedMoodTextView.getText().toString();

                // Add the selected mood to the list
                selectedMoods.add(selectedMood);

                // Get the username of the logged-in user from the session
                SessionManager sessionManager = new SessionManager(MainActivity3.this);
                String username = sessionManager.getLoggedInUsername();

                // Insert the selected mood and username into the database
                databaseHelper.insertMood(selectedMood, username);


                // Display the corresponding message for the selected mood
                switch (selectedMood) {
                    case "Glücklich\uD83D\uDE04":
                        moodInfoTextView.setText("Glück ist eine Entscheidung, also entscheide dich, glücklich zu sein!");
                        break;
                    case "Traurig\uD83D\uDE22":
                        moodInfoTextView.setText("Jedem Sturm geht irgendwann der Regen aus!");
                        break;
                    case "Normal\uD83D\uDE10":
                        moodInfoTextView.setText("Nehmen Sie die gewöhnlichen Momente an, denn sie machen das Leben aus!");
                        break;
                    case "Zufrieden\uD83D\uDE0A":
                        moodInfoTextView.setText("Zufriedenheit ist das Sprungbrett zur Zufriedenheit!");
                        break;
                    case "Ruhig\uD83D\uDE0C":
                        moodInfoTextView.setText("Finden Sie inmitten des Chaos die Ruhe in sich selbst!");
                        break;
                    case "Frustriert\uD83D\uDE20":
                        moodInfoTextView.setText("Atmen Sie tief durch, treten Sie zurück und finden Sie eine neue Perspektive!");
                        break;
                    case "Ängstlich\uD83D\uDE1F":
                        moodInfoTextView.setText("Erkennen Sie Ihre Ängste an und unternehmen Sie kleine Schritte, um sie zu bewältigen!");
                        break;
                    case "Wütend\uD83D\uDE21":
                        moodInfoTextView.setText("Zorn ist nur vorübergehend; lass ihn vorübergehen und wähle den Frieden!");
                        break;
                    case "Einsam\uD83D\uDE14":
                        moodInfoTextView.setText("Gehen Sie auf Ihre Lieben zu und bauen Sie Verbindungen auf!");
                        break;
                    case "Müde\uD83D\uDE2B":
                        moodInfoTextView.setText("Ausruhen, verjüngen und gestärkt wieder auf die Beine kommen!");
                        break;
                    case "Langweilig\uD83D\uDE12":
                        moodInfoTextView.setText("Ruhen Sie sich aus, verjüngen Sie sich und kommen Sie gestärkt zurück!");
                        break;
                    case "Schläfrig\uD83D\uDE34":
                        moodInfoTextView.setText("Gönnen Sie sich einen guten Schlaf und wachen Sie erfrischt auf!");
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