
# Application Météo

[![Build Status](https://travis-ci.org/ericcitaire/meteo.svg?branch=master)](https://travis-ci.org/ericcitaire/meteo)

## Spécifications

* L'application doit afficher la température pour une ville donnée.
* La température est fournie par l'[API OpenWeatherMap](https://openweathermap.org/current).
* La température est affichée en degrés Celsius.
* La température est arrondie sans décimale, suivi de l'unité (exemple: `5°C`).
* La température est accompagnée d'un commentaire qui décrit le temps qu'il fait dans la ville concernée:
    * `Il fait doux à <nom de la ville>` pour une température supérieure ou égale à 10°C et strictement inférieure à 20°C
    * `Il fait chaud à <nom de la ville>` pour une température supérieure ou égale à 20°C et strictement inférieure à 30°C
    * `Il fait froid à <nom de la ville>` pour une température supérieure ou égale à -10°C et strictement inférieure à 10°C
    * `Il fait très chaud à <nom de la ville>` pour une température supérieure ou égale à 30°C
    * `Il fait très froid à <nom de la ville>` pour une température strictement inférieure à -10°C
