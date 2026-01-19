# Microservices de Gestion des Factures

Ce projet est une architecture de microservices Spring Boot pour la gestion des factures, des produits et des clients, utilisant Eureka comme serveur de découverte de services.

## 🏗️ Architecture du Projet

Le projet est composé de 4 services principaux :

1. **Service Eureka** - Serveur de découverte de services (port 8761)
2. **Service Produits** - Gestion des produits (port 8081)
3. **Service Clients** - Gestion des clients (port 8082)
4. **Service Factures** - Gestion des factures (port 8083)

## 📋 Prérequis

- Java 17 ou supérieur
- Maven 3.8+
- PostgreSQL
- IDE (IntelliJ IDEA, Eclipse, etc.)

## 🚀 Configuration

### Base de données

Créez les bases de données nécessaires :

```sql
-- Pour le service produits
CREATE DATABASE serviceproduit_db;
CREATE USER serviceproduit_user WITH PASSWORD 'serviceproduit_password';
GRANT ALL PRIVILEGES ON DATABASE serviceproduit_db TO serviceproduit_user;

-- Pour le service clients
CREATE DATABASE serviceclient_db;
CREATE USER serviceclient_user WITH PASSWORD 'serviceclient_password';
GRANT ALL PRIVILEGES ON DATABASE serviceclient_db TO serviceclient_user;

-- Pour le service factures
CREATE DATABASE servicefacture_db;
CREATE USER servicefacture_user WITH PASSWORD 'servicefacture_password';
GRANT ALL PRIVILEGES ON DATABASE servicefacture_db TO servicefacture_user;
```

### Configuration des services

Chaque service possède son propre fichier `application.properties` avec les configurations nécessaires.

## 🛠 Installation

1. Clonez le dépôt :
   ```bash
   git clone [URL_DU_DEPOT]
   ```

2. Compilez le projet :
   ```bash
   mvn clean install
   ```

## 🚀 Démarrage des services

Démarrez les services dans l'ordre suivant :

1. Service Eureka (port 8761)
2. Service Produits (port 8081)
3. Service Clients (port 8082)
4. Service Factures (port 8083)

## 🌐 Accès aux services

- **Eureka Dashboard** : http://localhost:8761
- **Service Produits** : http://localhost:8081
- **Service Clients** : http://localhost:8082
- **Service Factures** : http://localhost:8083

## 📚 Documentation des API

### Service Produits
- `GET /api/produits` - Liste tous les produits
- `GET /api/produits/{id}` - Récupère un produit par son ID
- `POST /api/produits` - Crée un nouveau produit
- `PUT /api/produits/{id}` - Met à jour un produit
- `DELETE /api/produits/{id}` - Supprime un produit

### Service Clients
- `GET /api/clients` - Liste tous les clients
- `GET /api/clients/{id}` - Récupère un client par son ID
- `POST /api/clients` - Crée un nouveau client
- `PUT /api/clients/{id}` - Met à jour un client
- `DELETE /api/clients/{id}` - Supprime un client

### Service Factures
- `GET /api/factures` - Liste toutes les factures
- `GET /api/factures/{id}` - Récupère une facture par son ID
- `POST /api/factures` - Crée une nouvelle facture
- `PUT /api/factures/{id}` - Met à jour une facture
- `DELETE /api/factures/{id}` - Supprime une facture

## 🛡️ Sécurité

Les services sont sécurisés avec Spring Security. L'authentification est requise pour accéder aux endpoints protégés.

## 📊 Monitoring

Chaque service expose des endpoints Actuator pour le monitoring :
- `/actuator/health` - Santé du service
- `/actuator/info` - Informations du service
- `/actuator/metrics` - Métriques du service

## 🤝 Contribution

1. Forkez le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Pushez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📝 Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

## ✉️ Contact

Pour toute question, veuillez contacter handytsoka89@gmail.com 
