package com.bezarjmand.bemind;



import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity22 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        Button playButton = findViewById(R.id.audioButtong5);
        Button backButton = findViewById(R.id.backButtong5);
        Button stopButton = findViewById(R.id.stopg5);
        Button nextButton= findViewById(R.id.nextButtong5);
        nextButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Set the language to German
                    Locale germanLocale = new Locale("de", "DE");
                    int result = textToSpeech.setLanguage(germanLocale);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity22.this, "Deutsch wird nicht unterstützt.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Set the speech rate to a slower value
                        textToSpeech.setSpeechRate(0.7f);
                    }
                } else {
                    Toast.makeText(MainActivity22.this, "TextToSpeech-Initialisierung fehlgeschlagen.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //@SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButtong5) {
            String text = "Schritt 5: Beobachten Sie Ihre Umgebung ohne Urteil.";
            speakText(text);
        }else if (view.getId() == R.id.stopg5) {
            stopSpeaking();
        }
        else if (view.getId() == R.id.backButtong5) {
            finish();
        }
    }


    private void speakText(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void stopSpeaking() {
        if (textToSpeech != null && textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }
    public void onNextButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
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