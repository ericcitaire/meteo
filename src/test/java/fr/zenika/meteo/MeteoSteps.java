package fr.zenika.meteo;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Quand;
import cucumber.api.java.fr.Étantdonnées;
import fr.zenika.meteo.features.MeteoFeatures;
import fr.zenika.meteo.model.Meteo;
import fr.zenika.meteo.model.MeteoAvecCommentaire;
import fr.zenika.meteo.owm.api.OpenWeatherMapAPI;
import fr.zenika.meteo.owm.api.model.Main;
import fr.zenika.meteo.owm.api.model.Weather;
import fr.zenika.meteo.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ContextConfiguration
@SpringBootTest
public class MeteoSteps {
    @MockBean
    @Autowired
    private OpenWeatherMapAPI openWeatherMapAPI;

    @Autowired
    private FeatureManager featureManager;

    @Autowired
    private MeteoService meteoService;

    private Meteo meteo;

    @Before("@FeatureCommentaire")
    public void beforeFeatureCommentaire() {
        featureManager.setFeatureState(new FeatureState(MeteoFeatures.FEATURE_COMMENTAIRE, true));
    }

    @After("@FeatureCommentaire")
    public void afterFeatureCommentaire() {
        featureManager.setFeatureState(new FeatureState(MeteoFeatures.FEATURE_COMMENTAIRE, false));
    }

    @Étantdonnées("^les conditions météo suivantes:$")
    public void les_conditions_météo_suivantes(DataTable table) throws Throwable {
        for (List<String> row : table.cells(1)) {
            String ville = row.get(0);
            double temperature = Double.parseDouble(row.get(1));

            Weather weather = new Weather();
            weather.setMain(new Main());
            weather.getMain().setTemp(temperature);

            given(this.openWeatherMapAPI.weather(anyString(), eq(ville), eq("metric"))).willReturn(weather);
        }
    }

    @Quand("^je demande la température pour la ville de \"([^\"]*)\"$")
    public void je_demande_la_température_pour_la_ville_de(String ville) throws Throwable {
        this.meteo = meteoService.meteo(ville);
    }

    @Alors("^la température affichée est \"([^\"]*)\"$")
    public void la_température_affichée_est(String expectedTemperature) throws Throwable {
        assertThat(meteo.getTemperature()).isEqualTo(expectedTemperature);
    }

    @Alors("^le commentaire affiché est \"([^\"]*)\"$")
    public void le_commentaire_affiché_est(String expectedCommentaire) throws Throwable {
        if (this.meteo instanceof MeteoAvecCommentaire) {
            MeteoAvecCommentaire meteoAvecCommentaire = (MeteoAvecCommentaire) meteo;
            assertThat(meteoAvecCommentaire.getCommentaire()).isEqualTo(expectedCommentaire);
        } else {
            fail("Pas de champ commentaire");
        }
    }
}
