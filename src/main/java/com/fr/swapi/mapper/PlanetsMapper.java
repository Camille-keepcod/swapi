package com.fr.swapi.mapper;

import com.fr.swapi.business.Planets;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlanetsMapper {
    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());

    public static Planets mapper(Map<String, Object> map) {
        try {
            return new Planets(
                    String.valueOf(map.get("name")),
                    String.valueOf(map.get("rotation_period")),
                    String.valueOf(map.get("orbital_period")),
                    String.valueOf(map.get("diameter")),
                    String.valueOf(map.get("climate")),
                    String.valueOf(map.get("gravity")),
                    String.valueOf(map.get("terrain")),
                    String.valueOf(map.get("surface_water")),
                    String.valueOf(map.get("population")),
                    ((List<Object>) map.get("residents")).stream().map(String::valueOf).collect(Collectors.toSet()),
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
