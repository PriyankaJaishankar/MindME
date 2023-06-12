package com.bezarjmand.bemind;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            case "Happy":
                value = 5;
                break;
            case "Normal":
                value = 4;
                break;
            case "Satisfied":
                value = 3;
                break;
            case "Sad":
                value = 2;
                break;
            case "Angry":
                value = 1;
                break;
        }
        return value;


    }


}