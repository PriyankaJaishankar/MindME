package com.bezarjmand.bemind;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity9 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.getDefault());
                } else {
                    Toast.makeText(MainActivity9.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton5);
        Button backButton = findViewById(R.id.backButton5);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton5) {
            String text = "Bei der Mantra-Meditation wird ein Wort, eine Phrase oder ein Ton, ein Mantra genannt, wiederholt, um den Geist zu fokussieren und ein Gefühl von Frieden und Klarheit zu entwickeln. Hier ist eine kurze Beschreibung der Mantra-Meditation: Wählen Sie ein Mantra, das Sie anspricht. Es kann ein traditionelles Sanskrit-Mantra wie „Om“ oder ein einfacher Satz wie „Ich bin ruhig und zentriert“ sein. Suchen Sie sich einen ruhigen und bequemen Platz zum Sitzen, schließen Sie die Augen und atmen Sie ein paar Mal tief durch, um sich zu entspannen. Beginnen Sie, das von Ihnen gewählte Mantra still oder laut zu wiederholen und richten Sie dabei Ihre Aufmerksamkeit auf den Klang und die Schwingung der Wörter. Wann immer Ihre Gedanken abschweifen, lenken Sie Ihre Aufmerksamkeit sanft wieder auf das Mantra. Üben Sie diese Meditation einige Minuten lang und lassen Sie sich vom Mantra in einen Zustand tiefer Entspannung und innerer Stille führen. Mantra-Meditation kann helfen, den Geist zu beruhigen, die Konzentration zu verbessern und ein Gefühl von Frieden und Klarheit zu vermitteln. Regelmäßiges Üben kann Ihre Verbindung zum gegenwärtigen Moment vertiefen und ein Gefühl der inneren Ruhe fördern.";
            speakText(text);
        } else if (view.getId() == R.id.backButton5) {
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

