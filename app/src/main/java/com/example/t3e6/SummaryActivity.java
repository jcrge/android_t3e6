package com.example.t3e6;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {
    private Movie movie;

    private ImageView summaryCoverImage;
    private TextView summaryText;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryCoverImage = findViewById(R.id.summaryCoverImage);
        summaryText = findViewById(R.id.summaryText);

        int moviePos = getIntent().getIntExtra(DetailedViewActivity.EXTRA_SELECTED_MOVIE, 0);
        movie = MovieList.getInstance().get(moviePos);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(movie.getMovieName());
        actionBar.setDisplayHomeAsUpEnabled(true);

        summaryCoverImage.setImageResource(movie.getCoverId());
        summaryText.setText(movie.getSummary());

        summaryCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = movie.getYoutubeId();

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                try {
                    startActivity(appIntent);
                } catch (ActivityNotFoundException e) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + id));
                    startActivity(webIntent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}