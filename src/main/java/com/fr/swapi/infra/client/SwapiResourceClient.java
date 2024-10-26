package com.fr.swapi.infra.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient(configKey = "swapi-api")
public interface SwapiResourceClient {
    @Path("films/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getFilm(@PathParam("id") int id);

    @Path("people/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getPeople(@PathParam("id") String id);

    @Path("planets/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getPlanets(@PathParam("id") String id);

    @Path("starships/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getSpaceships(@PathParam("id") String id);

    @Path("species/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getSpecies(@PathParam("id") String id);

    @Path("vehicles/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVehicles(@PathParam("id") String id);
}
