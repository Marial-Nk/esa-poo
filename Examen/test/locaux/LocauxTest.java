package test.locaux;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cours.Cours;
import java.time.LocalDate;

import locaux.Locaux;
import locaux.GestionLocaux;

class LocauxTest {

    private Locaux local;
    private Cours cours;
    private GestionLocaux gestionLocaux;

    @BeforeEach
    void setUp() {
        local = new Locaux("Salle A", 30);
        gestionLocaux = new GestionLocaux();
        gestionLocaux.enregistrerLocal(local); // Enregistrer un local
        cours = new Cours("Mathématiques");
    }

    @Test
    void testDisponibiliteLocal() {
        LocalDate date = LocalDate.of(2025, 6, 1);
        assertTrue(local.estDisponible(date), "Le local devrait être disponible.");
        
        // Réserver le local pour un cours
        gestionLocaux.reserverLocalPourCours(cours, local, date);
        assertFalse(local.estDisponible(date), "Le local ne devrait plus être disponible après la réservation.");
    }

    @Test
    void testAjouterReservation() {
        LocalDate dateReservation = LocalDate.of(2025, 6, 1);
        gestionLocaux.reserverLocalPourCours(cours, local, dateReservation);
        assertTrue(local.getReservations().size() > 0, "La réservation n'a pas été ajoutée au local.");
    }
}
