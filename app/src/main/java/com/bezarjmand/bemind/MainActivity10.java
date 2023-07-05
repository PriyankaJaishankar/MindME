package com.bezarjmand.bemind;

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

    //@SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton6) {
            String text = "Bei der Klangmeditation konzentriert man sich auf äußere oder innere Geräusche wie den Atem oder bestimmte Geräusche wie Klangschalen oder Naturgeräusche, um Achtsamkeit und tiefe Entspannung zu fördern. Hier ist eine kurze Beschreibung der Klangmeditation:Suchen Sie sich einen ruhigen und bequemen Platz zum Sitzen oder Liegen. Schließen Sie die Augen und atmen Sie ein paar Mal tief durch, um sich zu entspannen. Lenken Sie Ihre Aufmerksamkeit auf die Geräusche um Sie herum, sowohl in der Nähe als auch in der Ferne. Hören Sie sich einfach die Geräusche an, ohne sie zu beurteilen oder zu analysieren, und lassen Sie sie kommen und gehen. Beachten Sie die Qualitäten der Klänge, wie Tonhöhe, Lautstärke und Rhythmus. Wenn Ihre Gedanken abschweifen, konzentrieren Sie sich sanft wieder auf den gegenwärtigen Moment und die Geräusche um Sie herum. Sie können auch bestimmte Klanginstrumente oder Aufnahmen verwenden, um das Erlebnis zu verbessern. Üben Sie regelmäßig Klangmeditation, um ein Gefühl der Präsenz zu entwickeln, Ihr Bewusstsein zu vertiefen und die Entspannung zu fördern. Es kann eine beruhigende und eindringliche Möglichkeit sein, sich mit dem gegenwärtigen Moment zu verbinden.";
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

