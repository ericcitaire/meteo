package fr.zenika.meteo.meteo;

import cucumber.api.DataTable;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Quand;
import cucumber.api.java.fr.Étantdonnées;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ContextConfiguration
@SpringBootTest
public class MeteoSteps {
    @MockBean
    @Autowired
    OpenWeatherMapAPI openWeatherMapAPI;

    @Autowired
    MeteoService meteoService;

    String ville;

    @Étantdonnées("^les conditions météo suivantes:$")
    public void les_conditions_météo_suivantes(DataTable table) throws Throwable {
        for (List<String> row : table.cells(1)) {
            Weather weather = new Weather();
            weather.main = new Main();
            weather.main.temp = Double.parseDouble(row.get(1));
            given(this.openWeatherMapAPI.weather(anyString(), eq(row.get(0)), eq("metric"))).willReturn(weather);
        }
    }

    @Quand("^je demande la température pour la ville de \"([^\"]*)\"$")
    public void je_demande_la_température_pour_la_ville_de(String ville) throws Throwable {
        this.ville = ville;
    }

    @Alors("^la température affichée est \"([^\"]*)\"$")
    public void la_température_affichée_est(String expectedTemperature) throws Throwable {
        assertThat(meteoService.meteo(ville).temperature).isEqualTo(expectedTemperature);
    }

    @Alors("^le commentaire affiché est \"([^\"]*)\"$")
    public void le_commentaire_affiché_est(String expectedCommentaire) throws Throwable {
        assertThat(meteoService.meteo(ville).commentaire).isEqualTo(expectedCommentaire);
    }
}
