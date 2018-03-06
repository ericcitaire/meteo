package fr.zenika.meteo.service.impl;

import fr.zenika.meteo.features.MeteoFeatures;
import fr.zenika.meteo.model.Meteo;
import fr.zenika.meteo.model.MeteoAvecCommentaire;
import fr.zenika.meteo.owm.api.OpenWeatherMapAPI;
import fr.zenika.meteo.owm.api.model.Weather;
import fr.zenika.meteo.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

@Service
public class MeteoServiceImpl implements MeteoService {
    @Value("${openweathermap.appid}")
    String openWeatherMapAppId;

    @Autowired
    private FeatureManager featureManager;

    @Autowired
    OpenWeatherMapAPI openWeatherMapAPI;

    public Meteo meteo(String ville) {
        Weather weather = openWeatherMapAPI.weather(openWeatherMapAppId, ville, "metric");
        double temperature = weather.getMain().getTemp();
        String temperatureStr = buildTemperature(temperature);
        if (featureManager.isActive(MeteoFeatures.FEATURE_COMMENTAIRE)) {
            String commentaireStr = buildCommentaire(temperature, ville);
            return new MeteoAvecCommentaire(temperatureStr, commentaireStr);
        } else {
            return new Meteo(temperatureStr);
        }
    }

    private String buildTemperature(double t) {
        return String.format("%d°C", Math.round(t));
    }

    private String buildCommentaire(double temperature, String ville) {
        StringBuilder commentaire = new StringBuilder();
        if (temperature < -10) {
            commentaire.append("Il fait très froid");
        } else if (temperature < 10) {
            commentaire.append("Il fait froid");
        } else if (temperature >= 30) {
            commentaire.append("Il fait très chaud");
        } else if (temperature >= 20) {
            commentaire.append("Il fait chaud");
        } else {
            commentaire.append("Il fait doux");
        }
        commentaire.append(" à ").append(ville);

        return commentaire.toString();
    }
}
