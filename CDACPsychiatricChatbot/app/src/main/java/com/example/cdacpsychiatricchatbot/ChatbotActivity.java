package com.example.cdacpsychiatricchatbot;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatbotActivity extends AppCompatActivity {
    private LinearLayout scrollViewLayout;
    Button sendButton,exitButton;
    private EditText chatbot_input;
    JsonPlaceholderAPI jsonPlaceHolderApi;
    private ScrollView myScrollView;
    int queryType = 0;
    int responseType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot);

        exitButton = (Button) findViewById(R.id.exit_button);
        scrollViewLayout = (LinearLayout) findViewById(R.id.scrollViewLayout);
        sendButton = (Button) findViewById(R.id.send_button);
        chatbot_input = (EditText) findViewById(R.id.chatbot_input);
        myScrollView = (ScrollView) findViewById(R.id.chatScrollView);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPHQ9decider();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = chatbot_input.getText().toString();
                chatbot_input.setText("");
                scrollViewLayout.addView(createTextView(temp,0));
                connect(temp);
                myScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void openPHQ9decider() {
        Intent intent = new Intent(this,Phq9TesterActivity.class);
        startActivity(intent);
    }

    private void connect(String temp) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //creating the JsonPlaceHolderAPI
        jsonPlaceHolderApi = retrofit.create(JsonPlaceholderAPI.class);

        //Creating a new object of the class post with the input string as a parameter
        Post post = new Post(temp);

        //executing the call
        Call<Post> call = jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()) {
                    //Connection successful, response unsuccessful
                    return;
                }

                //Connection successful, response successful
                Post postResponse = response.body();

                //Inserting response into the responseView textView
                String content = "";
                content += postResponse.getUserQuery();
                scrollViewLayout.addView(createTextView(content,1));
                myScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                //No connection
            }
        });
    }

    private TextView createTextView(String text, int type) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        if(type==responseType) {
            lparams.gravity = Gravity.RIGHT;
            lparams.setMargins(150,0,40,50);
            textView.setLayoutParams(lparams);
            textView.setText(text);
            textView.setBackgroundResource(R.drawable.rounded_orange_button);

        } else if(type==queryType) {
            lparams.gravity = Gravity.LEFT;
            lparams.setMargins(40,0,150,50);
            textView.setLayoutParams(lparams);
            textView.setText(text);
            textView.setBackgroundResource(R.drawable.rounded_blue_button);

        }
        textView.setTextSize(20);
        textView.setPadding(15,15,15,15);
        return textView;
    }

}