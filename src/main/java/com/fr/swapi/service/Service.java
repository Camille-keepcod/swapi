package com.fr.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.swapi.business.*;
import com.fr.swapi.infra.client.SwapiResourceClient;

import com.fr.swapi.mapper.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class Service {
    @Inject
    @RestClient
    SwapiResourceClient client;

    @ConfigProperty(name = "swapi.get.people")
    protected String urlPeople;
    @ConfigProperty(name = "swapi.get.planets")
    protected String urlPlanets;
    @ConfigProperty(name = "swapi.get.starships")
    protected String urlSpaceships;
    @ConfigProperty(name = "swapi.get.species")
    protected String urlSpecies;
    @ConfigProperty(name = "swapi.get.vehicles")
    protected String urlVehicles;

    public Set<Film> getFilms() {
        return Set.of(
                FilmsMapper.mapper(getMapObject(this.client.getFilm(1))),
                FilmsMapper.mapper(getMapObject(this.client.getFilm(2))),
                FilmsMapper.mapper(getMapObject(this.client.getFilm(3))),
                FilmsMapper.mapper(getMapObject(this.client.getFilm(4))),
                FilmsMapper.mapper(getMapObject(this.client.getFilm(5))),
                FilmsMapper.mapper(getMapObject(this.client.getFilm(6)))
        ).stream().sorted(Comparator.comparing(Film::episodeId)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Map<String,Set<People>> getPeople(Set<Film> films) {
        Map<String,Set<People>> map = new TreeMap<>();
        films.forEach(film -> map.put(film.episodeId(),film.characters().stream()
                .map(url -> url.replace(urlPeople,"").replace("/",""))
                .map(id -> PeopleMapper.mapper(getMapObject(this.client.getPeople(id)))).collect(Collectors.toSet())));
        return map;
    }

    public Map<String,Set<Planets>> getPlanets(Set<Film> films) {
        Map<String,Set<Planets>> map = new TreeMap<>();
        films.forEach(film -> map.put(film.episodeId(),film.planets().stream()
                .map(url -> url.replace(urlPlanets,"").replace("/",""))
                .map(id -> PlanetsMapper.mapper(getMapObject(this.client.getPlanets(id)))).collect(Collectors.toSet())));
        return map;
    }

    public Map<String,Set<Spaceships>> getSpaceships(Set<Film> films) {
        Map<String,Set<Spaceships>> map = new TreeMap<>();
        films.forEach(film -> map.put(film.episodeId(),film.starships().stream()
                .map(url -> url.replace(urlSpaceships,"").replace("/",""))
                .map(id -> SpaceshipsMapper.mapper(getMapObject(this.client.getSpaceships(id)))).collect(Collectors.toSet())));
        return map;
    }

    public Map<String,Set<Species>> getSpecies(Set<Film> films) {
        Map<String,Set<Species>> map = new TreeMap<>();
        films.forEach(film -> map.put(film.episodeId(),film.species().stream()
                .map(url -> url.replace(urlSpecies,"").replace("/",""))
                .map(id -> SpeciesMapper.mapper(getMapObject(this.client.getSpecies(id)))).collect(Collectors.toSet())));
        return map;
    }

    public Map<String,Set<Vehicles>> getVehicles(Set<Film> films) {
        Map<String,Set<Vehicles>> map = new TreeMap<>();
        films.forEach(film -> map.put(film.episodeId(),film.vehicles().stream()
                .map(url -> url.replace(urlVehicles,"").replace("/",""))
                .map(id -> VehiclesMapper.mapper(getMapObject(this.client.getVehicles(id)))).collect(Collectors.toSet())));
        return map;
    }

    private Map<String, Object> getMapObject(String response) {
        try {
            return new ObjectMapper().readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
