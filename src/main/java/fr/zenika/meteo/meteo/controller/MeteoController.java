package fr.zenika.meteo.meteo.controller;

import fr.zenika.meteo.meteo.model.Meteo;
import fr.zenika.meteo.meteo.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.togglz.core.manager.FeatureManager;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MeteoController {
    @Autowired
    private FeatureManager featureManager;

    @Autowired
    MeteoService meteoService;

    @GetMapping(value = "/api/meteo", produces = "application/json")
    public Meteo meteo(@RequestParam("ville") String ville) {
        return meteoService.meteo(ville);
    }

    @GetMapping(value = "/meteo.html", produces = "text/html")
    public ModelAndView meteoHtml(@RequestParam("ville") String ville) {
        Map<String, Object> model = new HashMap<>();
        model.put("featureManager", featureManager);
        model.put("meteo", meteoService.meteo(ville));

        return new ModelAndView("meteo", model);
    }
}
