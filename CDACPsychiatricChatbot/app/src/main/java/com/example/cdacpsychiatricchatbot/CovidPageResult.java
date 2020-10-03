package com.example.cdacpsychiatricchatbot;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CovidPageResult extends Fragment {

    private TextView resultDisplay,probDisplay;
    private Button doneButton;
    private Double probability;
    private int ssCheck;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_covid_page_result,container,false);

        resultDisplay = (TextView) v.findViewById(R.id.resultDisplay);
        probDisplay = (TextView) v.findViewById(R.id.probDisplay);
        doneButton = (Button) v.findViewById(R.id.doneButton);

        if(getArguments()!=null) {
            probability = getArguments().getDouble("answer");
            ssCheck = getArguments().getInt("ssCheck");
        }

        String p = String.valueOf("The probability is "  + probability);
        probDisplay.setText(p);

        if(probability>0.5) {
            resultDisplay.setText("You may have COVID-19. Please get yourself tested");
        } else {
            if(ssCheck>0) {
                resultDisplay.setText("You have starting symptoms. Please make a precautionary visit to a doctor");
            } else {
                resultDisplay.setText("You have no COVID-19 symptoms.");
            }
        }



        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        return v;
    }

    private void openHome() {
        Intent intent = new Intent(getActivity(),HomeActivity.class);
        startActivity(intent);
    }
}