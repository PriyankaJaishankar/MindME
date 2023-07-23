package com.bezarjmand.bemind;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button loginButton;
    private Button createAccountButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.usernameEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inside the loginButton.setOnClickListener after successful login
                String username = usernameEditText.getText().toString();
                if (!username.isEmpty()) {
                    if (dbHelper.isExistingUser(username)) {
                        // Display a welcome back message
                        Toast.makeText(LoginActivity.this, "Willkommen zurück, " + username + "!", Toast.LENGTH_SHORT).show();

                        // Save the username to the session
                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.saveUsername(username);

                        // Start the MotivationalActivity
                        Intent intent = new Intent(LoginActivity.this, MotivationalActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Finish the LoginActivity to prevent going back on back press

                        // Hide the keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    } else {
                        Toast.makeText(LoginActivity.this, "Der Benutzer existiert nicht. Bitte erstellen Sie ein Konto.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Bitte geben Sie einen Benutzernamen ein", Toast.LENGTH_SHORT).show();
                }

            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                if (!username.isEmpty()) {
                    if (!dbHelper.isExistingUser(username)) {
                        dbHelper.saveUsernameToDatabase(username);

                        // Display an account created message
                        Toast.makeText(LoginActivity.this, "Account created for " + username, Toast.LENGTH_SHORT).show();

                        // Start the MotivationalActivity
                        Intent intent = new Intent(LoginActivity.this, MotivationalActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Finish the LoginActivity to prevent going back on back press
                    } else {
                        Toast.makeText(LoginActivity.this, "Der Benutzer existiert bereits. Bitte melden Sie sich an.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Bitte geben Sie einen Benutzername", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}