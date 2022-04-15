package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.test.models.MovieModel;
import com.example.test.request.Servicey;
import com.example.test.utils.Credentials;
import com.example.test.utils.MovieApi;

import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn = findViewById(R.id.button);



       btn.setOnClickListener(new View.OnClickListener() {
        Random rand = new Random();
        int i;
            @Override
            public void onClick(View view) {

                i = rand.nextInt(1000);
                GetRetrofitResponseByID(i);
                Log.v("Tag", "valeur de i = " + i);


            }
        });
    }

/*
    private void GetRetrofitResponse() {

        MovieApi movieApi = Servicey.getMovieApi();

        //probleme dans le call
        Call<MovieSearchResponse> responseCall = movieApi
        .searchMovie(
                Credentials.API_KEY,
                "Jack Reacher",
                1);


        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){

                    //Log.v("Tag","the response" +response.body().toString());
                    List<MovieModel> movies;
                    Log.v("Tag","test" );
                    movies = new ArrayList<>(response.body().getMovies());


                    for (MovieModel movie : movies){
                        Log.v("Tag", "The realease date" + movie.getTitle());
                    }
                }

                else{
                    try{
                        Log.v("Tag","Error" + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });






    }
*/
    private  void GetRetrofitResponseByID(int id){

        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                id,
                Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200){

                    MovieModel movie = response.body();
                    Log.v("Tag","The Response " +movie.getTitle());
                    Log.v("Tag","The Response " +movie.getOriginal_language());
                    Log.v("Tag","The Response " +movie.getRuntime());
                    Log.v("Tag","The Response " +movie.getPoster_path());
                    Log.v("Tag","The Response " + movie);

                    Intent i = new Intent(getBaseContext(),MovieRandom.class);
                    i.putExtra("movie", movie);
                    i.putExtra("langue", movie.getOriginal_language());
                    i.putExtra("time", movie.getRuntime());

                    startActivity(i);

                }
                else{
                    try {
                        Random rand = new Random();

                        GetRetrofitResponseByID(rand.nextInt(1000));
                        Log.v("Tag","Error "+response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {


            }
        });


    }




}