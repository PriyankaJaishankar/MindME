package com.bezarjmand.bemind;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.getDefault());
                } else {
                    Toast.makeText(MainActivity8.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton4);
        Button backButton = findViewById(R.id.backButton4);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton4) {
            String text = "Loving-kindness meditation involves cultivating feelings of love, kindness, and compassion towards oneself and others. Find a comfortable position and close your eyes. Begin by directing kind and loving thoughts towards yourself, wishing for your own well-being, happiness, and peace. Next, extend those feelings towards a loved one, sending them wishes of love and happiness. Then, expand your circle of compassion to include acquaintances, strangers, and even difficult individuals, offering them the same wishes of love and well-being. Finally, extend these feelings of love and kindness to all beings, near and far. This practice helps foster positive emotions, empathy, and connection with others. Practice regularly to cultivate a compassionate heart and a sense of interconnectedness.";
            speakText(text);
        } else if (view.getId() == R.id.backButton4) {
            finish();
        }
    }


    private void speakText(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}



