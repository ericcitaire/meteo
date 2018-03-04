package fr.zenika.meteo.meteo.service;

import fr.zenika.meteo.meteo.model.Meteo;

public interface MeteoService {
    Meteo meteo(String ville);
}
