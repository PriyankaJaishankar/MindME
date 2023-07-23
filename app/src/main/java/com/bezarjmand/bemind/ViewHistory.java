package com.bezarjmand.bemind;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ViewHistory extends AppCompatActivity {
    private BarChart moodChart;
    private List<String> moodList;
    private List<String> dateList;
    // Initialize the DatabaseHelper
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    int[] colors = ColorTemplate.MATERIAL_COLORS;

    private String loggedInUsername;

    private Button logoutButton;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);

        // Find the BarChart view
        moodChart = findViewById(R.id.moodChart);

        // Customize the grid color
        moodChart.setDrawGridBackground(true);
        moodChart.setGridBackgroundColor(R.color.lightGray);

        // Get the logged-in username from the session
        sessionManager = new SessionManager(this);
        loggedInUsername = sessionManager.getLoggedInUsername();

        if (loggedInUsername != null && !loggedInUsername.isEmpty()) {
            // Fetch mood data with date and time from the database for the currently logged-in user
            fetchMoodDataForLoggedInUser();
        } else {
            // Handle the case where the logged-in username is null or empty
            // For example, you can show an error message or redirect to the login screen
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity or redirect to the login screen
            return;
        }

        // Customize the X-axis color
        XAxis xAxis = moodChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(R.color.darkGray);
        xAxis.setTextColor(Color.BLACK);

        // Customize the Y-axis color
        YAxis leftAxis = moodChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(R.color.darkGray);
        leftAxis.setTextColor(Color.BLACK);

        YAxis rightAxis = moodChart.getAxisRight();
        rightAxis.setEnabled(false);

        // Create a dataset with the mood entries
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < moodList.size(); i++) {
            String mood = moodList.get(i);
            entries.add(new BarEntry(i, getMoodValue(mood)));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Mood Data");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);

        // Create a BarData object with the dataset
        BarData barData = new BarData(dataSet);

        // Set the bar chart data and customize it
        moodChart.setData(barData);
        moodChart.getDescription().setEnabled(false);
        moodChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(dateList));
        moodChart.getXAxis().setGranularity(1f);
        moodChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        moodChart.getAxisRight().setEnabled(false);
        moodChart.getLegend().setEnabled(false);
        moodChart.setFitBars(true);
        moodChart.invalidate();

        Button backToMoodSelectionButton = findViewById(R.id.backToMoodSelectionButton);
        backToMoodSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the action to go back to the main activity
                Intent intent = new Intent(ViewHistory.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        Button furtherButton = findViewById(R.id.furtherToMainButton);
        furtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewHistory.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        Button clearHistoryButton = findViewById(R.id.clearHistoryButton);
        clearHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });


        // Set click listeners for the chart
        moodChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int position = (int) e.getX();
                String mood = moodList.get(position);
                String date = dateList.get(position);

                // Show the date, time, and corresponding mood when a bar is clicked
                Toast.makeText(ViewHistory.this, "Date: " + date + "\nMood: " + mood, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
                // Do nothing when nothing is selected
            }
        });
    }

    private void fetchMoodDataForLoggedInUser() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_MOOD_HISTORY,
                new String[]{DatabaseHelper.COLUMN_MOOD, DatabaseHelper.COLUMN_DATE},
                DatabaseHelper.COLUMN_USERNAME + "=?",
                new String[]{loggedInUsername},
                null, null, null);
        int moodIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOOD);
        int dateIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE);
        moodList = new ArrayList<>();
        dateList = new ArrayList<>();
        if (moodIndex != -1 && dateIndex != -1) {
            while (cursor.moveToNext()) {
                String mood = cursor.getString(moodIndex);
                if (mood != null) {
                    moodList.add(mood);
                }
                String date = cursor.getString(dateIndex);
                if (date != null) {
                    dateList.add(date);
                }
            }
        }
        cursor.close();
    }

    private float getMoodValue(String mood) {
        if (mood != null) {
            // Assign a numerical value to each mood (e.g., Very Happy: 5, Happy: 4, Neutral: 3, etc.)
            // Adjust this logic based on your mood scale
            switch (mood) {
                case "Glücklich\uD83D\uDE04":
                    return 12;
                case "Zufrieden\uD83D\uDE0A":
                    return 11;
                case "Normal\uD83D\uDE10":
                    return 10;
                case "Ruhig\uD83D\uDE0C":
                    return 9;
                case "Traurig\uD83D\uDE22":
                    return 8;
                case "Frustriert\uD83D\uDE20":
                    return 7;
                case "Ängstlich\uD83D\uDE1F":
                    return 6;
                case "Wütend\uD83D\uDE21":
                    return 5;
                case "Einsam\uD83D\uDE14":
                    return 4;
                case "Müde\uD83D\uDE2B":
                    return 3;
                case "Langweilig\uD83D\uDE12":
                    return 2;
                case "Schläfrig\uD83D\uDE34":
                    return 1;
            }
        }
        // Return 0 or any default value if mood is null or not recognized
        return 0;
    }

    private void clearHistory() {
        // Clear the history from the database
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MOOD_HISTORY, null, null);

        // Display a message that the history is cleared
        Toast.makeText(this, "History cleared", Toast.LENGTH_SHORT).show();

        // Refresh the activity to update the chart and mood list
        recreate();
    }


}