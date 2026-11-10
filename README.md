# Patient MCP — Assistant médical avec Spring AI et MCP

Projet démontrant l'utilisation du **Model Context Protocol (MCP)** avec **Spring AI** : un chatbot en langage naturel (LLM hébergé sur Groq) interroge et modifie une base de patients via des outils MCP exposés par une API Spring Boot.

## Architecture

```
Utilisateur ──► chatbot (port 8082) ──MCP (streamable HTTP)──► patient_API (port 8081) ──► H2 (en mémoire)
                    │
                    └── LLM Groq (llama-3.1-8b-instant, API compatible OpenAI)
```

| Module | Rôle | Port |
|--------|------|------|
| `patient_API` | Serveur MCP + persistance JPA/H2. Expose les opérations CRUD sur les patients comme outils MCP. | 8081 |
| `chatbot` | Client MCP + agent IA. Reçoit une question, appelle les outils MCP, reformule la réponse en français. | 8082 |

## Fonctionnalités

- Outils MCP exposés par `patient_API` :
  - ajouter / enregistrer un patient
  - modifier un patient
  - supprimer un patient par id
  - rechercher un patient par id
  - lister tous les patients
- Agent médical avec mémoire de conversation (`MessageChatMemoryAdvisor`)
- Réponses toujours reformulées en texte naturel (pas de JSON brut)
- Documentation Swagger du chatbot via springdoc
- Données de démonstration chargées au démarrage (3 patients)

## Technologies

- Java 21
- Spring Boot 4.0.5
- Spring AI (MCP server / MCP client, starter OpenAI)
- Spring Data JPA, base H2 en mémoire
- Lombok
- Maven
- Groq (`llama-3.1-8b-instant`)

## Structure du projet

```
patient_MCP/
├── patient_API/    # Serveur MCP + API patients (JPA, H2)
├── chatbot/        # Client MCP + agent IA + contrôleur REST
└── pom.xml
```

## Prérequis

- JDK 21
- Maven (ou le wrapper `mvnw` fourni dans chaque module)
- Une clé API Groq : https://console.groq.com

## Configuration

La clé API ne doit **jamais** être écrite dans le code. Le fichier `chatbot/src/main/resources/application.properties` la lit depuis une variable d'environnement :

```properties
spring.ai.openai.api-key=${GROQ_API_KEY}
```

Définir la variable avant de lancer le chatbot :

```bash
# Linux / macOS
export GROQ_API_KEY=votre_cle

# Windows (PowerShell)
$env:GROQ_API_KEY="votre_cle"
```

## Lancement

1. Démarrer d'abord le serveur MCP :

```bash
cd patient_API
./mvnw spring-boot:run
```

2. Dans un second terminal, démarrer le chatbot :

```bash
cd chatbot
./mvnw spring-boot:run
```

Sous Windows, utiliser `mvnw.cmd` à la place de `./mvnw`.

## Utilisation

Interroger le chatbot via HTTP :

```
GET http://localhost:8082/chat?message=Donne moi la liste des patients
```

Exemples de questions :

- « Donne-moi la liste des patients »
- « Trouve le patient numéro 2 »
- « Ajoute le patient Sami Trabelsi de Bizerte »
- « Supprime le patient numéro 3 »

## Données de démonstration

Au démarrage, `patient_API` insère trois patients (Tunis, Sfax, Sousse). La base H2 est en mémoire : les données sont perdues à l'arrêt de l'application.

## Sécurité

- Ne pas versionner de clé API ni de secret.
- Ce projet est une démonstration : l'API patient n'est pas authentifiée et ne doit pas contenir de vraies données médicales.

## Auteur

Tayeb
