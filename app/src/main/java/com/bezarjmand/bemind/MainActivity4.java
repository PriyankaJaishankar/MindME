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
                String textToRead = "here is the breathing exercise for you, Find a comfortable position and close your eyes. Take a deep breath in through your nose, allowing your abdomen to rise as you fill your lungs with air. Slowly exhale through your mouth, releasing any tension or stress with each breath out. Continue this deep breathing pattern, focusing on the sensation of your breath and letting go of any distracting thoughts. Practice this exercise regularly to promote relaxation and mindfulness.";
                speakText(textToRead);
                navigateToPage(MainActivity5.class, textToRead);
            }
        });

        bodyScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Lets scan your body so please follow the structure : Find a comfortable position and close your eyes. Bring your attention to different parts of your body, starting from your toes and moving all the way up to the top of your head. Notice any sensations or areas of tension as you scan each body part. Take a moment to relax and release any tension you may be holding. Continue this process, gradually scanning and relaxing each part of your body. This practice helps you cultivate awareness of your body and promotes relaxation and a sense of grounding. Practice regularly to enhance your mind-body connection and overall well-being.";
                speakText(textToRead);
                navigateToPage(MainActivity6.class, textToRead);
            }
        });

        mindfulWalkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "here you can learn walking mediation so here we go, Engage in mindful walking by taking each step with awareness and presence. As you walk, pay attention to the sensation of your feet touching the ground. Notice the movement of your body and the rhythm of your steps. Be fully present in the experience of walking, observing the sights, sounds, and sensations around you. If your mind starts to wander, gently bring your focus back to the act of walking. This practice can help you cultivate a sense of grounding, enhance your connection with the present moment, and bring a greater sense of mindfulness into your daily activities.";
                speakText(textToRead);
                navigateToPage(MainActivity7.class, textToRead);
            }
        });

        lovingKindnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Loving-kindness meditation involves cultivating feelings of love, kindness, and compassion towards oneself and others. Find a comfortable position and close your eyes. Begin by directing kind and loving thoughts towards yourself, wishing for your own well-being, happiness, and peace. Next, extend those feelings towards a loved one, sending them wishes of love and happiness. Then, expand your circle of compassion to include acquaintances, strangers, and even difficult individuals, offering them the same wishes of love and well-being. Finally, extend these feelings of love and kindness to all beings, near and far. This practice helps foster positive emotions, empathy, and connection with others. Practice regularly to cultivate a compassionate heart and a sense of interconnectedness.";
                speakText(textToRead);
                navigateToPage(MainActivity8.class, textToRead);
            }
        });

        mantraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Mantra meditation involves the repetition of a word, phrase, or sound, known as a mantra, to focus the mind and cultivate a sense of peace and clarity. Here's a brief description of mantra meditation: Choose a mantra that resonates with you. It can be a traditional Sanskrit mantra like Om or a simple phrase like I am calm and centered.Find a quiet and comfortable place to sit, close your eyes, and take a few deep breaths to relax. Begin repeating your chosen mantra silently or aloud, focusing your attention on the sound and vibration of the words. Whenever your mind starts to wander, gently bring your attention back to the mantra. Practice this meditation for a few minutes, allowing the mantra to guide you into a state of deep relaxation and inner stillness. Mantra meditation can help quiet the mind, enhance focus, and bring a sense of peace and clarity. Regular practice can deepen your connection to the present moment and cultivate a sense of inner tranquility.";
                speakText(textToRead);
                navigateToPage(MainActivity9.class, textToRead);
            }
        });

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = "Sound meditation involves focusing on external sounds or internal sounds, such as the breath or specific sounds like singing bowls or nature sounds, to cultivate mindfulness and deep relaxation. Here's a brief description of sound meditation:Find a quiet and comfortable place to sit or lie down. Close your eyes and take a few deep breaths to relax. Bring your attention to the sounds around you, both near and far. Simply listen to the sounds without judgment or analysis, allowing them to come and go. Notice the qualities of the sounds, such as their pitch, volume, and rhythm. If your mind starts to wander, gently bring your focus back to the present moment and the sounds around you. You can also use specific sound instruments or recordings to enhance the experience. Practice sound meditation regularly to cultivate a sense of presence, deepen your awareness, and promote relaxation. It can be a soothing and immersive way to connect with the present moment.";
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