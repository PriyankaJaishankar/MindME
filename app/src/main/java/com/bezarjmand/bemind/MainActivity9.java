package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity9 extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(MainActivity9.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.GERMAN);
                    // Play the audio automatically when the page is loaded
                    playAudio();
                }
            }
        });

        // Set up the UtteranceProgressListener to detect when audio finishes
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                // Not needed for this implementation
            }

            @Override
            public void onDone(String utteranceId) {
                // Audio finished, navigate to the next page
                navigateToNextPage();
            }

            @Override
            public void onError(String utteranceId) {
                // Not needed for this implementation
            }
        });

        // Play Audio Button
        Button playAudioButton = findViewById(R.id.audioButtonm1);
        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        // Stop Audio Button
        Button stopAudioButton = findViewById(R.id.stopm1);
        stopAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });

        // Back Button
        Button backButton = findViewById(R.id.backButtonm1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void playAudio() {
        String textToRead = "Hier sind die Schritte der Mantra-Meditation,Schritt 1: Finden Sie einen ruhigen und komfortablen Ort.";
        // Using Utterance ID to identify the utterance in onDone callback
        textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, "Step1Utterance");
    }

    private void stopAudio() {
        if (textToSpeech != null && textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    // Override onBackPressed to stop audio before going back
    @Override
    public void onBackPressed() {
        stopAudio();
        super.onBackPressed();
    }

    private void navigateToNextPage() {
        // Start the activity for the next step of the breathing exercise
        Intent intent = new Intent(MainActivity9.this, MainActivity27.class);
        startActivity(intent);
    }
}


