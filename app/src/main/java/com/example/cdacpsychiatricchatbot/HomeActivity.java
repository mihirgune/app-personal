package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button bot,covid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bot = (Button) findViewById(R.id.bot);
        covid = (Button) findViewById(R.id.covid);

        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBot();
            }
        });

        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCOVID();
            }
        });
    }

    private void openBot() {
        Intent intent = new Intent(this, ChatbotActivity.class);
        startActivity(intent);
    }

    private void openCOVID() {
        Intent intent = new Intent(this, CovidResultActivity.class);
        startActivity(intent);
    }
}