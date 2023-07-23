package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private Button button1;
    private Button button2;

    // Initialize the SessionManager
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize the SessionManager
        sessionManager = new SessionManager(this);

        // Retrieve the logged-in username
        String loggedInUsername = sessionManager.getLoggedInUsername();

        if (loggedInUsername != null && !loggedInUsername.isEmpty()) {
            // User is logged in, do the required actions here
            // For example, you can display a welcome message or show specific content
            Toast.makeText(this, "Welcome, " + loggedInUsername, Toast.LENGTH_SHORT).show();
        } else {
            // User is not logged in, handle the scenario accordingly
            // For example, you can redirect the user to the login screen
            Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close MainActivity2 to prevent going back to it after login
        }
        button1 = findViewById(R.id.mt);
        button2 = findViewById(R.id.mg);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}