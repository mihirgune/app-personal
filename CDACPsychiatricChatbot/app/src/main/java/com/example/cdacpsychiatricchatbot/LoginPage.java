package com.example.cdacpsychiatricchatbot;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends Fragment {

    private Button loginButton;
    private EditText textPassword;
    private EditText textEmailAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_login_page, container, false);

        loginButton = (Button) v.findViewById(R.id.loginButton);
        textPassword = (EditText) v.findViewById(R.id.textPassword);
        textEmailAddress = (EditText) v.findViewById(R.id.textEmailAddress);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        return v;
    }

    private void openMain() {
        String checkEmail = textEmailAddress.getText().toString();
        String checkPassword = textPassword.getText().toString();
        if(checkEmail.equals("admin") && checkPassword.equals("12345")) {
            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
        } else {
            DefaultPage defaultPage = new DefaultPage();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, defaultPage).commit();
        }

    }
}