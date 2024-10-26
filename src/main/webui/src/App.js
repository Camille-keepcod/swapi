import React, {useState, useEffect } from 'react';
import Accordion from 'react-bootstrap/Accordion';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function Header() { return (<header className="App-header"><img src={logo} className="App-logo" alt="logo"/></header>);}

function App() {
    const [films, setFilms] = useState([]);
    const [searchItem, setSearchItem] = useState('')
    const [filteredFilms, setFilteredFilms] = useState([])

    const handleInputChange = (e) => {
        const searchTerm = e.target.value;
        setSearchItem(searchTerm)
        if (setSearchItem) {
            const filteredItems = films.filter(film => {
                let content = Object.values(film).join('').toLowerCase();
                film.characters.map(character => (content = content.concat(Object.values(character).join('').toLowerCase())));
                film.planets.map(planet => (content = content.concat(Object.values(planet).join('').toLowerCase())));
                film.starships.map(starship => (content = content.concat(Object.values(starship).join('').toLowerCase())));
                film.species.map(specie => (content = content.concat(Object.values(specie).join('').toLowerCase())));
                film.vehicles.map(vehicle => (content = content.concat(Object.values(vehicle).join('').toLowerCase())));
                return content.includes(searchTerm.toLowerCase());
            });
            setFilteredFilms(filteredItems);
        } else {
            setFilteredFilms(films);
        }
    }

    useEffect(() => {
        const filmsAPI = 'http://localhost:8080/api/films';
        const peopleAPI = 'http://localhost:8080/api/people';
        const planetsAPI = 'http://localhost:8080/api/planets';
        const spaceshipsAPI = 'http://localhost:8080/api/spaceships';
        const speciesAPI = 'http://localhost:8080/api/species';
        const vehiclesAPI = 'http://localhost:8080/api/vehicles';

        const getFilms = axios.get(filmsAPI);
        const getPeople = axios.get(peopleAPI);
        const getPlanets = axios.get(planetsAPI);
        const getSpaceships = axios.get(spaceshipsAPI);
        const getSpecies = axios.get(speciesAPI);
        const getVehicles = axios.get(vehiclesAPI);

        axios.all([getFilms, getPeople, getPlanets, getSpaceships, getSpecies, getVehicles]).then(
            axios.spread((...allData) => {
                const films = allData[0].data.sort((film1, film2) => {if (film1.episodeId < film2.episodeId) {return -1;}});
                const people = allData[1].data;
                const planets = allData[2].data;
                const starships = allData[3].data;
                const species = allData[4].data;
                const vehicles = allData[5].data;

                films.forEach((film) => {
                    film.charactersKey = `characters_${film.episodeId}`;
                    film.planetsKey = `planets_${film.episodeId}`;
                    film.starshipsKey = `starships_${film.episodeId}`;
                    film.vehiclesKey = `vehicles_${film.episodeId}`;
                    film.speciesKey = `species_${film.episodeId}`;
                    film.characters = people[film.episodeId];
                    film.planets = planets[film.episodeId];
                    film.starships = starships[film.episodeId];
                    film.species = species[film.episodeId];
                    film.vehicles = vehicles[film.episodeId];

                    people[film.episodeId].forEach((character) => {
                        character.charactersKey = `${film.episodeId}_${character.name}`;
                    })
                })
                setFilms(films);
                setFilteredFilms(films);
            })
        )
    }, []);

    return (
        <div>
            <Header/>
            <div id="accordion_search_bar_container">
                <input
                    id="accordion_search_bar"
                    type="text"
                    value={searchItem}
                    onChange={handleInputChange}
                    placeholder='Type to search'
                />
            </div>
            <Accordion key="1" defaultActiveKey="1">
                {filteredFilms.map(film => (
                    <Accordion.Item key={film.episodeId} eventKey={film.episodeId}>
                        <Accordion.Header key={film.title}><b>{film.title}</b></Accordion.Header>
                        <Accordion.Body>
                            <ul>
                                <li>Director : <b>{film.director}</b></li>
                                <li>Producer : <b>{film.producer}</b></li>
                                <li>Release date : {film.releaseDate}</li>
                                <li>Created : {film.created}</li>
                                <li>Edited : {film.edited}</li>
                            </ul>
                            <p className="opening-crawl">{film.openingCrawl}</p>
                            <Accordion key="submenus-level1">
                                <Accordion.Item key={film.charactersKey} eventKey={film.charactersKey}>
                                    <Accordion.Header><b>Characters</b></Accordion.Header>
                                    <Accordion.Body>
                                        <Accordion key="submenus-level2">
                                            {film.characters.map(character => (
                                                <Accordion.Item key={character.charactersKey}
                                                                eventKey={character.charactersKey}>
                                                    <Accordion.Header
                                                        key={character.name}><b>{character.name}</b></Accordion.Header>
                                                    <Accordion.Body>
                                                        <ul>
                                                            <li>Height : {character.height}</li>
                                                            <li>Mass : {character.mass}</li>
                                                            <li>Hair color : {character.hairColor}</li>
                                                            <li>Skin color : {character.skinColor}</li>
                                                            <li>Eye color : {character.eyeColor}</li>
                                                            <li>Birth year : {character.birthYear}</li>
                                                            <li>Gender : {character.gender}</li>
                                                            <li>Created : {character.created}</li>
                                                            <li>Edited : {character.edited}</li>
                                                        </ul>
                                                    </Accordion.Body>
                                                </Accordion.Item>
                                            ))}
                                        </Accordion>
                                    </Accordion.Body>
                                </Accordion.Item>
                                <Accordion.Item key={film.planetsKey} eventKey={film.planetsKey}>
                                    <Accordion.Header><b>Planets</b></Accordion.Header>
                                    <Accordion.Body>
                                        <Accordion key="submenus-level2">
                                            {film.planets.map(planet => (
                                                <Accordion.Item key={planet.name} eventKey={planet.name}>
                                                    <Accordion.Header><b>{planet.name}</b></Accordion.Header>
                                                    <Accordion.Body>
                                                        <ul>
                                                            <li>Rotation period : {planet.rotationPeriod}</li>
                                                            <li>Orbital period : {planet.orbitalPeriod}</li>
                                                            <li>Diameter : {planet.diameter}</li>
                                                            <li>Climate : {planet.climate}</li>
                                                            <li>Gravity : {planet.gravity}</li>
                                                            <li>Terrain : {planet.terrain}</li>
                                                            <li>Surface water : {planet.surfaceWater}</li>
                                                            <li>Population : {planet.population}</li>
                                                            <li>Created : {planet.created}</li>
                                                            <li>Edited : {planet.edited}</li>
                                                        </ul>
                                                    </Accordion.Body>
                                                </Accordion.Item>
                                            ))}
                                        </Accordion>
                                    </Accordion.Body>
                                </Accordion.Item>
                                <Accordion.Item key={film.starshipsKey} eventKey={film.starshipsKey}>
                                    <Accordion.Header><b>Starships</b></Accordion.Header>
                                    <Accordion.Body>
                                        <Accordion key="submenus-level2">
                                            {film.starships.map(starship => (
                                                <Accordion.Item key={starship.name} eventKey={starship.name}>
                                                    <Accordion.Header><b>{starship.name}</b></Accordion.Header>
                                                    <Accordion.Body>
                                                        <ul>
                                                            <li>Model : {starship.model}</li>
                                                            <li>Manufacturer : {starship.manufacturer}</li>
                                                            <li>Cost in credits : {starship.costInCredits}</li>
                                                            <li>Length : {starship.length}</li>
                                                            <li>Max atmosphering speed
                                                                : {starship.maxAtmospheringSpeed}</li>
                                                            <li>Crew : {starship.crew}</li>
                                                            <li>Passengers : {starship.passengers}</li>
                                                            <li>CargoCapacity : {starship.cargoCapacity}</li>
                                                            <li>Consumables : {starship.consumables}</li>
                                                            <li>HyperdriveRating : {starship.hyperdriveRating}</li>
                                                            <li>MGLT : {starship.MGLT}</li>
                                                            <li>Starship class : {starship.starshipClass}</li>
                                                            <li>Created : {starship.created}</li>
                                                            <li>Edited : {starship.edited}</li>
                                                        </ul>
                                                    </Accordion.Body>
                                                </Accordion.Item>
                                            ))}
                                        </Accordion>
                                    </Accordion.Body>
                                </Accordion.Item>
                                <Accordion.Item key={film.vehiclesKey} eventKey={film.vehiclesKey}>
                                    <Accordion.Header><b>Vehicles</b></Accordion.Header>
                                    <Accordion.Body>
                                        <Accordion key="submenus-level2">
                                            {film.vehicles.map(vehicle => (
                                                <Accordion.Item key={vehicle.name} eventKey={vehicle.name}>
                                                    <Accordion.Header><b>{vehicle.name}</b></Accordion.Header>
                                                    <Accordion.Body>
                                                        <ul>
                                                            <li>Model : {vehicle.model}</li>
                                                            <li>Manufacturer : {vehicle.manufacturer}</li>
                                                            <li>Cost in credits : {vehicle.costInCredits}</li>
                                                            <li>Length : {vehicle.length}</li>
                                                            <li>Max atmosphering speed
                                                                : {vehicle.maxAtmospheringSpeed}</li>
                                                            <li>Crew : {vehicle.crew}</li>
                                                            <li>Passengers : {vehicle.passengers}</li>
                                                            <li>CargoCapacity : {vehicle.cargoCapacity}</li>
                                                            <li>Consumables : {vehicle.consumables}</li>
                                                            <li>Vehicle class : {vehicle.vehicleClass}</li>
                                                            <li>Created : {vehicle.created}</li>
                                                            <li>Edited : {vehicle.edited}</li>
                                                        </ul>
                                                    </Accordion.Body>
                                                </Accordion.Item>
                                            ))}
                                        </Accordion>
                                    </Accordion.Body>
                                </Accordion.Item>
                                <Accordion.Item key={film.speciesKey} eventKey={film.speciesKey}>
                                    <Accordion.Header><b>Species</b></Accordion.Header>
                                    <Accordion.Body>
                                        <Accordion key="submenus-level2">
                                            {film.species.map(specie => (
                                                <Accordion.Item key={specie.name} eventKey={specie.name}>
                                                    <Accordion.Header><b>{specie.name}</b></Accordion.Header>
                                                    <Accordion.Body>
                                                        <ul>
                                                            <li>Classification : {specie.classification}</li>
                                                            <li>Designation : {specie.designation}</li>
                                                            <li>AverageHeight : {specie.averageHeight}</li>
                                                            <li>Skin colors : {specie.skinColors}</li>
                                                            <li>Hair colors : {specie.hairColors}</li>
                                                            <li>Eye colors : {specie.eyeColors}</li>
                                                            <li>Average lifespan : {specie.averageLifespan}</li>
                                                            <li>Homeworld : {specie.homeworld}</li>
                                                            <li>Language : {specie.language}</li>
                                                            <li>Created : {specie.created}</li>
                                                            <li>Edited : {specie.edited}</li>
                                                        </ul>
                                                    </Accordion.Body>
                                                </Accordion.Item>
                                            ))}
                                        </Accordion>
                                    </Accordion.Body>
                                </Accordion.Item>
                            </Accordion>
                        </Accordion.Body>
                    </Accordion.Item>
                ))}
            </Accordion>
        </div>
    );
}

export default App;