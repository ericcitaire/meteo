package fr.zenika.meteo.meteo.owm.api.model;

public class Weather {
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    private Main main;
}
