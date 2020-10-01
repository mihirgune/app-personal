package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Phq_9TesterActivity extends AppCompatActivity {

    private LinearLayout phq9ScrollViewLayout;
    private ScrollView phq9ScrollView;
    EditText phq9Input;
    Button sendPhq9Button;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phq_9_tester);

        phq9ScrollView = (ScrollView) findViewById(R.id.phq9ScrollView);
        phq9ScrollViewLayout = (LinearLayout) findViewById(R.id.phq9ScrollViewLayout);
        phq9Input = (EditText) findViewById(R.id.phq9_input);
        sendPhq9Button = (Button) findViewById(R.id.send_phq9_button);

        sendPhq9Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


    }
}