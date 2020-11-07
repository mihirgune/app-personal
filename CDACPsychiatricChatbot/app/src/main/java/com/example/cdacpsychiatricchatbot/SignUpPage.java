package com.example.cdacpsychiatricchatbot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SignUpPage extends Fragment {

    private Button signUpConfirmButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sign_up_page, container, false);

        signUpConfirmButton = (Button) v.findViewById(R.id.signUpConfirmButton);
        signUpConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginFromSign();
            }
        });

        return v;
    }

    private void openLoginFromSign() {
        LoginPage loginPage = new LoginPage();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,loginPage).commit();
    }
}