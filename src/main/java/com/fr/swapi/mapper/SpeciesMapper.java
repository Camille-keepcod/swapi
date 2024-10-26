package com.fr.swapi.mapper;

import com.fr.swapi.business.Species;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpeciesMapper {
    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());

    public static Species mapper(Map<String, Object> map) {
        try {
            return new Species(
                    String.valueOf(map.get("name")),
                    String.valueOf(map.get("classification")),
                    String.valueOf(map.get("designation")),
                    String.valueOf(map.get("average_height")),
                    String.valueOf(map.get("skin_colors")),
                    String.valueOf(map.get("hair_colors")),
                    String.valueOf(map.get("eye_colors")),
                    String.valueOf(map.get("average_lifespan")),
                    String.valueOf(map.get("homeworld")),
                    String.valueOf(map.get("language")),
                    ((List<Object>) map.get("people")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    ((List<Object>) map.get("films")).stream().map(String::valueOf).collect(Collectors.toSet()),
                    LocalDateTime.parse(String.valueOf(map.get("created")), formatterDateTime),
                    LocalDateTime.parse(String.valueOf(map.get("edited")), formatterDateTime),
                    new URL(String.valueOf(map.get("url")))
                    );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
