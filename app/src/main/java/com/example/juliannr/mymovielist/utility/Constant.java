package com.example.juliannr.mymovielist.utility;

/**
 * Created by Julian Noor Rizani on 23/01/18.
 * Email: julian.rizani@iconpln.co.id
 */

public interface Constant {
    interface Api{
        static String BASE_URL = "https://Api.themoviedb.org/3/movie/";
        static String API_KEY = "1758b70f5564761e74d97cf6d5ec9d28";
        static String IMAGE_PATH = "https://image.tmdb.org/t/p/w500";
    }
    interface FragmentChooser{
        static String NOW_PLAYING = "Now Playing";
        static String TOP_RATED = "Top Rated";
        static String UPCOMING = "Upcoming";
    }
}
