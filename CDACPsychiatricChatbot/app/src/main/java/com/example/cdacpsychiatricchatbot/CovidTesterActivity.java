package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CovidTesterActivity extends AppCompatActivity {

    TextView questions;
    EditText editText;
    Button enter;
    public String age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tester);

        questions = (TextView) findViewById(R.id.questions);
        editText = (EditText) findViewById(R.id.editText);
        enter = (Button) findViewById(R.id.enter);

        questions.setText("Enter age");
        editText.setHint("Years");

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = editText.getText().toString();
                openCOVIDTesterActivity2();
            }
        });
    }

    public void openCOVIDTesterActivity2() {
        Intent intent = new Intent(this, CovidTesterActivity2.class);
        intent.putExtra("userAge",age);
        startActivity(intent);
    }
}