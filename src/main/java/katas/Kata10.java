package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/*
    Goal: Create a datastructure from the given data:

    We have 2 arrays each containing lists, and videos respectively.
    Each video has a listId field indicating its parent list.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id and title.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber"
                },
                {
                    "id": 675465,
                    "title": "Fracture"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos()
    Output: the given datastructure
*/
public class Kata10 {
    /*
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();

        return ImmutableList.of(ImmutableMap.of("name", "someName", "videos", ImmutableList.of(
                ImmutableMap.of("id", 5, "title", "The Chamber"),
                ImmutableMap.of("id", 3, "title", "Fracture")
        )));
    }
    */
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        List<Map> boxArts = DataUtil.getBoxArts();
        List<Map> bookmarkList = DataUtil.getBookmarkList();

        return lists.stream()
                .map(genero -> Map.of("name", genero.get("name"),
                        "videos", videos.stream().filter(video -> Objects.equals(
                                        genero.get("id"),
                                        video.get("listId")))
                                            .map(video -> Map.of(
                                                     "id", video.get("id"),
                                                     "title", video.get("title"),
                                                     "boxart", boxArts.stream()
                                                        .filter(boxArt -> Objects.equals(
                                                                boxArt.get("videoId"),
                                                                video.get("id"))).map(
                                                                        boxArt -> boxArt.get("url")),
                                                                    "time", bookmarkList.stream()
                                                                            .filter(bookmark -> Objects.equals(
                                                                                    bookmark.get("videoId"),
                                                                                    video.get("id")))
                                                                .map(bookmark -> bookmark.get("time")))
                                        ).collect(Collectors.toList())
                        )).collect(Collectors.toList());
    }
}
