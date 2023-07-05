package com.bezarjmand.bemind;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech textToSpeech;
    private Button backButton;
    private Button audioButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        backButton = findViewById(R.id.backButton4);
        audioButton = findViewById(R.id.audioButton4);
        stopButton = findViewById(R.id.stop);

        backButton.setOnClickListener(this);
        audioButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity8.this, "Language not supported", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity8.this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {}

            @Override
            public void onDone(String utteranceId) {
                if (utteranceId.equals("playback_complete")) {
                    // Playback completed, perform any necessary tasks
                }
            }

            @Override
            public void onError(String utteranceId) {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == backButton) {

            onBackPressed();
        } else if (view == audioButton) {

            String textToRead = "Bei der Meditation der liebenden Güte geht es darum, Gefühle der Liebe, Freundlichkeit und des Mitgefühls gegenüber sich selbst und anderen zu kultivieren. Finden Sie eine bequeme Position und schließen Sie die Augen. Beginnen Sie damit, freundliche und liebevolle Gedanken auf sich selbst zu richten und sich Ihr eigenes Wohlbefinden, Glück und Frieden zu wünschen. Als nächstes richten Sie diese Gefühle auf einen geliebten Menschen aus und senden ihm Wünsche der Liebe und des Glücks. Erweitern Sie dann Ihren Kreis des Mitgefühls, um Bekannte, Fremde und sogar schwierige Menschen einzubeziehen, und äußern Sie ihnen die gleichen Wünsche nach Liebe und Wohlbefinden. Breiten Sie diese Gefühle der Liebe und Güte schließlich auf alle Wesen in der Nähe und in der Ferne aus. Diese Praxis trägt dazu bei, positive Emotionen, Empathie und die Verbindung zu anderen zu fördern. Üben Sie regelmäßig, um ein mitfühlendes Herz und ein Gefühl der Verbundenheit zu entwickeln.";
            HashMap<String, String> params = new HashMap<>();
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "playback_complete");

            textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, params);
        } else if (view == stopButton) {
            // Handle stop button click (Stop the currently playing audio)
            textToSpeech.stop();
        }
    }
}
