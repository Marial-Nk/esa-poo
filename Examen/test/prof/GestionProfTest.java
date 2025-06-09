package test.prof;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cours.GestionCours;
import cours.Cours;
import prof.GestionProf;
import prof.Prof;

class GestionProfTest {

    private GestionProf gestionProf;
    private Prof professeur;
    private Cours cours;
    private GestionCours gestionCours;

    @BeforeEach
    void setUp() {
        gestionProf = new GestionProf();
        gestionCours = new GestionCours();
        professeur = new Prof("Durand", "Jean");
        cours = new Cours("Mathématiques");
        gestionCours.ajouterCours(cours); // Ajouter un cours à la gestion
        gestionProf.enregistrerProfesseur(professeur); // Enregistrer un professeur
    }

    @Test
    void testEnregistrerProfesseur() {
        assertEquals(1, gestionProf.getProfesseurs().size(), "Le professeur n'a pas été correctement enregistré.");
    }

    @Test
    void testAssignerCoursAProfesseur() {
        gestionProf.assignerCoursAProfesseur(professeur.getId(), cours.getId(), gestionCours);
        assertTrue(professeur.getCoursAssignes().contains(cours), "Le cours n'a pas été correctement assigné au professeur.");
    }

    @Test
    void testRechercherProfesseurParId() {
        Prof professeurTrouve = gestionProf.rechercherProfesseurParId(professeur.getId());
        assertNotNull(professeurTrouve, "Le professeur n'a pas été trouvé par ID.");
    }

    @Test
    void testAffichageProfesseurs() {
        gestionProf.afficherTousLesProfesseurs(); // Cette méthode affiche dans la console, vérifier visuellement
    }
}
