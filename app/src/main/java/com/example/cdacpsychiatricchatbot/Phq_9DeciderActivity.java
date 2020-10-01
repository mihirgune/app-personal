package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Phq_9DeciderActivity extends AppCompatActivity {

    Button phq9TestOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phq_9_decider);

        phq9TestOpen = (Button) findViewById(R.id.phq9_test_open);

        phq9TestOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhq9Test();
            }
        });
    }

    private void openPhq9Test() {
        Intent intent = new Intent(this, Phq_9TesterActivity.class);
        startActivity(intent);
    }
}