package com.example.t3e6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;

public class AddMovieActivity extends AppCompatActivity {
    private static String CONFIRM_DIALOG_TAG = "com.example.t3e6.CONFIRM_DIALOG_TAG";
    public static String EXTRA_MOVIE = "com.example.t3e6.EXTRA_MOVIE";

    private EditText nameEdit;
    private EditText directorEdit;
    private EditText durationEdit;
    private Spinner theaterSpinner;
    private RadioGroup ageRadioGroup;
    private RadioButton radioR;
    private RadioButton radioG;
    private RadioButton radioNC17;
    private RadioButton radioPG;
    private RadioButton radioPG13;
    private CalendarView releaseDateCalendar;

    private ArrayAdapter<String> theaterAdapter;
    private String[] theaters;
    private String selectedTheater;
    private Movie.AgeRating selectedAgeRating;
    private Calendar selectedReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        nameEdit = findViewById(R.id.newName);
        directorEdit = findViewById(R.id.newDirector);
        durationEdit = findViewById(R.id.newDuration);
        theaterSpinner = findViewById(R.id.newTheater);
        ageRadioGroup = findViewById(R.id.newAgeGroup);
        radioR = findViewById(R.id.radioR);
        radioG = findViewById(R.id.radioG);
        radioNC17 = findViewById(R.id.radioNC17);
        radioPG = findViewById(R.id.radioPG);
        radioPG13 = findViewById(R.id.radioPG13);
        releaseDateCalendar = findViewById(R.id.newReleaseDate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        theaters = getResources().getStringArray(R.array.theaters);
        theaterAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, theaters);

        theaterSpinner.setAdapter(theaterAdapter);
        theaterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTheater = theaters[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        radioR.setText(Movie.AgeRating.R_.getText());
        radioR.setTag(Movie.AgeRating.R_);
        radioG.setText(Movie.AgeRating.G.getText());
        radioG.setTag(Movie.AgeRating.G);
        radioNC17.setText(Movie.AgeRating.NC17.getText());
        radioNC17.setTag(Movie.AgeRating.NC17);
        radioPG.setText(Movie.AgeRating.PG.getText());
        radioPG.setTag(Movie.AgeRating.PG);
        radioPG13.setText(Movie.AgeRating.PG13.getText());
        radioPG13.setTag(Movie.AgeRating.PG13);
        ageRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedAgeRating = (Movie.AgeRating)group.findViewById(checkedId).getTag();
            }
        });

        releaseDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedReleaseDate.set(year, month, dayOfMonth);
            }
        });
        selectedReleaseDate = Calendar.getInstance();
        selectedReleaseDate.setTimeInMillis(releaseDateCalendar.getDate());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.menuConfirmAdd:
                acceptClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    public static class ConfirmQuit extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.confirm_quit_message);
            builder.setTitle(R.string.confirm_quit_title);

            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((AddMovieActivity)getActivity()).confirmCancel();
                }
            });

            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            return builder.create();
        }
    }

    private void confirmCancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void cancelClicked(View v) {
        onBackPressed();
    }

    public void acceptClicked(View v) {
        acceptClicked();
    }

    public void acceptClicked() {
        String name = nameEdit.getText().toString().trim();
        String director = directorEdit.getText().toString().trim();

        if (name.length() == 0 || director.length() == 0 || selectedAgeRating == null)
        {
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationEdit.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }

        Movie movie = new Movie();
        movie.setMovieName(name);
        movie.setDirectorName(director);
        movie.setMinutes(duration);
        movie.setTheaterName(selectedTheater);
        movie.setReleaseDate(selectedReleaseDate.getTime());
        movie.setAgeRating(selectedAgeRating);
        movie.setYoutubeId("oHg5SJYRHA0");
        movie.setSummary("Sinopsis no disponible.");
        movie.setCoverId(R.drawable.sincara);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_MOVIE, movie);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        ConfirmQuit dialog = new ConfirmQuit();
        dialog.show(getSupportFragmentManager(), CONFIRM_DIALOG_TAG);
    }
}