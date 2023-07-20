package com.bezarjmand.bemind;



import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity14 extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(MainActivity14.this, new TextToSpeech.OnInitListener() {
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
                // Not needed for this implementation
            }

            @Override
            public void onError(String utteranceId) {
                // Not needed for this implementation
            }
        });

        // Play Audio Button
        Button playAudioButton = findViewById(R.id.audioButton5);
        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        // Stop Audio Button
        Button stopAudioButton = findViewById(R.id.stop5);
        stopAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });

        // Next Übung Button
        Button nextUebungButton = findViewById(R.id.nextButton1);
        nextUebungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop the audio before navigating to the next page
                stopAudio();
                navigateToNextUebung();
            }
        });

        // Back Button
        Button backButton = findViewById(R.id.backButton5);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void playAudio() {
        String textToRead = "Schritt 5: Bleiben Sie präsent und lenken Sie Ihre Aufmerksamkeit auf die Empfindungen jedes Atems.";
        // Using Utterance ID to identify the utterance in onDone callback
        textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, "Step2Utterance");
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

    private void navigateToNextUebung() {
        // Start the activity for the next Übung or step of the breathing exercise
        // Replace BreathingExerciseStep3Activity with the class for the next Übung
        Intent intent = new Intent(MainActivity14.this, MainActivity4.class);
        startActivity(intent);
    }
}
