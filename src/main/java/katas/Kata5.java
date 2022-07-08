package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;

import static java.lang.System.err;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    /*
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();

        return 3.0;
    }
    */

    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();
        String error = "not found largest rating";
        return movies.stream()
                .map(Movie::getRating)
                .reduce(Double::max)
                .orElseThrow();
    }
}
