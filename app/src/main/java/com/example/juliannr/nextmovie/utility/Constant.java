package com.example.juliannr.nextmovie.utility;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface Constant {
    String GITHUB_LINK = "https://github.com/jrizani/NextMovie";

    interface Api{
        String BASE_URL = "https://Api.themoviedb.org/3/movie/";
        String API_KEY = "1758b70f5564761e74d97cf6d5ec9d28";
        String IMAGE_PATH = "https://image.tmdb.org/t/p/w500";
        String RATING = "\u2605 ";
    }
    interface FragmentChooser{
        String NOW_PLAYING = "Now Playing";
        String TOP_RATED = "Top Rated";
        String UPCOMING = "Upcoming";
        String POPULAR = "Popular";
        String FAVORITE = "Favorite";
    }
}
