package com.example.cdacpsychiatricchatbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CovidPageOne extends Fragment {

    private TextView questionsOne;
    private EditText editTextOne;
    private Button enterOne;
    private CovidPageOneListener listener;

    public interface CovidPageOneListener {
        void onInputOneSent(CharSequence input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_covid_page_one,container,false);

        questionsOne = (TextView) v.findViewById(R.id.questionsOne);
        editTextOne = (EditText) v.findViewById(R.id.editTextOne);
        enterOne = (Button) v.findViewById(R.id.enterOne);

        questionsOne.setText("Enter your age");

        enterOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence input = editTextOne.getText();
                listener.onInputOneSent(input);
            }
        });
        return v;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof CovidPageOneListener) {
            listener = (CovidPageOneListener) context;
        } else {
            throw new RuntimeException("error one");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }
}