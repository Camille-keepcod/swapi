package com.fr.swapi.mapper;

import com.fr.swapi.business.Film;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FilmsMapper {
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);
    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());

    public static Film mapper(Map<String, Object> map) {
        try {
            return new Film(
                    String.valueOf(map.get("title")),
                    String.valueOf(map.get("episode_id")),
                    new StringBuilder(String.valueOf(map.get("opening_crawl"))),
                    String.valueOf(map.get("director")),
                    String.valueOf(map.get("producer")),
                    LocalDate.parse(String.valueOf(map.get("release_date")), formatterDate),
                    ((List<Object>) map.get("characters")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("planets")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("starships")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("vehicles")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("species")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    LocalDateTime.parse(String.valueOf(map.get("created")), formatterDateTime),
                    LocalDateTime.parse(String.valueOf(map.get("edited")), formatterDateTime),
                    new URL(String.valueOf(map.get("url"))));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
