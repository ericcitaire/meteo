package fr.zenika.meteo.model;

public class Meteo {
    private final String temperature;

    public Meteo(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }
}
