package com.bezarjmand.bemind;

import android.annotation.SuppressLint;
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton1) {
            String text = "Finden Sie eine bequeme Position und schließen Sie die Augen. Atmen Sie tief durch die Nase ein und lassen Sie dabei zu, dass sich Ihr Bauch hebt, während Sie Ihre Lungen mit Luft füllen. Atmen Sie langsam durch den Mund aus und lösen Sie mit jedem Ausatmen jegliche Anspannung oder Anspannung. Setzen Sie dieses tiefe Atemmuster fort, konzentrieren Sie sich auf das Gefühl Ihres Atems und lassen Sie alle ablenkenden Gedanken los. Machen Sie diese Übung regelmäßig, um Entspannung und Achtsamkeit zu fördern.";
            speakText(text);
        } else if (view.getId() == R.id.backButton1) {
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


