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
  "statut" : "membre",
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
