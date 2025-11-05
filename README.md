# 🛒 Application CRUD de Gestion de Produits

Application web monolithique développée avec **Spring Boot** pour la gestion complète de produits (Create, Read, Update, Delete).



##  Fonctionnalités

-  **CRUD complet** - Ajouter, Modifier, Supprimer, Consulter des produits
-  **Pagination** - Affichage paginé des données (10 éléments par page)
-  **Tri dynamique** - Tri par nom, prix, quantité, date
-  **Validation des données** - Contraintes Hibernate Validator
-  **Gestion des erreurs** - Messages d'erreur conviviaux
-  **Interface responsive** - Design adaptatif avec Bootstrap 5
-  **Design moderne** - Interface élégante avec animations

## Technologies Utilisées

### Backend
- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Data JPA** (Hibernate)
- **Spring MVC**
- **Maven**

### Frontend
- **Thymeleaf** (Template Engine)
- **Bootstrap 5.3.2**
- **Font Awesome 6.4.0**
- **CSS3** (Custom styling)

### Base de données
- **MySQL 8.0**

## 📁 Architecture
```
tp-monolithique/
├── src/main/java/
│   └── com.example.tpmonolithique/
│       ├── controller/      # Couche Web (MVC)
│       ├── service/         # Logique métier
│       ├── repository/      # Accès aux données (DAO)
│       ├── entity/          # Entités JPA
│       └── TpMonolithiqueApplication.java
├── src/main/resources/
│   ├── templates/           # Vues Thymeleaf
│   │   ├── produits.html
│   │   └── form.html
│   ├── static/css/          # Fichiers CSS
│   └── application.properties
└── pom.xml
```
## Installation et Configuration

### Prérequis
- Java JDK 17+
- Maven 3.6+
- MySQL 8.0+
```



## 📝 License

Ce projet est développé à des fins éducatives.
