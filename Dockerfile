# ==========================================
# DOCKERFILE POUR APPLICATION SPRING BOOT
# ==========================================

# ÉTAPE 1 : Builder - Compilation de l'application
# -------------------------------------------------
# On utilise Maven avec Java 21 pour compiler le projet
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers de configuration Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Télécharger les dépendances (mise en cache pour accélérer les builds futurs)
RUN mvn dependency:go-offline -B

# Copier tout le code source
COPY src ./src

# Compiler l'application et créer le fichier JAR
# -DskipTests : sauter les tests pour accélérer le build
RUN mvn clean package -DskipTests

# ÉTAPE 2 : Runtime - Exécution de l'application
# -----------------------------------------------
# Image légère avec seulement Java pour exécuter l'application
FROM eclipse-temurin:21-jre-alpine

# Créer un utilisateur non-root pour la sécurité
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR compilé depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port 8080 (port par défaut de Spring Boot)
EXPOSE 8080

# Commande pour lancer l'application
# -Dspring.profiles.active=docker : utilise le profil Docker
ENTRYPOINT ["java", "-jar", "app.jar"]