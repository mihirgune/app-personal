package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CovidResultActivity extends AppCompatActivity {

    TextView probDisplay,resultDisplay;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_result);

        probDisplay = (TextView) findViewById(R.id.probDisplay);
        resultDisplay = (TextView) findViewById(R.id.resultDisplay);
        doneButton = (Button) findViewById(R.id.doneButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        double probability = extras.getDouble("userProb");
        int sscheck = extras.getInt("ssCheck");

        String p = String.valueOf("The probability is "  + probability);
        probDisplay.setText(p);

        if(probability>0.5) {
            resultDisplay.setText("You may have COVID-19. Please get yourself tested");
        } else {
            if(sscheck>0) {
                resultDisplay.setText("You have starting symptoms. Please make a precautionary visit to a doctor");
            } else {
                resultDisplay.setText("You have no COVID-19 symptoms.");
            }
        }

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}