package com.fr.swapi.infra.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class ResourceTest {

    @Test
    void testFilmsEndpoint() throws IOException {
        File jsonFile = new File("src/test/resources/expectedFilms.json");
        JsonNode jsonFromFile = new ObjectMapper().readTree(jsonFile);
        given()
                .when().get("/api/films")
                .then()
                .statusCode(200)
                .body(is(jsonFromFile.toString()));
    }
    @Test
    void testPeopleEndpoint() throws IOException {
        File jsonFile = new File("src/test/resources/expectedPeople.json");
        JsonNode jsonFromFile = new ObjectMapper().readTree(jsonFile);
        given()
                .when().get("/api/people")
                .then()
                .statusCode(200)
                .body(is(jsonFromFile.toString()));
    }
    @Test
    void testPlanetsEndpoint() throws IOException {
        File jsonFile = new File("src/test/resources/expectedPlanets.json");
        JsonNode jsonFromFile = new ObjectMapper().readTree(jsonFile);
        given()
                .when().get("/api/planets")
                .then()
                .statusCode(200)
                .body(is(jsonFromFile.toString()));
    }
    @Test
    void testSpaceshipsEndpoint() throws IOException {
        File jsonFile = new File("src/test/resources/expectedSpaceships.json");
        JsonNode jsonFromFile = new ObjectMapper().readTree(jsonFile);
        given()
                .when().get("/api/spaceships")
                .then()
                .statusCode(200)
                .body(is(jsonFromFile.toString()));
    }
    @Test
    void testSpeciesEndpoint() throws IOException {
        File jsonFile = new File("src/test/resources/expectedSpecies.json");
        JsonNode jsonFromFile = new ObjectMapper().readTree(jsonFile);
        given()
                .when().get("/api/species")
                .then()
                .statusCode(200)
                .body(is(jsonFromFile.toString()));
    }
    @Test
    void testVehiclesEndpoint() throws IOException {
        File jsonFile = new File("src/test/resources/expectedVehicles.json");
        JsonNode jsonFromFile = new ObjectMapper().readTree(jsonFile);
        given()
                .when().get("/api/vehicles")
                .then()
                .statusCode(200)
                .body(is(jsonFromFile.toString()));
    }
}
