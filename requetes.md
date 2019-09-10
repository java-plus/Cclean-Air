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