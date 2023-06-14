package com.bezarjmand.bemind;


import android.annotation.SuppressLint;
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
                    textToSpeech.setLanguage(Locale.getDefault());
                } else {
                    Toast.makeText(MainActivity6.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton2);
        Button backButton = findViewById(R.id.backButton2);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton2) {
            String text = "Find a comfortable position and close your eyes. Bring your attention to different parts of your body, starting from your toes and moving all the way up to the top of your head. Notice any sensations or areas of tension as you scan each body part. Take a moment to relax and release any tension you may be holding. Continue this process, gradually scanning and relaxing each part of your body. This practice helps you cultivate awareness of your body and promotes relaxation and a sense of grounding. Practice regularly to enhance your mind-body connection and overall well-being.";
            speakText(text);
        } else if (view.getId() == R.id.backButton2) {
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



