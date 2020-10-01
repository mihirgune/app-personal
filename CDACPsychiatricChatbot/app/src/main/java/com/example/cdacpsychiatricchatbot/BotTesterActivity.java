package com.example.cdacpsychiatricchatbot;

import android.content.res.AssetFileDescriptor;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BotTesterActivity extends AppCompatActivity {

    TextView responseView;
    EditText enterQuery;
    Button predictButton;
    JsonPlaceholderAPI jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_tester);

        //initializing the layout
        responseView = (TextView) findViewById(R.id.responseView);
        enterQuery = (EditText) findViewById(R.id.enterQuery);
        predictButton = (Button) findViewById(R.id.predictButton);

        //adding an onClickListener to the predict button
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //taking input from the enterQuery editText
                String temp = enterQuery.getText().toString();

                //building connection using RetroFit
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
                            responseView.setText("Unsuccessful");
                            return;
                        }

                        //Connection successful, response successful
                        Post postResponse = response.body();

                        //Inserting response into the responseView textView
                        String content = "";
                        content += postResponse.getUserQuery();
                        responseView.setText(content);
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        //No connection
                        responseView.setText(t.getMessage());
                    }
                });
            }
        });
    }

}
