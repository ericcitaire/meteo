package fr.zenika.meteo.meteo.model;

public class Meteo {
    private String temperature;

    private String commentaire;

    public Meteo(String temperature, String commentaire) {
        this.temperature = temperature;
        this.commentaire = commentaire;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
