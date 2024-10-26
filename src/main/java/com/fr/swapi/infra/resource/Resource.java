package com.fr.swapi.infra.resource;

import com.fr.swapi.business.*;
import com.fr.swapi.service.Service;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;
import java.util.Set;

@Startup
@Path("/api")
@ApplicationScoped
public class Resource {
    private final Service service;
    Set<Film> films;
    Map<String,Set<People>> people;
    Map<String,Set<Planets>> planets;
    Map<String,Set<Spaceships>> spaceships;
    Map<String,Set<Species>> species;
    Map<String,Set<Vehicles>> vehicles;

    public Resource(final Service service) {
        this.service = service;
    }

    public void onApplicationStart(@Observes StartupEvent event) {
        this.films = this.service.getFilms();
        this.people = this.service.getPeople(this.films);
        this.planets = this.service.getPlanets(this.films);
        this.spaceships = this.service.getSpaceships(this.films);
        this.species = this.service.getSpecies(this.films);
        this.vehicles = this.service.getVehicles(this.films);
    }

    @GET
    @Path("films")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Film> getFilms() {
        return this.films;
    }

    @GET
    @Path("people")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Set<People>> getPeople() {
        return this.people;
    }

    @GET
    @Path("planets")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Set<Planets>> getPlanets() {
        return this.planets;
    }

    @GET
    @Path("spaceships")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Set<Spaceships>> getSpaceships() {
        return this.spaceships;
    }

    @GET
    @Path("species")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Set<Species>> getSpecies() {
        return this.species;
    }

    @GET
    @Path("vehicles")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Set<Vehicles>> getVehicles() {
        return this.vehicles;
    }

}
