package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Phq9TesterActivity extends AppCompatActivity implements Phq9QuestionsPage.Phq9QuestionsPageListener, Phq9DeciderPage.Phq9DeciderPageListener {
    private Phq9QuestionsPage phq9QuestionsPage;
    private Phq9ResultPage phq9ResultPage;
    private Phq9DeciderPage phq9DeciderPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phq9_tester2);

        Phq9DeciderPage phq9DeciderPage = new Phq9DeciderPage();
        getSupportFragmentManager().beginTransaction().replace(R.id.phqContainer, phq9DeciderPage).commit();
    }

    @Override
    public void onInputQuestionsSent(int score) {
        Phq9ResultPage phq9ResultPage = new Phq9ResultPage();
        Bundle args = new Bundle();
        args.putInt("score",score);
        phq9ResultPage.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.phqContainer,phq9ResultPage).commit();
    }

    @Override
    public void onInputDeciderSent(int holder) {
        Phq9QuestionsPage phq9QuestionsPage = new Phq9QuestionsPage();
        getSupportFragmentManager().beginTransaction().replace(R.id.phqContainer,phq9QuestionsPage).commit();
    }
}