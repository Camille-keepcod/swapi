package com.fr.swapi.business;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record Film(String title,
                   String episodeId,
                   StringBuilder openingCrawl,
                   String director,
                   String producer,
                   LocalDate releaseDate,
                   Set<String>characters,
                   Set<String> planets,
                   Set<String> starships,
                   Set<String> vehicles,
                   Set<String> species,
                   LocalDateTime created,
                   LocalDateTime edited,
                   URL url) {
}
