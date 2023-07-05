package com.bezarjmand.bemind;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.getDefault());
                } else {
                    Toast.makeText(MainActivity5.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton1);
        Button backButton = findViewById(R.id.backButton1);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton1) {
            String text = "Nehmen Sie eine bequeme Position ein und schließen Sie die Augen. Atmen Sie tief durch die Nase ein und erlauben Sie Ihrem Bauch, sich zu heben, während Sie Ihre Lungen mit Luft füllen. Atmen Sie langsam durch den Mund aus und lassen Sie dabei mit jedem Ausatmen jegliche Anspannung oder Stress los. Setzen Sie diese tiefe Atmung fort, konzentrieren Sie sich auf das Gefühl Ihres Atems und lassen Sie alle ablenkenden Gedanken los. Üben Sie diese Übung regelmäßig, um Entspannung und Achtsamkeit zu fördern.";
            speakText(text);
        } else if (view.getId() == R.id.backButton1) {
            stopSpeaking();
            finish();
        }
    }

    private void speakText(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void stopSpeaking() {
        if (textToSpeech != null) {
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
