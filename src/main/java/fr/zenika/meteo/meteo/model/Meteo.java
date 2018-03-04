package fr.zenika.meteo.meteo.model;

public class Meteo {
    private final String temperature;

    private final String commentaire;

    public Meteo(String temperature, String commentaire) {
        this.temperature = temperature;
        this.commentaire = commentaire;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCommentaire() {
        return commentaire;
    }
}
