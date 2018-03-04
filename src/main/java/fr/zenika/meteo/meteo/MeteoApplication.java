package fr.zenika.meteo.meteo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@EnableFeignClients
public class MeteoApplication {

    @Autowired
    OpenWeatherMapAPI openWeatherMapAPI;

	public static void main(String[] args) {
		SpringApplication.run(MeteoApplication.class, args);
	}
}

@Data
@AllArgsConstructor
class Meteo {
    String temperature;
    String commentaire;
}

interface MeteoService {
    Meteo meteo(String ville);
}

@Service
class MeteoServiceImpl implements MeteoService {
    @Value("${openweathermap.appid}")
    String openWeatherMapAppId;

    @Autowired
    OpenWeatherMapAPI openWeatherMapAPI;

    public Meteo meteo(String ville) {
        Weather weather = openWeatherMapAPI.weather(openWeatherMapAppId, ville, "metric");
        String temperature = String.valueOf(Math.round(weather.main.temp)) + "°C";
        String commentaire;
        if (weather.main.temp < -10) {
            commentaire = "Il fait très froid à " + ville;
        } else if (weather.main.temp < 10) {
            commentaire = "Il fait froid à " + ville;
        } else if (weather.main.temp >= 30) {
            commentaire = "Il fait très chaud à " + ville;
        } else if (weather.main.temp >= 20) {
            commentaire = "Il fait chaud à " + ville;
        } else {
            commentaire = "Il fait doux à " + ville;
        }
        return new Meteo(temperature, commentaire);
    }
}

@RestController
class MeteoController {
    @Autowired
    MeteoService meteoService;

    @GetMapping(value = "/api/meteo", produces = "application/json")
    public Meteo meteo(@RequestParam("ville") String ville) {
        return meteoService.meteo(ville);
    }

    @GetMapping(value = "/meteo.html", produces = "text/html")
    public ModelAndView meteoHtml(@RequestParam("ville") String ville) {
        return new ModelAndView("meteo", "meteo", meteoService.meteo(ville));
    }
}

@Data
class Main {
    double temp;
}

@Data
class Weather {
    Main main;
}

@FeignClient(name = "OpenWeatherMap", url = "http://api.openweathermap.org/data/2.5")
interface OpenWeatherMapAPI {
    @RequestMapping(method = RequestMethod.GET, value = "/weather?q={q}&lang=fr&appid={appid}&units={units}", consumes = "application/json")
    Weather weather(@PathVariable("appid") String appid, @PathVariable("q") String q, @PathVariable("units") String units);
}
