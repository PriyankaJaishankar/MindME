package com.bezarjmand.bemind;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.getDefault());
                } else {
                    Toast.makeText(MainActivity8.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playButton = findViewById(R.id.audioButton4);
        Button backButton = findViewById(R.id.backButton4);

        playButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    //@SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.audioButton4) {
            String text = "Bei der Meditation der liebenden Güte geht es darum, Gefühle der Liebe, Freundlichkeit und des Mitgefühls gegenüber sich selbst und anderen zu kultivieren. Finden Sie eine bequeme Position und schließen Sie die Augen. Beginnen Sie damit, freundliche und liebevolle Gedanken auf sich selbst zu richten und sich Ihr eigenes Wohlbefinden, Glück und Frieden zu wünschen. Als nächstes richten Sie diese Gefühle auf einen geliebten Menschen aus und senden ihm Wünsche der Liebe und des Glücks. Erweitern Sie dann Ihren Kreis des Mitgefühls, um Bekannte, Fremde und sogar schwierige Menschen einzubeziehen, und äußern Sie ihnen die gleichen Wünsche nach Liebe und Wohlbefinden. Breiten Sie diese Gefühle der Liebe und Güte schließlich auf alle Wesen in der Nähe und in der Ferne aus. Diese Praxis trägt dazu bei, positive Emotionen, Empathie und die Verbindung zu anderen zu fördern. Üben Sie regelmäßig, um ein mitfühlendes Herz und ein Gefühl der Verbundenheit zu entwickeln.";
            speakText(text);
        } else if (view.getId() == R.id.backButton4) {
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



