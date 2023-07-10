package com.bezarjmand.bemind;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity7 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        
        Button playButton = findViewById(R.id.audioButton3);
        Button backButton = findViewById(R.id.backButton3);
        Button stopButton = findViewById(R.id.stop3);
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
                        Toast.makeText(MainActivity7.this, "Deutsch wird nicht unterstützt.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Set the speech rate to a slower value
                        textToSpeech.setSpeechRate(0.7f);
                    }
                } else {
                    Toast.makeText(MainActivity7.this, "TextToSpeech-Initialisierung fehlgeschlagen.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        
    }

    //@SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton3) {
            String text = "Beteiligen Sie sich am achtsamen Gehen, indem Sie jeden Schritt bewusst und präsent unternehmen. Achten Sie beim Gehen auf das Gefühl, dass Ihre Füße den Boden berühren. Nehmen Sie die Bewegung Ihres Körpers und den Rhythmus Ihrer Schritte wahr. Seien Sie beim Gehen völlig präsent und beobachten Sie die Anblicke, Geräusche und Empfindungen um Sie herum. Wenn Ihre Gedanken abschweifen, konzentrieren Sie sich wieder sanft auf das Gehen. Diese Praxis kann Ihnen dabei helfen, ein Gefühl der Erdung zu entwickeln, Ihre Verbindung zum gegenwärtigen Moment zu stärken und mehr Achtsamkeit in Ihre täglichen Aktivitäten zu bringen.";
            speakText(text);
        }else if (view.getId() == R.id.stop3) {
            stopSpeaking();
        }
        else if (view.getId() == R.id.backButton3) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}

