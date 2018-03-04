# language: fr
Fonctionnalité: Affichage de la température

    # L'utilisation d'un contexte global (background) permet
    # de décrire en un seul endroit la situation de départ.


    Contexte:
        Étant données les conditions météo suivantes:
            | Ville     | Température en °C |
            | Paris     | 5.2345            |
            | Oslo      | -2.3456           |
            | Tokyo     | 14.5678           |
            | Montréal  | -35.6789          |
            | Dakar     | 32.4567           |
            | Marrakech | 28.4567           |
            | Moscou    | -8.4567          |


    # Arrondis

    Scénario: Température positive arrondie à l'entier inférieur
        Lorsque je demande la température pour la ville de "Paris"
        Alors la température affichée est "5°C"

    Scénario: Température positive arrondie à l'entier suppérieur
        Lorsque je demande la température pour la ville de "Tokyo"
        Alors la température affichée est "15°C"

    Scénario: Température négative arrondie à l'entier inférieur
        Lorsque je demande la température pour la ville de "Oslo"
        Alors la température affichée est "-2°C"

    Scénario: Température négative arrondie à l'entier suppérieur
        Lorsque je demande la température pour la ville de "Montréal"
        Alors la température affichée est "-36°C"


    # Commentaire

    Scénario: Il fait froid
        Lorsque je demande la température pour la ville de "Moscou"
        Alors le commentaire affiché est "Il fait froid à Moscou"

    Scénario: Il fait doux
        Lorsque je demande la température pour la ville de "Tokyo"
        Alors le commentaire affiché est "Il fait doux à Tokyo"

    Scénario: Il fait chaud
        Lorsque je demande la température pour la ville de "Marrakech"
        Alors le commentaire affiché est "Il fait chaud à Marrakech"

    Scénario: Il fait très chaud
        Lorsque je demande la température pour la ville de "Dakar"
        Alors le commentaire affiché est "Il fait très chaud à Dakar"

    Scénario: Il fait très froid
        Lorsque je demande la température pour la ville de "Montréal"
        Alors le commentaire affiché est "Il fait très froid à Montréal"


    # Comportement global

    # L'utilisation de plans de scénario (scenario outline)
    # permet de produire un grand nombre de cas de test en
    # très peu de temps.

    Plan du scénario: Affichage de la température et du commentaire
        Lorsque je demande la température pour la ville de "<Ville>"
        Alors la température affichée est "<Température>"
        Et le commentaire affiché est "<Commentaire>"

    Exemples:
      | Ville     | Température   | Commentaire                     |
      | Paris     | 5°C           | Il fait froid à Paris           |
      | Oslo      | -2°C          | Il fait froid à Oslo            |
      | Tokyo     | 15°C          | Il fait doux à Tokyo            |
      | Montréal  | -36°C         | Il fait très froid à Montréal   |
      | Dakar     | 32°C          | Il fait très chaud à Dakar      |
      | Marrakech | 28°C          | Il fait chaud à Marrakech       |
      | Moscou    | -8°C          | Il fait froid à Moscou          |
