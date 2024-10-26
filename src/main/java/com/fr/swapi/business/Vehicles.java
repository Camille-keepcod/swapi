package com.fr.swapi.business;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Set;

public record Vehicles(String name,
                       String model,
                       String manufacturer,
                       String costInCredits,
                       String length,
                       String maxAtmospheringSpeed,
                       String crew,
                       String passengers,
                       String cargoCapacity,
                       String consumables,
                       String vehicleClass,
                       Set<String> pilots,
                       Set<String> films,
                       LocalDateTime created,
                       LocalDateTime edited,
                       URL url) {
}
