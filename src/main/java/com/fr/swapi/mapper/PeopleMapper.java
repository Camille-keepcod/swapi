package com.fr.swapi.mapper;

import com.fr.swapi.business.People;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PeopleMapper {
    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());

    public static People mapper(Map<String, Object> map) {
        try {
            return new People(
                    String.valueOf(map.get("name")),
                    String.valueOf(map.get("height")),
                    String.valueOf(map.get("mass")),
                    String.valueOf(map.get("hair_color")),
                    String.valueOf(map.get("skin_color")),
                    String.valueOf(map.get("eye_color")),
                    String.valueOf(map.get("birth_year")),
                    String.valueOf(map.get("gender")),
                    new URL(String.valueOf(map.get("homeworld"))),
                    ((List<Object>) map.get("films")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("species")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("vehicles")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("starships")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    LocalDateTime.parse(String.valueOf(map.get("created")), formatterDateTime),
                    LocalDateTime.parse(String.valueOf(map.get("edited")), formatterDateTime),
                    new URL(String.valueOf(map.get("url")))
                    );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
