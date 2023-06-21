package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity4 extends AppCompatActivity {

    Button breathingExercisesButton, bodyScanButton, mindfulWalkingButton, lovingKindnessButton, mantraButton, soundButton;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        breathingExercisesButton = findViewById(R.id.BreathingExercises);
        bodyScanButton = findViewById(R.id.BodyScan);
        mindfulWalkingButton = findViewById(R.id.MindfulWalking);
        lovingKindnessButton = findViewById(R.id.love);
        mantraButton = findViewById(R.id.Mantra);
        soundButton = findViewById(R.id.sound);

        textToSpeech = new TextToSpeech(MainActivity4.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        breathingExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Hier ist die Atemübung für Sie: Nehmen Sie eine bequeme Position ein und schließen Sie die Augen. Atmen Sie tief durch die Nase ein und lassen Sie dabei zu, dass sich Ihr Bauch hebt, während Sie Ihre Lungen mit Luft füllen. Atmen Sie langsam durch den Mund aus und lösen Sie mit jedem Ausatmen jegliche Anspannung oder Anspannung. Setzen Sie dieses tiefe Atemmuster fort, konzentrieren Sie sich auf das Gefühl Ihres Atems und lassen Sie alle ablenkenden Gedanken los. Machen Sie diese Übung regelmäßig, um Entspannung und Achtsamkeit zu fördern.";
                speakText(textToRead);
                navigateToPage(MainActivity5.class, textToRead);
            }
        });

        bodyScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Lassen Sie uns Ihren Körper scannen, folgen Sie also bitte der Struktur: Finden Sie eine bequeme Position und schließen Sie die Augen. Richten Sie Ihre Aufmerksamkeit auf verschiedene Teile Ihres Körpers, angefangen bei den Zehen bis hin zum Scheitel Ihres Kopfes. Beachten Sie alle Empfindungen oder Spannungsbereiche, während Sie jeden Körperteil scannen. Nehmen Sie sich einen Moment Zeit, um sich zu entspannen und eventuelle Spannungen abzubauen. Setzen Sie diesen Vorgang fort, indem Sie nach und nach jeden Teil Ihres Körpers scannen und entspannen. Diese Praxis hilft Ihnen, das Bewusstsein für Ihren Körper zu schärfen und fördert Entspannung und ein Gefühl der Erdung. Üben Sie regelmäßig, um Ihre Geist-Körper-Verbindung und Ihr allgemeines Wohlbefinden zu verbessern.";
                speakText(textToRead);
                navigateToPage(MainActivity6.class, textToRead);
            }
        });

        mindfulWalkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Hier können Sie die Gehmediation erlernen, also los geht's: Machen Sie achtsames Gehen, indem Sie jeden Schritt bewusst und präsent unternehmen. Achten Sie beim Gehen auf das Gefühl, dass Ihre Füße den Boden berühren. Nehmen Sie die Bewegung Ihres Körpers und den Rhythmus Ihrer Schritte wahr. Seien Sie beim Gehen völlig präsent und beobachten Sie die Anblicke, Geräusche und Empfindungen um Sie herum. Wenn Ihre Gedanken abschweifen, konzentrieren Sie sich wieder sanft auf das Gehen. Diese Praxis kann Ihnen dabei helfen, ein Gefühl der Erdung zu entwickeln, Ihre Verbindung zum gegenwärtigen Moment zu stärken und mehr Achtsamkeit in Ihre täglichen Aktivitäten zu bringen.";
                speakText(textToRead);
                navigateToPage(MainActivity7.class, textToRead);
            }
        });

        lovingKindnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Bei der Meditation der liebenden Güte geht es darum, Gefühle der Liebe, Freundlichkeit und des Mitgefühls gegenüber sich selbst und anderen zu kultivieren. Finden Sie eine bequeme Position und schließen Sie die Augen. Beginnen Sie damit, freundliche und liebevolle Gedanken auf sich selbst zu richten und sich Ihr eigenes Wohlbefinden, Glück und Frieden zu wünschen. Als nächstes richten Sie diese Gefühle auf einen geliebten Menschen aus und senden ihm Wünsche der Liebe und des Glücks. Erweitern Sie dann Ihren Kreis des Mitgefühls, um Bekannte, Fremde und sogar schwierige Menschen einzubeziehen, und äußern Sie ihnen die gleichen Wünsche nach Liebe und Wohlbefinden. Breiten Sie diese Gefühle der Liebe und Güte schließlich auf alle Wesen in der Nähe und in der Ferne aus. Diese Praxis trägt dazu bei, positive Emotionen, Empathie und die Verbindung zu anderen zu fördern. Üben Sie regelmäßig, um ein mitfühlendes Herz und ein Gefühl der Verbundenheit zu entwickeln.";
                speakText(textToRead);
                navigateToPage(MainActivity8.class, textToRead);
            }
        });

        mantraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Bei der Mantra-Meditation wird ein Wort, eine Phrase oder ein Ton, ein Mantra genannt, wiederholt, um den Geist zu fokussieren und ein Gefühl von Frieden und Klarheit zu entwickeln. Hier ist eine kurze Beschreibung der Mantra-Meditation: Wählen Sie ein Mantra, das Sie anspricht. Es kann ein traditionelles Sanskrit-Mantra wie „Om“ oder ein einfacher Satz wie „Ich bin ruhig und zentriert“ sein. Suchen Sie sich einen ruhigen und bequemen Platz zum Sitzen, schließen Sie die Augen und atmen Sie ein paar Mal tief durch, um sich zu entspannen. Beginnen Sie, das von Ihnen gewählte Mantra still oder laut zu wiederholen und richten Sie dabei Ihre Aufmerksamkeit auf den Klang und die Schwingung der Wörter. Wann immer Ihre Gedanken abschweifen, lenken Sie Ihre Aufmerksamkeit sanft wieder auf das Mantra. Üben Sie diese Meditation einige Minuten lang und lassen Sie sich vom Mantra in einen Zustand tiefer Entspannung und innerer Stille führen. Mantra-Meditation kann helfen, den Geist zu beruhigen, die Konzentration zu verbessern und ein Gefühl von Frieden und Klarheit zu vermitteln. Regelmäßiges Üben kann Ihre Verbindung zum gegenwärtigen Moment vertiefen und ein Gefühl der inneren Ruhe fördern.";
                speakText(textToRead);
                navigateToPage(MainActivity9.class, textToRead);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Bei der Klangmeditation konzentriert man sich auf äußere oder innere Geräusche wie den Atem oder bestimmte Geräusche wie Klangschalen oder Naturgeräusche, um Achtsamkeit und tiefe Entspannung zu fördern. Hier ist eine kurze Beschreibung der Klangmeditation:Suchen Sie sich einen ruhigen und bequemen Platz zum Sitzen oder Liegen. Schließen Sie die Augen und atmen Sie ein paar Mal tief durch, um sich zu entspannen. Lenken Sie Ihre Aufmerksamkeit auf die Geräusche um Sie herum, sowohl in der Nähe als auch in der Ferne. Hören Sie sich einfach die Geräusche an, ohne sie zu beurteilen oder zu analysieren, und lassen Sie sie kommen und gehen. Beachten Sie die Qualitäten der Klänge, wie Tonhöhe, Lautstärke und Rhythmus. Wenn Ihre Gedanken abschweifen, konzentrieren Sie sich sanft wieder auf den gegenwärtigen Moment und die Geräusche um Sie herum. Sie können auch bestimmte Klanginstrumente oder Aufnahmen verwenden, um das Erlebnis zu verbessern. Üben Sie regelmäßig Klangmeditation, um ein Gefühl der Präsenz zu entwickeln, Ihr Bewusstsein zu vertiefen und die Entspannung zu fördern. Es kann eine beruhigende und eindringliche Möglichkeit sein, sich mit dem gegenwärtigen Moment zu verbinden.";
                speakText(textToRead);
                navigateToPage(MainActivity10.class, textToRead);
            }
        });
    }

    private void speakText(String text) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void navigateToPage(Class<?> cls, String text) {
        Intent intent = new Intent(MainActivity4.this, cls);
        intent.putExtra("textToRead", text);
        startActivity(intent);
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