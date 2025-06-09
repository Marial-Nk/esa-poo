package test.eleve;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cours.GestionCours;
import cours.Cours;
import eleve.Eleve;
import presence.CertificatMedical;

import java.time.LocalDate;

class EleveTest {

    private Eleve eleve;
    private Cours cours;
    private GestionCours gestionCours;

    @BeforeEach
    void setUp() {
        eleve = new Eleve("Durand", "Alice");
        gestionCours = new GestionCours();
        cours = new Cours("Mathématiques");
        gestionCours.ajouterCours(cours); // Ajouter un cours à la gestion
    }

    @Test
    void testAjoutCours() {
        eleve.ajouterCours(gestionCours, cours.getId());
        assertTrue(eleve.getCoursAssignes().contains(cours), "Le cours n'a pas été ajouté à l'élève.");
    }

    @Test
    void testAjoutCertificat() {
        CertificatMedical certificat = new CertificatMedical("Maladie", LocalDate.now());
        eleve.ajouterCertificat(certificat);
        assertTrue(eleve.getCertificats().contains(certificat), "Le certificat médical n'a pas été ajouté.");
    }

    @Test
    void testAdmissibiliteExamen() {
        // Simuler l'absence et la présence d'un élève pour un examen
        eleve.ajouterAbsenceNonJustifiee(cours); // Ajouter une absence non justifiée
        assertFalse(eleve.peutPresenterExamen(cours), "L'élève ne devrait pas être admissible à l'examen.");
    }

    @Test
    void testAffichageEtatParCours() {
        eleve.afficherEtatParCours(); // Cette méthode affiche dans la console, vérifier visuellement
    }
}
