package fr.zenika.meteo.owm.api;

import fr.zenika.meteo.owm.api.model.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OpenWeatherMap", url = "http://api.openweathermap.org/data/2.5")
public interface OpenWeatherMapAPI {
    @RequestMapping(method = RequestMethod.GET, value = "/weather?q={q}&lang=fr&appid={appid}&units={units}", consumes = "application/json")
    Weather weather(@PathVariable("appid") String appid, @PathVariable("q") String q, @PathVariable("units") String units);
}
