package com.fr.swapi.mapper;

import com.fr.swapi.business.Spaceships;
import com.fr.swapi.business.Vehicles;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehiclesMapper {
    private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault());

    public static Vehicles mapper(Map<String, Object> map) {
        try {
            return new Vehicles(
                    String.valueOf(map.get("name")),
                    String.valueOf(map.get("model")),
                    String.valueOf(map.get("manufacturer")),
                    String.valueOf(map.get("cost_in_credits")),
                    String.valueOf(map.get("length")),
                    String.valueOf(map.get("max_atmosphering_speed")),
                    String.valueOf(map.get("crew")),
                    String.valueOf(map.get("passengers")),
                    String.valueOf(map.get("cargo_capacity")),
                    String.valueOf(map.get("consumables")),
                    String.valueOf(map.get("vehicle_class")),
                    ((List<Object>) map.get("pilots")).stream().map(String::valueOf).collect(Collectors.toSet()),
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
