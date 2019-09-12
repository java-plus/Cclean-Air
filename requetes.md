# Liste des requêtes back de Cclean-air

## Module de création, de gestion de compte et d'authentification

### Requête pour s'inscrire

[POST] /comptes

```JSON
{
  "nom" : "Dupuis",
  "prenom" : "Jean",
  "email" : "jean.dupuis@mail.com",
  "motDePasse" : "ldjHSD$$53",
  "statut" : "membre",
  "nomCommune" : "Nantes",
  "codePostal" : "44000",
  "statutNotification" : "true"
}
```

Réponse en cas de réussite :

Code `201`

```JSON
{
  "nom" : "Dupuis",
  "prenom" : "Jean",
  "email" : "jean.dupuis@mail.com",
  "motDePasse" : "ldjHSD$$53",
  "statuts" : [
  	"MEMBRE"
  	],
  "nomCommune" : "Nantes",
  "codePostal" : "44000",
  "statutNotification" : "true"
}
```

Réponse en cas d'échec :

Code `404`


## Module de consultation de la qualité de l’air, des conditions météorologiques en temps réel pour les communes de Loire-Atlantique


### Cas d’utilisation “Visualiser données pollution” 
[GET] / communes/{codeInsee}

Réponse en cas de réussite : 

Code : `200`

```JSON
{
    "communeDtoVisualisation": {
        "nom": "Nantes",
        "nbHabitants": 303382
    },
    "listePolluantDtoVisualisation": [
        {
            "nom": "azote",
            "unite": "µg",
            "valeur": 12.0
        }
    ],
    "conditionMeteoDtoVisualisation": {
        "ensoleillement": 10.0,
        "temperature": 20.0,
        "humidite": 20.0
    },
    "date": "2019-09-12T14:00:00+02:00"
} 
```

Réponse en cas d'erreur : 

Code `404`



## API externes

### OpenWeatherMap

#### Requête pour récupérer les données météorologiques d'une ville

-> Cette requête sera exécutée toutes les heures pour 10 villes de la base de données.

[GET] http://api.openweathermap.org/data/2.5/weather?lat=-1.553621&lon=47.218371&lang=fr&units=metric&APPID
=633e2a135fc012a55447e2b1f366972d&units=metric

-> lat=-1.553621&lon=47.218371 correspondent à la latitude et à la longitude de la ville

Réponse en cas de réussite :

Code `200`

```JSON
{
  "coord": {
    "lon": 47.22,
    "lat": -1.55
  },
  "weather": [
    {
      "id": 800,
      "main": "Clear",
      "description": "ciel dégagé",
      "icon": "01d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 25.89,
    "pressure": 1013.23,
    "humidity": 78,
    "temp_min": 25.89,
    "temp_max": 25.89,
    "sea_level": 1013.23,
    "grnd_level": 1013.16
  },
  "wind": {
    "speed": 7.55,
    "deg": 174.721
  },
  "clouds": {
    "all": 0
  },
  "dt": 1568189835,
  "sys": {
    "message": 0.006,
    "sunrise": 1568169918,
    "sunset": 1568213456
  },
  "timezone": 10800,
  "id": 0,
  "name": "",
  "cod": 200
}
```

#### Requête pour récupérer les polluants

-> Cette requête sera exécutée toutes les heures.

[GET] http://www.airpl.org/api/polluants

Réponse en cas de réussite (seulement le début du JSON) :

Code `200`

```JSON
[
  {
    "id": "24",
    "nom": "Particules fines",
    "code": "PM10",
    "famille": "",
    "principal": false
  },
  {
    "id": "08",
    "nom": "Ozone",
    "code": "O3",
    "famille": "",
    "principal": true
  },
```

### Requête pour récupérer les communes de Loire-Atlantique

-> Cette requête sera exécutée tous les 15 jours.

[GET] https://geo.api.gouv.fr/communes?codeDepartement=44&fields=nom,code,codesPostaux,centre,codeRegion,population&format=json&geometry=centre

Réponse en cas de succès :

Code `200` ou `304`

Extrait du début du JSON :
```JSON
[
  {
    "nom": "Abbaretz",
    "code": "44001",
    "codeDepartement": "44",
    "codeRegion": "52",
    "codesPostaux": [
      "44170"
    ],
    "population": 2068
  },
  {
    "nom": "Aigrefeuille-sur-Maine",
    "code": "44002",
    "codeDepartement": "44",
    "codeRegion": "52",
    "codesPostaux": [
      "44140"
    ],
    "population": 3763
  }

```

