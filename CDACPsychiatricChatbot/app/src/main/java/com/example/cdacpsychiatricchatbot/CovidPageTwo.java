package com.example.cdacpsychiatricchatbot;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CovidPageTwo extends Fragment {

    private Button buttonFirst,buttonSecond;
    private TextView questionsTwo;
    private CovidPageTwoListener listener;
    private int sexInput = 0;
    private int lossSmellAndTasteInput = 1;
    private int coughInput = 0;
    private int fatigueInput = 0;
    private int skippedMealsInput = 0;
    private int num = 0;

    public interface CovidPageTwoListener {
        void onInputTwoSent(Bundle input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_covid_page_two, container, false);

        buttonFirst = (Button) v.findViewById(R.id.buttonFirst);
        buttonSecond = (Button) v.findViewById(R.id.buttonSecond);
        questionsTwo = (TextView) v.findViewById(R.id.questionsTwo);

        questionsTwo.setText("Enter your gender");
        buttonFirst.setText("Male");
        buttonSecond.setText("Female");

        buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0) {
                    sexInput = 1;
                    buttonFirst.setText("Yes");
                    buttonSecond.setText("No");
                    questionsTwo.setText("Do you have the sense of smell and taste?");
                    num = 1;
                }
                else if(num==1) {
                    lossSmellAndTasteInput = 0;
                    questionsTwo.setText("Do you have severe or significant cough?");
                    num = 2;
                }
                else if(num==2) {
                    coughInput = 1;
                    questionsTwo.setText("Are you feeling fatigued?");
                    num = 3;
                }
                else if(num==3) {
                    fatigueInput = 1;
                    questionsTwo.setText("Have you skipped a meal in the last week?");
                    num = 4;
                }
                else if(num==4) {
                    skippedMealsInput = 1;
                    Bundle bundle = new Bundle();
                    bundle.putInt("sexInput",sexInput);
                    bundle.putInt("lossSmellAndTasteInput",lossSmellAndTasteInput);
                    bundle.putInt("coughInput",coughInput);
                    bundle.putInt("fatigueInput",fatigueInput);
                    bundle.putInt("skippedMealsInput",skippedMealsInput);
                    listener.onInputTwoSent(bundle);
                }
            }
        });

        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num==0) {
                    sexInput = 0;
                    buttonFirst.setText("Yes");
                    buttonSecond.setText("No");
                    questionsTwo.setText("Do you have the sense of smell and taste?");
                    num = 1;
                }
                else if(num==1) {
                    lossSmellAndTasteInput = 1;
                    questionsTwo.setText("Do you have severe or significant cough?");
                    num = 2;
                }
                else if(num==2) {
                    coughInput = 0;
                    questionsTwo.setText("Are you feeling fatigued?");
                    num = 3;
                }
                else if(num==3) {
                    fatigueInput = 0;
                    questionsTwo.setText("Have you skipped a meal in the last week?");
                    num = 4;
                }
                else if(num==4) {
                    skippedMealsInput = 0;
                    Bundle bundle = new Bundle();
                    bundle.putInt("sexInput",sexInput);
                    bundle.putInt("lossSmellAndTasteInput",lossSmellAndTasteInput);
                    bundle.putInt("coughInput",coughInput);
                    bundle.putInt("fatigueInput",fatigueInput);
                    bundle.putInt("skippedMealsInput",skippedMealsInput);
                    listener.onInputTwoSent(bundle);
                }
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof CovidPageTwoListener) {
            listener = (CovidPageTwoListener) context;
        } else {
            throw new RuntimeException("error two");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }
}