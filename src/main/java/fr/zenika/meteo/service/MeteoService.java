package fr.zenika.meteo.service;

import fr.zenika.meteo.model.Meteo;

public interface MeteoService {
    Meteo meteo(String ville);
}
