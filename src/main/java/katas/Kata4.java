package katas;

import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    /*
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", new BoxArt(150, 200, "url")));
    }
    */

    public static List<Map> execute(){
        List<MovieList> movieLists = DataUtil.getMovieLists();
        String width = "150";
        String heigth = "200";

        return movieLists.stream()
                .flatMap(movieList -> movieList.getVideos().stream())
                .map(movie -> Map.of("id", movie.getId(),
                                     "title", movie.getTitle(),
                                     "box", movie.getBoxarts()
                                                .stream()
                                                .filter(box  -> box.getWidth().equals(width) &&
                                                                box.getHeight().equals(heigth))
                        )).collect(Collectors.toList());
    }
}
