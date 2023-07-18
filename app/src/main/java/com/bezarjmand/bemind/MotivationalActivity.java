package com.bezarjmand.bemind;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MotivationalActivity extends AppCompatActivity {

    private List<String> motivationalThoughts;
    private TextView thoughtTextView;
    private Button furtherToMoodTrackingButton;

    private List<String> loadMotivationalThoughtsFromDatabase() {
        List<String> thoughts = new ArrayList<>();

        // Open the database
        SQLiteDatabase db = openOrCreateDatabase("MotivationalDB", Context.MODE_PRIVATE, null);

        // Create the "thoughts" table if it doesn't exist
        db.execSQL("CREATE TABLE IF NOT EXISTS thoughts (id INTEGER PRIMARY KEY AUTOINCREMENT, thought TEXT)");

        // Retrieve all thoughts from the table
        Cursor cursor = db.rawQuery("SELECT * FROM thoughts", null);
        int thoughtIndex = cursor.getColumnIndex("thought");
        if (cursor.moveToFirst()) {
            do {
                if (thoughtIndex != -1) {
                    String thought = cursor.getString(thoughtIndex);
                    thoughts.add(thought);
                }
            } while (cursor.moveToNext());
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return thoughts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational);

        thoughtTextView = findViewById(R.id.thoughtTextView);
        furtherToMoodTrackingButton = findViewById(R.id.furtherToMoodTracking);

        // Add thoughts to the database (backend)
        addMotivationalThoughtToDatabase("Das Leben ist eine Reise, genieße jeden Schritt.");
        addMotivationalThoughtToDatabase("Es ist nie zu spät, um glücklich zu sein.");
        addMotivationalThoughtToDatabase("Lasse dich von deinem Alter nicht limitieren, sondern inspirieren.");
        addMotivationalThoughtToDatabase("Jeder Tag ist ein neues Kapitel, fülle es mit Lächeln und Freude.");
        addMotivationalThoughtToDatabase("Das Alter ist nur eine Zahl, deine Einstellung ist das, was zählt.");
        addMotivationalThoughtToDatabase("Träume nicht nur, sondern tue. Du kannst noch viel erreichen.");
        addMotivationalThoughtToDatabase("In jedem Tag steckt ein Grund zur Dankbarkeit, finde ihn und lächle.");
        addMotivationalThoughtToDatabase("Das Leben mag Veränderungen bringen, aber dein Geist ist unerschütterlich.");
        addMotivationalThoughtToDatabase("Du hast bereits so viel erreicht. Vertraue darauf, dass noch mehr möglich ist.");
        addMotivationalThoughtToDatabase("Egal wie alt du bist, du bist immer noch wertvoll und wichtig.");
        addMotivationalThoughtToDatabase("Du bist einzigartig und hast eine Geschichte, die erzählt werden sollte.");
        addMotivationalThoughtToDatabase("Erfolg hat kein Verfallsdatum. Glaube an dich und deine Fähigkeiten.");
        addMotivationalThoughtToDatabase("Es ist nie zu spät, um Träume zu verwirklichen. Gehe mutig voran.");
        addMotivationalThoughtToDatabase("Deine Weisheit ist ein Geschenk, das mit anderen geteilt werden sollte");
        addMotivationalThoughtToDatabase("Jeder Tag bietet neue Chancen, um dankbar zu sein und Freude zu finden.");
        addMotivationalThoughtToDatabase("Halte dich an die guten Erinnerungen und lass die schlechten los.");
        addMotivationalThoughtToDatabase("Dein Lebensweg ist einzigartig und wertvoll. Gehe ihn mit Stolz.");

        // Load motivational thoughts from the database
        motivationalThoughts = loadMotivationalThoughtsFromDatabase();

        // Display a random thought
        if (!motivationalThoughts.isEmpty()) {
            String randomThought = getRandomThought();
            thoughtTextView.setText(randomThought);
        }

        furtherToMoodTrackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to MainActivity2
                Intent intent = new Intent(MotivationalActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    private void addMotivationalThoughtToDatabase(String thought) {
        // Open the database
        SQLiteDatabase db = openOrCreateDatabase("MotivationalDB", Context.MODE_PRIVATE, null);

        // Create the "thoughts" table if it doesn't exist
        db.execSQL("CREATE TABLE IF NOT EXISTS thoughts (id INTEGER PRIMARY KEY AUTOINCREMENT, thought TEXT)");

        // Insert the thought into the table
        ContentValues values = new ContentValues();
        values.put("thought", thought);
        db.insert("thoughts", null, values);

        // Close the database
        db.close();
    }



    private String getRandomThought() {
        Random random = new Random();
        int randomIndex = random.nextInt(motivationalThoughts.size());
        return motivationalThoughts.get(randomIndex);
    }
}