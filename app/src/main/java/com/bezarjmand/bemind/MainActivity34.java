package com.bezarjmand.bemind;



import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity34 extends AppCompatActivity {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main34);

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(MainActivity34.this, new TextToSpeech.OnInitListener() {
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
        Button playAudioButton = findViewById(R.id.audioButtonkl5);
        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        // Stop Audio Button
        Button stopAudioButton = findViewById(R.id.stopkl5);
        stopAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });

        // Next Übung Button
        Button nextUebungButton = findViewById(R.id.nextButton6);
        nextUebungButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop the audio before navigating to the next page
                stopAudio();
                navigateToNextUebung();
            }
        });

        // Back Button
        Button backButton = findViewById(R.id.backButtonkl5);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void playAudio() {
        String textToRead = "Schritt 5: Atmen Sie tief ein und lassen Sie sich beim Meditieren vom Klang leiten.";
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


    @Override
    public void onBackPressed() {
        stopAudio();
        super.onBackPressed();
    }

    private void navigateToNextUebung() {

        Intent intent = new Intent(MainActivity34.this, MainActivity4.class);
        startActivity(intent);
    }
}
