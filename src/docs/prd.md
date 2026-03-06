# 🏋️ PRD — SaaS d’organisation de compétitions CrossFit locales

## 1. 🎯 Vision du produit

Créer un SaaS permettant aux salles de CrossFit et organisateurs indépendants de **gérer entièrement une compétition locale**, de l’inscription des participants jusqu’au classement final.

### Objectifs

- Remplacer Excel + papier + outils bricolés
- Simplifier l’organisation d’événements
- Offrir une expérience professionnelle aux athlètes
- Produit monétisable en B2B (organisateurs)

---

## 2. 👤 Utilisateurs cibles

### 🧑‍💼 Organisateur (client payant)

Exemples :

- Box de CrossFit
- Coach organisant un événement
- Association sportive
- Compétition locale amateur

#### Besoins

- Gagner du temps
- Éviter les erreurs
- Centraliser les informations
- Gérer scores et classements facilement
- Offrir un suivi en temps réel

---

### 🏃 Participants (utilisateurs gratuits)

- S’inscrivent à une compétition
- Consultent leurs scores
- Suivent le classement
- Accèdent aux informations de l’événement

---

### 👥 Spectateurs (optionnel)

- Suivi du leaderboard en temps réel
- Affichage public sur écran

---

## 3. 🧩 Proposition de valeur

> Créer et gérer une compétition CrossFit en quelques minutes, sans Excel.

---

## 4. 🏗️ Périmètre MVP

Le MVP doit permettre d’organiser une compétition simple de bout en bout.

---

## 5. ⭐ Fonctionnalités MVP

### 🥇 5.1 Gestion des compétitions

L’organisateur peut :

- Créer une compétition
- Modifier les informations
- Supprimer une compétition
- Voir la liste de ses compétitions

#### Données

- Nom
- Date
- Lieu
- Description
- Statut (draft / open / ongoing / finished)

---

### 🥈 5.2 Gestion des participants

L’organisateur peut :

- Ajouter un participant
- Modifier ses informations
- Supprimer un participant
- Consulter la liste

#### Données participant

- Nom
- Prénom
- Email (optionnel)
- Catégorie (Rx, Scaled, Beginner…)
- Genre (optionnel)

---

### 🥉 5.3 Gestion des WODs

Un WOD = une épreuve.

L’organisateur peut :

- Ajouter un WOD
- Modifier un WOD
- Supprimer un WOD

#### Données WOD

- Nom
- Description
- Type de score (temps / répétitions / poids)
- Ordre dans la compétition

---

### 🏅 5.4 Saisie des scores

Fonction centrale du produit.

L’organisateur peut :

- Entrer le score d’un participant pour un WOD
- Modifier un score
- Visualiser les scores par WOD

---

### 🏆 5.5 Classement automatique

Calcul automatique :

- Classement par WOD
- Classement global
- Classement par catégorie

#### Affichage

- Rang
- Nom du participant
- Score total

---

### 📺 5.6 Leaderboard public

Page accessible sans compte.

Fonction :

- Affichage du classement en temps réel
- Utilisable sur écran dans la salle
- Partageable avec participants et spectateurs

---

## 6. 🔐 Gestion des comptes

### MVP minimal

- Compte organisateur
- Authentification sécurisée
- Isolation des données (chaque utilisateur voit ses compétitions)

---

## 7. 🚫 Hors MVP (Phase ultérieure)

Fonctionnalités prévues mais non indispensables au lancement :

- Paiement des inscriptions
- Check-in des athlètes
- Planning des heats
- Attribution des juges
- Application mobile
- Notifications (email / push)
- Multi-organisation
- Export PDF / Excel
- Gestion des sponsors
- Statistiques avancées

---

## 8. 💰 Modèle économique envisagé

Options possibles :

- Paiement par compétition
- Abonnement mensuel pour les box
- Commission par participant
- Freemium avec limitations

À valider après tests utilisateurs.

---

## 9. 🧠 Objectifs techniques (portfolio senior)

Le projet doit démontrer :

### Frontend

- SPA moderne
- Gestion d’état
- Authentification
- UI professionnelle
- Responsive design

### Backend

- API REST propre
- Sécurité et authentification
- Validation des données
- Architecture claire
- Tests

### Base de données

- Modélisation relationnelle solide
- Gestion des relations complexes
- Requêtes optimisées pour le classement

### DevOps

- Déploiement sur serveur personnel
- Containerisation
- Reverse proxy
- Monitoring simple
- CI/CD basique

---

## 10. 🧱 Entités principales (MVP)

- User
- Competition
- Participant
- Wod
- Score

---

## 11. 📈 Critères de succès du MVP

Le produit est considéré viable si :

- Un organisateur peut créer une compétition complète
- Les participants peuvent être ajoutés facilement
- Les scores peuvent être saisis rapidement
- Le classement est correct et automatique
- Le leaderboard public fonctionne
- L’application est déployée en production
- Utilisable lors d’un événement réel

---

## 12. 🚀 Valeur pour un portfolio

Ce projet démontre :

- Conception d’un produit complet
- Architecture full-stack
- Complexité métier réelle
- Gestion multi-utilisateurs
- Modélisation de données avancée
- Capacité à livrer un SaaS en production

Projet particulièrement pertinent pour un poste d’ingénieur logiciel mid / senior.
