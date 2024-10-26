package com.fr.swapi.business;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

public record Species(String name,
                      String classification,
                      String designation,
                      String averageHeight,
                      String skinColors,
                      String hairColors,
                      String eyeColors,
                      String averageLifespan,
                      String homeworld,
                      String language,
                      Set<String> people,
                      Set<String> films,
                      LocalDateTime created,
                      LocalDateTime edited,
                      URL url) {
}
