package fr.zenika.meteo.meteo.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum MeteoFeatures implements Feature {
    @Label("Affichage commentaire")
    FEATURE_COMMENTAIRE;
}