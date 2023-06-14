package com.bezarjmand.bemind;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity10 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.getDefault());
                } else {
                    Toast.makeText(MainActivity10.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton6);
        Button backButton = findViewById(R.id.backButton6);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton6) {
            String text = "Sound meditation involves focusing on external sounds or internal sounds, such as the breath or specific sounds like singing bowls or nature sounds, to cultivate mindfulness and deep relaxation. Here's a brief description of sound meditation:Find a quiet and comfortable place to sit or lie down. Close your eyes and take a few deep breaths to relax. Bring your attention to the sounds around you, both near and far. Simply listen to the sounds without judgment or analysis, allowing them to come and go. Notice the qualities of the sounds, such as their pitch, volume, and rhythm. If your mind starts to wander, gently bring your focus back to the present moment and the sounds around you. You can also use specific sound instruments or recordings to enhance the experience. Practice sound meditation regularly to cultivate a sense of presence, deepen your awareness, and promote relaxation. It can be a soothing and immersive way to connect with the present moment.";
            speakText(text);
        } else if (view.getId() == R.id.backButton6) {
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

