# Cclean-Air (API) : API de suivi des données météorologiques et de pollution en Loire-Atlantique.

=> Travail réalisé dans le cadre d'un projet "fil rouge" en août/septembre 2019.

=> URL du back en localhost : `http://localhost:8090/`

=> consultation de la base de données h2 (embarquée) : `http://localhost:8090/h2-console/`

## Liste des requêtes back de Cclean-Air

### Module de création, de gestion de compte et d'authentification

#### Requête pour s'inscrire

[POST] /comptes

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

#### Requête pour s'authentifier

[POST] /connexion

```JSON
{
    "email" : "cecile@test.fr", 
    "motDePasse" : "Abcd.123"
}
```

Réponse en cas d'échec :

Code `401`

#### Requête pour modifier son compte

[PATCH] /profil/modification

```JSON
  {
	"nom" : "Toto", 
	"prenom" : "", 
	"email" : "", 
	"commune" : "", 
	"alerte" : "", 
	"listeIndicateurs" : [
		{
			"commune" : "Nantes", 
			"alerte" : "true"
		}, 
		{
			"commune" : "Sautron", 
			"alerte" : "false"
		}], 
	"motDePasseActuel" : "", 
	"motDePasseNouveau" : "", 
	"getMotDePasseNouveauValidation" : ""
}

```

### Module de consultation de la qualité de l’air, des conditions météorologiques en temps réel pour les communes de Loire-Atlantique

#### Cas d’utilisation “Visualiser données pollution” 

[GET] /communes/{codeInsee}

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

### Module de création, mise à jour, suppression et visualisation de tous les indicateurs

#### Requête pour créer un indicateur

[POST] /indicateurs

```JSON
{
	"commune" : "Nantes",
	"alerte" : true
}
```
Réponse en cas de réussite :

Code `201`

```JSON
{
    "mailUtilisateur": "cecile@test.fr",
    "nomCommune": "Nantes",
    "alerte": true
}
```

Réponse en cas d'échec :

Code `400`

#### Requête pour modifier un indicateur

[PATCH] /indicateurs

```JSON
{
	"communes" : ["Nantes", "Châteaubriant"]
}
```
Réponse en cas de réussite :

Code `200`

```JSON
{
    "mailUtilisateur": "cecile@test.fr",
    "nomCommune": "Châteaubriant",
    "alerte": true
}
```

Réponse en cas d'échec :

Code `400`


#### Requête pour supprimer un indicateur

[DELETE] /indicateurs


```JSON
{
	"commune" : "Nantes"
}
```
Réponse en cas de réussite :

Code `204`

Réponse en cas d'échec :

Code `400`

### Module d'administration

#### Requête pour afficher la liste des membres

[GET] /admin/membres

Réponse en cas de succès : 

Code `200`

```json
[
    {
        "nom": "peyras",
        "prenom": "cecile",
        "email": "cecile@test.fr"
    },
    {
        "nom": "peyras2",
        "prenom": "cecile2",
        "email": "cecile2@test.fr"
    }
]
```

#### Requête pour supprimer un membre

[DELETE] /admin/membres/{email}

Réponse en cas de succès :

Code `200`

```

```

Réponses en cas d'échec :

Code `400`

```
ERREUR: Aucun utilisateur trouvé avec cet email.
```

```
ERREUR: Un admin ne peut pas supprimer son propre compte.
```


## API externes (les requêtes sont effectuées par l'API)

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

#### Requête pour récupérer les données de pollution d'un polluant pour les communes de Loire-Atlantique

[GET] http://www.airpl.org/api/mesures?debut=2019-09-12T10:00:00&fin=2019-09-12T10:00:00&zones=all&polluant=24

Réponse en cas de succès : 

Code `200`

Extrait du début du JSON :

```JSON
{
  "polluant": {
    "id": "24",
    "nom": "Particules fines",
    "code": "PM10",
    "unite": "microg/m3",
    "seuils": [
      
    ]
  },
  "intervalle": "quart-horaire",
  "download_url": "/api/mesures.csv?debut=2019-09-12T10%3A00%3A00&fin=2019-09-12T10%3A00%3A00&zones=all&polluant=24",
  "next": "/api/mesures?debut=2019-09-12&fin=2019-09-12&zones=all&polluant=24",
  "prev": "/api/mesures?debut=2019-09-12&fin=2019-09-12&zones=all&polluant=24",
  "moyenne": "horaire",
  "mesures": {
    "68": {
      "departement": "Loire-Atlantique",
      "zone": "Basse Loire",
      "name": "Frossay",
      "code": "68",
      "data": [
        {
          "nom_departement": "Loire-Atlantique",
          "nom_zone": "Basse Loire",
          "nom_station": "Frossay",
          "code_station": "68",
          "polluant": "PM10",
          "niveau": 0,
          "unite": "microg/m3",
          "mesure": "moyenne horaire",
          "etat": "B",
          "date": "2019-09-12T08:00:00",
          "TZ": "UTC"
        }
      ]
    }
```

#### Requête pour récupérer les communes de Loire-Atlantique

-> Cette requête sera exécutée tous les 15 jours.

[GET] https://geo.api.gouv.fr/communes?codeRegion=52&fields=nom,code,codesPostaux,centre,codeRegion,population&format=json&geometry=centre

Pas celle-ci :
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
