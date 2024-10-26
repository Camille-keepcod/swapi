package com.fr.swapi.business;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

public record Planets(String name,
                      String rotationPeriod,
                      String orbitalPeriod,
                      String diameter,
                      String climate,
                      String gravity,
                      String terrain,
                      String surfaceWater,
                      String population,
                      Set<String> residents,
                      Set<String> films,
                      LocalDateTime created,
                      LocalDateTime edited,
                      URL url) {
}
