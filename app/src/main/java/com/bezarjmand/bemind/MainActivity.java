package com.bezarjmand.bemind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.bemind);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBemind();

            }
        });

    }
    public void openBemind(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}