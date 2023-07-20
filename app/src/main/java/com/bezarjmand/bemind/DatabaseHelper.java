package com.bezarjmand.bemind;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mood_history.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_MOOD_HISTORY = "mood_history";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_DATE = "date";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //private static final String TABLE_MOOD_HISTORY = "mood_history";

    public static String getTableMoodHistory() {
        return TABLE_MOOD_HISTORY;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_MOOD_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MOOD + " TEXT,"
                + COLUMN_DATE + " TEXT" // Add date column
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD_HISTORY);
        onCreate(db);
    }

    public long insertMood(String mood) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOOD, mood);
        // Add the current date to the database
        values.put(COLUMN_DATE, getCurrentDateTime());
        return db.insert(TABLE_MOOD_HISTORY, null, values);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}