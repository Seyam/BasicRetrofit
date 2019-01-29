package com.example.basicretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView texViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
        //here I have implemented the jsonPlaceholderAPI interface by calling retrofit.create function
        // that creates corresponding class that implements the interface

        Call<List<Posts>> call =   jsonPlaceholderAPI.getPosts();
        // calling the interface method getPosts which is auto implemented by retrofit by creating
        // JsonPlaceholderAPI.class

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if(!response.isSuccessful()){
                    texViewResult.setText("Code: "+ response.code());
                    return; //to leave this method from here
                }

                List<Posts> posts = response.body();

                for(Posts post : posts){
                    String content="";
                    content += "ID: "+post.getId()+"\n";
                    content += "User ID: "+post.getUserId()+"\n";
                    content += "Title: "+post.getTitle()+"\n";
                    content += "Text: "+post.getText()+"\n\n";

                    texViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) { //Throwable is super class of exceptions

                texViewResult.setText(t.getMessage());

            }
        });
    }
}
