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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
public class ViewHistory extends AppCompatActivity {
    private BarChart moodChart;

    // Initialize the DatabaseHelper
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    int[] colors = ColorTemplate.MATERIAL_COLORS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);
// Find the BarChart view
        moodChart = findViewById(R.id.moodChart);

        // Customize the grid color
        moodChart.setDrawGridBackground(true);
        moodChart.setGridBackgroundColor(R.color.lightGray);

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

        // Fetch mood data from the database
        List<String> moodList = fetchMoodData();

        // Create entries for the BarChart
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < moodList.size(); i++) {
            String mood = moodList.get(i);
            entries.add(new BarEntry(i, getMoodValue(mood)));
        }

        // Create a dataset with the mood entries
        BarDataSet dataSet = new BarDataSet(entries, "Mood Data");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);

        // Create a BarData object with the dataset
        BarData barData = new BarData(dataSet);

        // Set the bar chart data and customize it
        moodChart.setData(barData);
        moodChart.getDescription().setEnabled(false);
        moodChart.getXAxis().setEnabled(false);
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
    }

    private List<String> fetchMoodData() {
        List<String> moodList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_MOOD_HISTORY,
                new String[]{DatabaseHelper.COLUMN_MOOD},
                null, null, null, null, null);
        int columnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOOD);
        if (columnIndex != -1) {
            while (cursor.moveToNext()) {
                String mood = cursor.getString(columnIndex);
                moodList.add(mood);
            }
        }
        cursor.close();
        return moodList;
    }

    private float getMoodValue(String mood) {
        // Assign a numerical value to each mood (e.g., Very Happy: 5, Happy: 4, Neutral: 3, etc.)
        // Adjust this logic based on your mood scale
        float value = 0;
        switch (mood) {
            case "Glücklich\uD83D\uDE04":
                value = 12;
                break;
            case "Zufrieden\uD83D\uDE0A":
                value = 11;
                break;
            case "Normal\uD83D\uDE10":
                value = 10;
                break;
            case "Ruhig\uD83D\uDE0C":
                value = 9;
                break;
            case "Traurig\uD83D\uDE22":
                value = 8;
                break;
            case "Frustriert\uD83D\uDE20":
                value = 7;
                break;
            case "Ängstlich\uD83D\uDE1F":
                value = 6;
                break;
            case "Wütend\uD83D\uDE21":
                value = 5;
                break;
            case "Einsam\uD83D\uDE14":
                value = 4;
                break;
            case "Müde\uD83D\uDE2B":
                value = 3;
                break;
            case "Langweilig\uD83D\uDE12":
                value = 2;
                break;
            case "Schläfrig\uD83D\uDE34":
                value = 1;
                break;
        }
        return value;


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