package menu;

import agenda.ProfesseurAgenda;
import agenda.EleveAgenda;
import agenda.LocalAgenda;
import agenda.IAgenda;
import prof.Create_prof;
import cours.Cours;
import cours.Create_cours;
import cours.GestionCours;
import eleve.Create_eleve;
import eleve.Eleve;
import locaux.Create_locaux;
import locaux.GestionLocaux;
import locaux.Locaux;
import locaux.Reservation;
import prof.Prof;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Menu {
    public static void main(String[] args) {
        Random random = new Random();
        // Créer des professeurs aléatoires en utilisant Create_prof
        List<Prof> professeurs = Create_prof.genererProfesseurs(7);
        // Créer des élèves aléatoires en utilisant Create_eleve
        List<Eleve> eleves = Create_eleve.genererEleves(10);
        // Créer des cours aléatoires en utilisant Create_cours
        List<Cours> cours = Create_cours.genererCours(10);
        // Créer des locaux aléatoires en utilisant Create_locaux
        List<Locaux> locaux = Create_locaux.genererLocaux(5);
        
        // Listes pour stocker les professeurs, élèves et locaux
        List<IAgenda> professeursAgenda = new ArrayList<>();
        List<IAgenda> elevesAgenda = new ArrayList<>();
        List<IAgenda> locauxAgenda = new ArrayList<>();

        // Création de l'objet gestionCours pour rechercher les cours par ID
        GestionCours gestionCours = new GestionCours();
        for (Cours coursElement : cours) {
            gestionCours.ajouterCours(coursElement); // Ajouter les cours au gestionnaire
        }

        // Créer les réservations pour les cours
        for (Cours coursElement : cours) {
            // Sélectionner un local aléatoire
            Locaux local = locaux.get(random.nextInt(locaux.size()));

            // Générer une date aléatoire pour la réservation
            int month = random.nextInt(3) + 4; // Mois entre avril et juin
            int day = random.nextInt(30) + 1;  // Jour entre 1 et 30
            LocalDate dateReservation = LocalDate.of(2025, month, day);

            // Réserver le local pour le cours
            GestionLocaux.reserverLocalPourCours(coursElement, local, dateReservation);
        }

        // Créer un agenda pour chaque professeur
        for (Prof professeur : professeurs) {
            ProfesseurAgenda profAgenda = new ProfesseurAgenda(professeur.getNomComplet());

            // Ajouter des événements dans l'agenda du professeur
            int numEvenements = random.nextInt(5) + 1; // Ajouter entre 1 et 5 événements
            for (int i = 1; i <= numEvenements; i++) {
                LocalDate randomDate = LocalDate.of(2025, 6, random.nextInt(30) + 1);
                profAgenda.ajouterEvenement(randomDate, "Cours de " + professeur.getNomComplet() + " - Cours " + i); // Ajout de l'événement dans l'agenda
            }

            // Ajouter l'agenda du professeur à la liste
            professeursAgenda.add(profAgenda);
        }

        // Assigner des professeurs aux cours
        for (Cours coursElement : cours) {
            // Assigner un professeur à ce cours
            Prof professeur = professeurs.get(random.nextInt(professeurs.size()));
            coursElement.ajouterProf(professeur); // Ajouter un professeur au cours

            // 3 cours auront 2 professeurs assignés
            if (coursElement.getId() <= 3) {
                Prof professeur2 = professeurs.get(random.nextInt(professeurs.size()));
                while (professeur == professeur2) {
                    professeur2 = professeurs.get(random.nextInt(professeurs.size())); // Assurer que le deuxième professeur est différent
                }
                coursElement.ajouterProf(professeur2); // Ajouter un second professeur
            }
        }

        // Assignation des cours aux professeurs
        for (Cours coursElement : cours) {
            for (Prof professeur : coursElement.getProfsAssignes()) {
                // Chaque professeur reçoit un ou plusieurs cours
                professeur.ajouterCours(gestionCours, coursElement.getId());
            }
        }

        // Créer un agenda pour chaque élève
        for (Eleve eleve : eleves) {
            EleveAgenda eleveAgenda = new EleveAgenda(eleve.getNomComplet());
            
            // Ajouter des événements dans l'agenda de l'élève
            int numEvenements = random.nextInt(5) + 1; // Ajouter entre 1 et 5 événements
            for (int i = 1; i <= numEvenements; i++) {
                eleveAgenda.ajouterEvenement(LocalDate.of(2025, 6, random.nextInt(30) + 1)," Cours " + i +"\n");
            }

            // Assigner des cours aléatoires à l'élève
            int nbCours = 2 + random.nextInt(3); // Minimum 2 cours par élève
            for (int i = 0; i < nbCours; i++) {
                Cours coursElement = cours.get(random.nextInt(cours.size()));
                eleve.ajouterCours(gestionCours, coursElement.getId());
            }

            elevesAgenda.add(eleveAgenda);
        }

        // Créer un agenda pour chaque local
        for (Locaux local : locaux) {
            LocalAgenda localAgenda = new LocalAgenda(local.getNom());
            int numEvenements = random.nextInt(7) + 1; // Ajouter entre 1 et 7 événements
            for (int i = 1; i <= numEvenements; i++) {
                localAgenda.ajouterEvenement(LocalDate.of(2025, 6, random.nextInt(30) + 1)," pour Cours " + i);
            }
            locauxAgenda.add(localAgenda);
        }

        System.out.println("Agenda des professeurs :");
        for (Prof professeur : professeurs) {
            System.out.println(professeur.getNomComplet());
            
            // Pour chaque cours, vérifier les réservations dans les locaux
            for (Cours coursProf : professeur.getCoursAssignes()) {
                for (Locaux local : locaux) {
                    // Vérifier les réservations dans chaque local
                    for (Reservation reservation : local.getReservations()) {
                        // Si la réservation concerne le cours
                        if (reservation.getCours().getNom().equals(coursProf.getNom())) {
                            System.out.println(reservation.getDateHeure() + " : " +coursProf.getNom() + " | " + local.getNom());
                        }
                    }
                }
            }
            System.out.println();
        }

        // Afficher l'agenda des élèves
        System.out.println("Agenda des élèves :");
        for (Eleve eleve : eleves) {
            System.out.println(eleve.getNomComplet());
            
            // Pour chaque cours de l'élève, vérifier les réservations dans les locaux
            for (Cours coursEleve : eleve.getCoursAssignes()) {
                for (Locaux local : locaux) {
                    // Vérifier les réservations dans chaque local
                    for (Reservation reservation : local.getReservations()) {
                        // Si la réservation concerne le cours de l'élève
                        if (reservation.getCours().getNom().equals(coursEleve.getNom())) {
                            System.out.println(reservation.getDateHeure() + " : " +coursEleve.getNom() + " | " + local.getNom());
                        }
                    }
                }
            }
            System.out.println();
        }

        // Afficher les réservations pour les locaux
        System.out.println("Réservations des locaux :");
        for (Locaux local : locaux) {
            System.out.println(local.getNom());
            for (Reservation reservation : local.getReservations()) {
                // Affichage du nom du cours
                System.out.println(reservation.getDateHeure()+ " : " + reservation.getCours().getNom());
            }
            System.out.println();
        }
    }
}
