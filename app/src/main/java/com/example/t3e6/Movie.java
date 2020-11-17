package com.example.t3e6;

import android.graphics.drawable.Drawable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie {
    private String movieName = null;
    private String theaterName = null;
    private String directorName = null;
    private String summary = null;
    private Date releaseDate = null;
    private Integer minutes = null;
    private Integer coverId = null;
    private AgeRating ageRating = null;
    private boolean isFavorite = false;
    private String youtubeId = null;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");

    public Movie() {
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }

    public AgeRating getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRating ageRating) {
        this.ageRating = ageRating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getFormattedReleaseDate() {
        return dateFormat.format(releaseDate);
    }


    public static enum AgeRating {
        G("G", R.drawable.g),
        NC17("NC17", R.drawable.nc17),
        PG("PG", R.drawable.pg),
        PG13("PG13", R.drawable.pg13),
        R_("R", R.drawable.r);

        private int resId;
        private String text;

        AgeRating(String text, int resId) {
            this.text = text;
            this.resId = resId;
        }

        public int getResId() {
            return resId;
        }

        public String getText() {
            return text;
        }
    };
}
