package com.example.cdacpsychiatricchatbot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DefaultPage extends Fragment {

    Button loginButton,signUpButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_default_page, container, false);

        loginButton = (Button) v.findViewById(R.id.loginButton);
        signUpButton = (Button) v.findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        return v;
    }

    private void openLogin() {
        LoginPage loginFragment = new LoginPage();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer,loginFragment);
        fragmentTransaction.commit();
    }

    private void openSignUp() {
        SignUpPage signupFragment = new SignUpPage();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer,signupFragment);
        fragmentTransaction.commit();
    }
}