package fr.zenika.meteo.meteo.model;

public class MeteoAvecCommentaire extends Meteo {
    private final String commentaire;

    public MeteoAvecCommentaire(String temperature, String commentaire) {
        super(temperature);
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }
}
