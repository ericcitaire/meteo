package fr.zenika.meteo.meteo.controller;

import fr.zenika.meteo.meteo.model.Meteo;
import fr.zenika.meteo.meteo.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MeteoController {
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
