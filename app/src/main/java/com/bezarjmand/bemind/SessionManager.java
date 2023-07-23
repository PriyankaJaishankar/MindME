package com.bezarjmand.bemind;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public String getLoggedInUsername() {
        return preferences.getString(KEY_USERNAME, null);
    }

    public boolean isLoggedIn() {
        return preferences.contains(KEY_USERNAME);
    }

    public void logoutUser() {
        editor.remove(KEY_USERNAME);
        editor.apply();
    }
}
