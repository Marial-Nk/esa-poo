package test.cours;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prof.Prof;
import eleve.Eleve;
import cours.Cours;
import cours.GestionCours;
import locaux.Locaux;
import locaux.GestionLocaux;
import java.time.LocalDate;

class CoursTest {

    private Cours cours;
    private Prof professeur;
    private Eleve eleve;
    private GestionCours gestionCours;
    private GestionLocaux gestionLocaux;
    private Locaux local;

    @BeforeEach
    void setUp() {
        cours = new Cours("Mathématiques");
        gestionCours = new GestionCours();
        gestionCours.ajouterCours(cours);
        professeur = new Prof("Durand", "Jean");
        eleve = new Eleve("Dupont", "Luc");
        gestionLocaux = new GestionLocaux();
        local = new Locaux("Salle A", 30);
        gestionLocaux.enregistrerLocal(local); // Enregistrer un local
    }

    @Test
    void testAjouterProfesseur() {
        cours.ajouterProf(professeur);
        assertTrue(cours.getProfsAssignes().contains(professeur), "Le professeur n'a pas été ajouté au cours.");
    }

    @Test
    void testInscrireEleve() {
        cours.inscrireEleve(eleve);
        assertTrue(cours.getElevesInscrits().contains(eleve), "L'élève n'a pas été inscrit au cours.");
    }

    @Test
    void testAjouterReservation() {
        LocalDate dateReservation = LocalDate.of(2025, 6, 1);
        gestionLocaux.reserverLocalPourCours(cours, local, dateReservation);
        assertFalse(local.getReservations().isEmpty(), "La réservation du local n'a pas été effectuée.");
    }

    @Test
    void testJourPremiereReservation() {
        LocalDate dateReservation = LocalDate.of(2025, 6, 1);
        gestionLocaux.reserverLocalPourCours(cours, local, dateReservation);
        assertEquals("MONDAY", cours.getJour(), "Le jour de la première réservation est incorrect.");
    }
}
