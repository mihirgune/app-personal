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

public class Phq9DeciderPage extends Fragment {

    private Phq9DeciderPageListener phq9DeciderPageListener;

    public interface Phq9DeciderPageListener {
        void onInputDeciderSent(int holder);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phq_decider, container, false);
        TextView deciderTextView = v.findViewById(R.id.deciderTextView);
        Button deciderButton = v.findViewById(R.id.deciderButton);

        deciderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phq9DeciderPageListener.onInputDeciderSent(10);
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Phq9QuestionsPage.Phq9QuestionsPageListener) {
            phq9DeciderPageListener = (Phq9DeciderPage.Phq9DeciderPageListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement page listener ");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        phq9DeciderPageListener = null;
    }
}
