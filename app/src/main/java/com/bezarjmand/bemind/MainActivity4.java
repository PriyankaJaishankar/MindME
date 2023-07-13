package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    Button breathingExercisesButton, bodyScanButton, mindfulWalkingButton, lovingKindnessButton, mantraButton, soundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        breathingExercisesButton = findViewById(R.id.BreathingExercises);
        bodyScanButton = findViewById(R.id.BodyScan);
        mindfulWalkingButton = findViewById(R.id.MindfulWalking);
        lovingKindnessButton = findViewById(R.id.love);
        mantraButton = findViewById(R.id.Mantra);
        soundButton = findViewById(R.id.sound);

        breathingExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                startActivity(intent);
            }
        });

        bodyScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
                startActivity(intent);
            }
        });

        mindfulWalkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
                startActivity(intent);
            }
        });

        lovingKindnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity8.class);
                startActivity(intent);
            }
        });

        mantraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity9.class);
                startActivity(intent);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity10.class);
                startActivity(intent);
            }
        });
    }
}
