package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.example.test.adapters.MovieViewHolder;
import com.example.test.models.MovieModel;

import java.util.Locale;

public class MovieRandom extends AppCompatActivity {

    ImageView down,poster,up;

    TextView title,time,note,langue;

    MovieModel movie;

    String strTime,strLangue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_random);

        initialisation();

        getMovie();

        setupMovie(movie);




    }

    private void setupMovie(MovieModel movie) {
        Log.d("movie", "setupMovie: " +strTime);
        title.setText(movie.getTitle());
        time.setText(strTime+" min");
        note.setText(Float.toString(movie.getVote_average()));
        langue.setText(strLangue.toUpperCase());

    }

    private void getMovie() {

        Intent i = getIntent();
        movie = (MovieModel) i.getExtras().get("movie");
        strTime = String.valueOf(i.getExtras().get("time"));
        strLangue = i.getExtras().getString("langue");


        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" +movie.getPoster_path()).into(poster);



    }

    private void initialisation() {

        down = findViewById(R.id.down);
        up = findViewById(R.id.up);
        poster = findViewById(R.id.poster);

        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        note = findViewById(R.id.note);
        langue = findViewById(R.id.langue);


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast t = Toast.makeText(MovieRandom.this,"Vous n'avez pas aimer ce film",Toast.LENGTH_SHORT);
                t.show();
                finish();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "https://www.google.com/search?q="+movie.getTitle()+" movie";
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                startActivity(intent);
                finish();

            }
        });

    }
}