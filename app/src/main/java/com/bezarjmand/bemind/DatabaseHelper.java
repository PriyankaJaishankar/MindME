package com.bezarjmand.bemind;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mood_history.db";
    private static final int DATABASE_VERSION = 3; // Increase the version number
    public static final String TABLE_MOOD_HISTORY = "mood_history";
    private static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_USERNAME = "username"; // New column for username

    private static final String CREATE_TABLE_MOOD_HISTORY_QUERY = "CREATE TABLE IF NOT EXISTS " +
            TABLE_MOOD_HISTORY + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_MOOD + " TEXT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_USERNAME + " TEXT" + // Add the new column for username
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOOD_HISTORY_QUERY); // Create the mood history table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOOD_HISTORY);
        onCreate(db);
    }

    public long insertMood(String mood, String username) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOOD, mood);
        // Add the current date to the database
        values.put(COLUMN_DATE, getCurrentDateTime());
        values.put(COLUMN_USERNAME, username); // Save the username with the mood
        return db.insert(TABLE_MOOD_HISTORY, null, values);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public boolean isExistingUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MOOD_HISTORY + " WHERE " + COLUMN_USERNAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public long saveUsernameToDatabase(String username) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        return db.insert(TABLE_MOOD_HISTORY, null, values);
    }
}
