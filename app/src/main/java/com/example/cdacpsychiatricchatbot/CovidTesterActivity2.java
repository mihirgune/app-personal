package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CovidTesterActivity2 extends AppCompatActivity {

    TextView questions2;
    Button buttonOne,buttonTwo;
    public int age;
    public int sexInput = 0;
    public int lossSmellAndTasteInput = 1;
    public int coughInput = 0;
    public int fatigueInput = 0;
    public int skippedMealsInput = 0;
    public double answer;

    public int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tester2);

        questions2 = (TextView)  findViewById(R.id.questions2);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);

        Intent intent = getIntent();
        String temp = intent.getStringExtra("userAge");
        age = Integer.parseInt(temp);

        buttonOne.setText("Male");
        buttonTwo.setText("Female");

        questions2.setText("Enter your gender");

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0) {
                    sexInput = 1;
                    buttonOne.setText("Yes");
                    buttonTwo.setText("No");
                    questions2.setText("Do you have the sense of smell and taste?");
                    num = 1;
                }
                else if(num==1) {
                    lossSmellAndTasteInput = 0;
                    questions2.setText("Do you have severe or significant cough?");
                    num = 2;
                }
                else if(num==2) {
                    coughInput = 1;
                    questions2.setText("Are you feeling fatigued?");
                    num = 3;
                }
                else if(num==3) {
                    fatigueInput = 1;
                    questions2.setText("Have you skipped a meal in the last week?");
                    num = 4;
                }
                else if(num==4) {
                    skippedMealsInput = 1;
                    double predictionsModel = - 1.32 - (0.01 * age) + (0.44 * sexInput) + (1.75 * lossSmellAndTasteInput) + (0.31 * coughInput) + (0.49 * fatigueInput) + (0.39 * skippedMealsInput);
                    answer = convertValue(predictionsModel);
                    openResult();
                }
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0) {
                    sexInput = 0;
                    buttonOne.setText("Yes");
                    buttonTwo.setText("No");
                    questions2.setText("Do you have the sense of smell and taste?");
                    num = 1;
                }
                else if(num==1) {
                    lossSmellAndTasteInput = 1;
                    questions2.setText("Do you have severe or significant cough?");
                    num = 2;
                }
                else if(num==2) {
                    coughInput = 0;
                    questions2.setText("Are you feeling fatigued?");
                    num = 3;
                }
                else if(num==3) {
                    fatigueInput = 0;
                    questions2.setText("Have you skipped a meal in the last week?");
                    num = 4;
                }
                else if(num==4) {
                    skippedMealsInput = 0;
                    double predictionsModel = - 1.32 - (0.01 * age) + (0.44 * sexInput) + (1.75 * lossSmellAndTasteInput) + (0.31 * coughInput) + (0.49 * fatigueInput) + (0.39 * skippedMealsInput);
                    answer = convertValue(predictionsModel);
                    openResult();
                } else {
                    openResult();
                }
            }
        });


    }

    public double convertValue(double x) {
        return (Math.exp(x)/(1+Math.exp(x)));
    }

    public void openResult() {
        Intent intent = new Intent(this, CovidResultActivity.class);
        int startingCheck = lossSmellAndTasteInput + coughInput + skippedMealsInput + fatigueInput;
        Bundle extras = new Bundle();
        extras.putDouble("userProb",answer);
        extras.putInt("ssCheck",startingCheck);
        intent.putExtras(extras);
        startActivity(intent);
    }
}