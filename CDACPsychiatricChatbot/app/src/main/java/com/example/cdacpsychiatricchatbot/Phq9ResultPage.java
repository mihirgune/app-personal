package com.example.cdacpsychiatricchatbot;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Phq9ResultPage extends Fragment {

    public TextView phq9results;
    private int results;
    private Button phqDoneButton;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phq_result, container, false);

        phq9results = (TextView) v.findViewById(R.id.phq9Result);
        phqDoneButton = (Button) v.findViewById(R.id.phqDoneButton);

        if(getArguments()!=null) {
            results = getArguments().getInt("score");
        }

        String resultString;

        if(results<=4) {
            resultString = "Minimal Depression";
        } else if(results<=9) {
            resultString = "Mild Depression";
        } else if(results<=14) {
            resultString = "Moderate Depression";
        } else if(results<=19) {
            resultString = "Moderately Severe Depression";
        } else {
            resultString = "Severe Depression";
        }

        phq9results.setText(resultString);

        phqDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });
        return v;
    }

    public void openHome() {
        Intent intent = new Intent(getActivity(),HomeActivity.class);
        startActivity(intent);
    }
}