package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ConcurrentHashMap;

public class CovidTesterActivity extends AppCompatActivity implements CovidPageTwo.CovidPageTwoListener, CovidPageOne.CovidPageOneListener {

    private CovidPageOne fragmentOne;
    private CovidPageTwo fragmentTwo;
    private CharSequence ageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_tester);

        fragmentOne = new CovidPageOne();
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, fragmentOne).commit();

    }

    @Override
    public void onInputOneSent(CharSequence input) {
        ageInput = input;
        Log.w("testing one",input.toString());
        fragmentTwo = new CovidPageTwo();
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,fragmentTwo).commit();
    }

    @Override
    public void onInputTwoSent(Bundle input) {
        int age = Integer.parseInt(ageInput.toString());
        int sexInput = input.getInt("sexInput");
        int lossSmellAndTasteInput = input.getInt("lossSmellAndTasteInput");
        int coughInput = input.getInt("coughInput");
        int fatigueInput = input.getInt("fatigueInput");
        int skippedMealsInput = input.getInt("skippedMealsInput");
        double predictionsModel = - 1.32 - (0.01 * age) + (0.44 * sexInput) + (1.75 * lossSmellAndTasteInput) + (0.31 * coughInput) + (0.49 * fatigueInput) + (0.39 * skippedMealsInput);
        double answer = convertValue(predictionsModel);
        int ssCheck = lossSmellAndTasteInput + coughInput + fatigueInput + skippedMealsInput;
        CovidPageResult fragmentThree = new CovidPageResult();
        Bundle b = new Bundle();
        b.putDouble("answer",answer);
        b.putInt("ssCheck",ssCheck);
        fragmentThree.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer,fragmentThree).commit();
    }

    private double convertValue(double x) {
        return (Math.exp(x)/(1+Math.exp(x)));
    }
}