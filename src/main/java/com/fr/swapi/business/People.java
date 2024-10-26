package com.fr.swapi.business;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

public record People(String name,
                     String height,
                     String mass,
                     String hairColor,
                     String skinColor,
                     String eyeColor,
                     String birthYear,
                     String gender,
                     URL homeworld,             // todel
                     Set<String> films ,   // todel
                     Set<String> species,       // todel
                     Set<String> vehicles,     // todel
                     Set<String> starships,      // todel
                     LocalDateTime created,
                     LocalDateTime edited,
                     URL url) {
}
