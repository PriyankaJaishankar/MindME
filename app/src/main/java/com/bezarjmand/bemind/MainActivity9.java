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
            String text = "Mantra meditation involves the repetition of a word, phrase, or sound, known as a mantra, to focus the mind and cultivate a sense of peace and clarity. Here's a brief description of mantra meditation:Choose a mantra that resonates with you. It can be a traditional Sanskrit mantra like Om or a simple phrase like I am calm and centred. Find a quiet and comfortable place to sit, close your eyes, and take a few deep breaths to relax. Begin repeating your chosen mantra silently or aloud, focusing your attention on the sound and vibration of the words. Whenever your mind starts to wander, gently bring your attention back to the mantra. Practice this meditation for a few minutes, allowing the mantra to guide you into a state of deep relaxation and inner stillness. Mantra meditation can help quiet the mind, enhance focus, and bring a sense of peace and clarity. Regular practice can deepen your connection to the present moment and cultivate a sense of inner tranquility.";
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

