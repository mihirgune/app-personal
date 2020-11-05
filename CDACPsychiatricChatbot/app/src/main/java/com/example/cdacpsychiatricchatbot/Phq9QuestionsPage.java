package com.example.cdacpsychiatricchatbot;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Phq9QuestionsPage extends Fragment {

    private int phq9Score = 0;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private TextView phq9Questions;
    private int pivot = 1;
    private Phq9QuestionsPageListener phq9QuestionsPageListener;

    public interface Phq9QuestionsPageListener {
        void onInputQuestionsSent(int score);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phq_questions, container, false);

        buttonA = (Button) v.findViewById(R.id.buttonA);
        buttonB = (Button) v.findViewById(R.id.buttonB);
        buttonC = (Button) v.findViewById(R.id.buttonC);
        buttonD = (Button) v.findViewById(R.id.buttonD);

        phq9Questions = (TextView) v.findViewById(R.id.phq9Questions);
        phq9Questions.setText(R.string.phq9QuestionOne);

        //button value 0
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                if(pivot==1) {
                    phq9Questions.setText(R.string.phq9QuestionTwo);
                    pivot = 2;
                } else if(pivot==2) {
                    phq9Questions.setText(R.string.phq9QuestionThree);
                    pivot = 3;
                } else if(pivot==3) {
                    phq9Questions.setText(R.string.phq9QuestionFour);
                    pivot = 4;
                } else if(pivot==4) {
                    phq9Questions.setText(R.string.phq9QuestionFive);
                    pivot = 5;
                } else if(pivot==5) {
                    phq9Questions.setText(R.string.phq9QuestionSix);
                    pivot = 6;
                } else if(pivot==6) {
                    phq9Questions.setText(R.string.phq9QuestionSeven);
                    pivot = 7;
                } else if(pivot==7) {
                    phq9Questions.setText(R.string.phq9QuestionEight);
                    pivot = 8;
                } else if(pivot==8) {
                    phq9Questions.setText(R.string.phq9QuestionNine);
                    pivot = 9;
                } else {
                    phq9QuestionsPageListener.onInputQuestionsSent(phq9Score);
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phq9Score = phq9Score + 1;
                if (pivot == 1) {
                    phq9Questions.setText(R.string.phq9QuestionTwo);
                    pivot = 2;
                } else if (pivot == 2) {
                    phq9Questions.setText(R.string.phq9QuestionThree);
                    pivot = 3;
                } else if (pivot == 3) {
                    phq9Questions.setText(R.string.phq9QuestionFour);
                    pivot = 4;
                } else if (pivot == 4) {
                    phq9Questions.setText(R.string.phq9QuestionFive);
                    pivot = 5;
                } else if (pivot == 5) {
                    phq9Questions.setText(R.string.phq9QuestionSix);
                    pivot = 6;
                } else if (pivot == 6) {
                    phq9Questions.setText(R.string.phq9QuestionSeven);
                    pivot = 7;
                } else if (pivot == 7) {
                    phq9Questions.setText(R.string.phq9QuestionEight);
                    pivot = 8;
                } else if (pivot == 8) {
                    phq9Questions.setText(R.string.phq9QuestionNine);
                    pivot = 9;
                } else {
                    phq9QuestionsPageListener.onInputQuestionsSent(phq9Score);
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phq9Score = phq9Score + 2;
                if(pivot==1) {
                    phq9Questions.setText(R.string.phq9QuestionTwo);
                    pivot = 2;
                } else if(pivot==2) {
                    phq9Questions.setText(R.string.phq9QuestionThree);
                    pivot = 3;
                } else if(pivot==3) {
                    phq9Questions.setText(R.string.phq9QuestionFour);
                    pivot = 4;
                } else if(pivot==4) {
                    phq9Questions.setText(R.string.phq9QuestionFive);
                    pivot = 5;
                } else if(pivot==5) {
                    phq9Questions.setText(R.string.phq9QuestionSix);
                    pivot = 6;
                } else if(pivot==6) {
                    phq9Questions.setText(R.string.phq9QuestionSeven);
                    pivot = 7;
                } else if(pivot==7) {
                    phq9Questions.setText(R.string.phq9QuestionEight);
                    pivot = 8;
                } else if(pivot==8) {
                    phq9Questions.setText(R.string.phq9QuestionNine);
                    pivot = 9;
                } else {
                    phq9QuestionsPageListener.onInputQuestionsSent(phq9Score);
                }
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phq9Score = phq9Score + 3;
                if(pivot==1) {
                    phq9Questions.setText(R.string.phq9QuestionTwo);
                    pivot = 2;
                } else if(pivot==2) {
                    phq9Questions.setText(R.string.phq9QuestionThree);
                    pivot = 3;
                } else if(pivot==3) {
                    phq9Questions.setText(R.string.phq9QuestionFour);
                    pivot = 4;
                } else if(pivot==4) {
                    phq9Questions.setText(R.string.phq9QuestionFive);
                    pivot = 5;
                } else if(pivot==5) {
                    phq9Questions.setText(R.string.phq9QuestionSix);
                    pivot = 6;
                } else if(pivot==6) {
                    phq9Questions.setText(R.string.phq9QuestionSeven);
                    pivot = 7;
                } else if(pivot==7) {
                    phq9Questions.setText(R.string.phq9QuestionEight);
                    pivot = 8;
                } else if(pivot==8) {
                    phq9Questions.setText(R.string.phq9QuestionNine);
                    pivot = 9;
                } else {
                    phq9QuestionsPageListener.onInputQuestionsSent(phq9Score);
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Phq9QuestionsPageListener) {
            phq9QuestionsPageListener = (Phq9QuestionsPageListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement page listener ");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        phq9QuestionsPageListener = null;
    }
}

