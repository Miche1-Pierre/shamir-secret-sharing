# Shamir Secret Sharing - Double Authentification pour Documents Sensibles

## Description

Ce projet met en œuvre un système de double authentification pour sécuriser l'accès aux documents sensibles (ex. CNI, passeports). Il utilise l'algorithme Shamir's Secret Sharing pour diviser une clé de chiffrement en fragments et la reconstruire de manière sécurisée.

Le projet est construit avec Java Spring Boot, une base de données MySQL, et un front-end basé sur des API REST.

## Fonctionnalités

1) **Chiffrement des documents :**
   - Génération d'une clé unique pour chaque document.
   - Division de la clé en n fragments.
   - Stockage des fragments dans une base de données MySQL.


2) **Déchiffrement sécurisé :**
   - Reconstruction de la clé à partir des fragments.
   - Déchiffrement du document.


3) **Double authentification :**
   - L'administrateur fournit un fragment personnel.
   - Les fragments restants sont récupérés automatiquement depuis la base de données.


4) **API REST :**
   - POST /api/documents/encrypt : Chiffrement d'un document.
   - POST /api/documents/decrypt : Déchiffrement d'un document.


## Prérequis
   1) **Java** : Version 17 ou plus récente.
   2) **Maven** : Pour la gestion des dépendances.
   3) **MySQL** : Base de données relationnelle.
   4) **Postman** ou un outil similaire pour tester les API.


## Installation

### Étape 1 : Cloner le projet
   
```bash
git clone https://github.com/Miche1-Pierre/shamir-secret-sharing.git
cd shamir-secret-sharing
```
### Étape 2 : Configurer la base de données
   1) Créez une base de données MySQL :

```sql
CREATE DATABASE shamir_secret_sharing;
```
   2) Configurez application.properties :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shamir_secret_sharing
spring.datasource.username=<votre_username>
spring.datasource.password=<votre_password>
spring.jpa.hibernate.ddl-auto=update
```

### Étape 3 : Installer les dépendances

```bash
mvn clean install
```

### Étape 4 : Lancer l'application
```bash
mvn spring-boot:run
```

## Utilisation
### 1) Chiffrement d'un document

    Envoyez une requête POST à /api/documents/encrypt :

```
POST /api/documents/encrypt
```
```json
{
"documentId": "doc123",
"content": "mySecretKey",
"n": 5,
"t": 3
}
```

### 2. Déchiffrement d'un document

    Envoyez une requête POST à /api/documents/decrypt :

```
POST /api/documents/decrypt
```
```json
{
  "documentId": "doc123",
  "fragments": {
    "1": "310524561739677801416925875",
    "2": "679720522211372830108415807",
    "3": "1239933740483073811674522397"
  }
}
```

### Réponses des API
- Chiffrement :
```
Document encrypté, fragments enregistrés.
```

- Déchiffrement :
```
Document déchiffré avec la clé : mySecretKey
```


## Principales Technologies
- **Backend** : Spring Boot
- **Base de données** : MySQL
- **Langage** : Java
- **Gestion des dépendances** : Maven
- **API REST** : Spring Web

## Tests
1) Utilisez Postman pour envoyer des requêtes POST aux endpoints.
2) Vérifiez que les fragments sont correctement stockés dans MySQL.
3) Assurez-vous que la clé reconstruite correspond à la clé originale.

## Auteurs
**Miche1-Pierre**

En formation à Metz Numeric School (Master II développeur Fullstack).

## Licence
Ce projet est sous licence MIT. Vous pouvez l'utiliser, le modifier et le distribuer librement.