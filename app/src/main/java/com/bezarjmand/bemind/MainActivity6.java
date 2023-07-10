package com.bezarjmand.bemind;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity6 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Set the language to German
                    Locale germanLocale = new Locale("de", "DE");
                    int result = textToSpeech.setLanguage(germanLocale);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity6.this, "Deutsch wird nicht unterstützt.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Set the speech rate to a slower value
                        textToSpeech.setSpeechRate(0.7f);
                    }
                } else {
                    Toast.makeText(MainActivity6.this, "TextToSpeech-Initialisierung fehlgeschlagen.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton2);
        Button backButton = findViewById(R.id.backButton2);
        Button stopButton = findViewById(R.id.stop2);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }

    //@SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton2) {
            String text = "Finden Sie eine bequeme Position und schließen Sie die Augen. Richten Sie Ihre Aufmerksamkeit auf verschiedene Teile Ihres Körpers, angefangen bei den Zehen bis hin zum Scheitel Ihres Kopfes. Beachten Sie alle Empfindungen oder Spannungsbereiche, während Sie jeden Körperteil scannen. Nehmen Sie sich einen Moment Zeit, um sich zu entspannen und eventuelle Spannungen abzubauen. Setzen Sie diesen Vorgang fort, indem Sie nach und nach jeden Teil Ihres Körpers scannen und entspannen. Diese Praxis hilft Ihnen, das Bewusstsein für Ihren Körper zu schärfen und fördert Entspannung und ein Gefühl der Erdung. Üben Sie regelmäßig, um Ihre Geist-Körper-Verbindung und Ihr allgemeines Wohlbefinden zu verbessern.";
            speakText(text);
        }else if (view.getId() == R.id.stop2) {
            stopSpeaking();
        }
        else if (view.getId() == R.id.backButton2) {
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



