package fr.zenika.meteo.meteo.service.impl;

import fr.zenika.meteo.meteo.model.Meteo;
import fr.zenika.meteo.meteo.owm.api.OpenWeatherMapAPI;
import fr.zenika.meteo.meteo.owm.api.model.Weather;
import fr.zenika.meteo.meteo.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MeteoServiceImpl implements MeteoService {
    @Value("${openweathermap.appid}")
    String openWeatherMapAppId;

    @Autowired
    OpenWeatherMapAPI openWeatherMapAPI;

    public Meteo meteo(String ville) {
        Weather weather = openWeatherMapAPI.weather(openWeatherMapAppId, ville, "metric");
        double t = weather.getMain().getTemp();
        String temperature = String.valueOf(Math.round(t)) + "°C";
        String commentaire;
        if (t < -10) {
            commentaire = "Il fait très froid à " + ville;
        } else if (t < 10) {
            commentaire = "Il fait froid à " + ville;
        } else if (t >= 30) {
            commentaire = "Il fait très chaud à " + ville;
        } else if (t >= 20) {
            commentaire = "Il fait chaud à " + ville;
        } else {
            commentaire = "Il fait doux à " + ville;
        }
        return new Meteo(temperature, commentaire);
    }
}
