package eleve;

import cours.Cours;
import cours.GestionCours;
import presence.CertificatMedical;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionEleves {

    private List<Eleve> eleves = new ArrayList<>(); // Liste des élèves enregistrés

    /**
     * Enregistre un nouvel élève.
     * 
     * @param eleve L'élève à enregistrer dans la liste.
     */
    public void enregistrerEleve(Eleve eleve) {
        eleves.add(eleve); // Ajoute l'élève à la liste des élèves
    }

    /**
     * Recherche un élève dans la liste par son ID.
     * 
     * @param id L'ID de l'élève à rechercher.
     * @return L'élève trouvé ou null si aucun élève n'a cet ID.
     */
    public Eleve rechercherEleveParId(int id) {
        for (Eleve e : eleves) {
            if (e.getId() == id) {
                return e; // Retourne l'élève si son ID est trouvé
            }
        }
        return null; // Si l'élève n'est pas trouvé, retourne null
    }

    /**
     * Assigne un cours à un élève en utilisant l'ID de l'élève et l'ID du cours.
     * 
     * @param ideleve L'ID de l'élève.
     * @param idCours L'ID du cours à assigner à l'élève.
     * @param gestionCours L'objet qui gère les cours et permet de rechercher un cours par son ID.
     */
    public void assignerCoursAEleve(int ideleve, int idCours, GestionCours gestionCours) {
        Eleve eleve = rechercherEleveParId(ideleve); // Recherche l'élève par son ID
        if (eleve != null) {
            eleve.ajouterCours(gestionCours, idCours); // Assigne le cours à l'élève
        } else {
            System.out.println("L'élève avec ID " + ideleve + " non trouvé."); // Affiche un message d'erreur si l'élève n'est pas trouvé
        }
    }

    /**
     * Affiche la liste de tous les élèves enregistrés.
     */
    public void afficherTousLesEleves() {
        if (eleves.isEmpty()) {
            System.out.println("Aucun élève enregistré."); // Si la liste est vide
        } else {
            System.out.println("\nListe des élèves enregistrés :");
            for (Eleve e : eleves) {
                System.out.println(e); // Affiche chaque élève de la liste
            }
        }
    }

    /**
     * Affiche la liste des élèves inscrits à un cours spécifique.
     * 
     * @param idCours L'ID du cours pour lequel afficher les élèves inscrits.
     */
    public void afficherElevesParCours(int idCours) {
        boolean trouve = false; // Drapeau pour vérifier si des élèves sont inscrits à ce cours
        for (Eleve e : eleves) {
            for (Cours c : e.getCoursAssignes()) {
                if (c.getId() == idCours) {
                    System.out.println("- " + e.getNomComplet()); // Affiche le nom de l'élève inscrit au cours
                    trouve = true; // Si l'élève est trouvé, on met le drapeau à true
                    break;
                }
            }
        }
        if (!trouve) {
            System.out.println("Aucun élève n'est inscrit à ce cours."); // Affiche un message si aucun élève n'est trouvé
        }
    }

    /**
     * Ajoute un certificat médical à un élève.
     * 
     * @param idEleve L'ID de l'élève auquel le certificat sera ajouté.
     * @param nomCertificat Le nom du certificat médical.
     * @param date La date du certificat médical.
     */
    public void ajouterCertificatAEleve(int idEleve, String nomCertificat, LocalDate date) {
        Eleve eleve = rechercherEleveParId(idEleve); // Recherche l'élève par son ID
        if (eleve != null) {
            CertificatMedical certif = new CertificatMedical(nomCertificat, date); // Crée un certificat médical
            eleve.ajouterCertificat(certif); // Ajoute le certificat à l'élève
            System.out.println("Certificat ajouté pour l'élève " + eleve.getNomComplet() + " à la date " + date); // Affiche un message de confirmation
        } else {
            System.out.println("Élève avec ID " + idEleve + " non trouvé."); // Affiche un message si l'élève n'est pas trouvé
        }
    }

    /**
     * Affiche les certificats médicaux d'un élève.
     * 
     * @param idEleve L'ID de l'élève pour lequel afficher les certificats.
     */
    public void afficherCertificatsEleve(int idEleve) {
        Eleve eleve = rechercherEleveParId(idEleve); // Recherche l'élève par son ID
        if (eleve != null) {
            List<CertificatMedical> certificats = eleve.getCertificats();
            if (certificats.isEmpty()) {
                System.out.println("Aucun certificat enregistré pour " + eleve.getNomComplet()); // Si aucun certificat n'est trouvé
            } else {
                System.out.println("Certificats de " + eleve.getNomComplet() + " :");
                for (CertificatMedical c : certificats) {
                    System.out.println("- " + c.getDate() + " : " + c.getNom()); // Affiche chaque certificat médical
                }
            }
        } else {
            System.out.println("Élève avec ID " + idEleve + " non trouvé."); // Affiche un message si l'élève n'est pas trouvé
        }
    }

    /**
     * Affiche la liste des cours assignés à un élève spécifique.
     * 
     * @param idEleve L'ID de l'élève pour lequel afficher les cours.
     */
    public void afficherCoursDUnEleve(int idEleve) {
        Eleve eleve = rechercherEleveParId(idEleve); // Recherche l'élève par son ID
        if (eleve != null) {
            List<Cours> cours = eleve.getCoursAssignes();
            if (cours.isEmpty()) {
                System.out.println("Aucun cours assigné à " + eleve.getNomComplet()); // Si aucun cours n'est assigné à l'élève
            } else {
                System.out.println("Cours assignés à " + eleve.getNomComplet() + " :");
                for (Cours c : cours) {
                    System.out.println("- " + c.getNom()); // Affiche chaque cours assigné
                }
            }
        } else {
            System.out.println("Élève avec ID " + idEleve + " non trouvé."); // Affiche un message si l'élève n'est pas trouvé
        }
    }

    /**
     * Affiche l'état d'admissibilité de l'élève à présenter les examens dans ses cours.
     * 
     * @param idEleve L'ID de l'élève pour lequel vérifier l'admissibilité aux examens.
     */
    public void afficherAdmissibiliteExamen(int idEleve) {
        Eleve eleve = rechercherEleveParId(idEleve); // Recherche l'élève par son ID
        if (eleve != null) {
            System.out.println("État d’admissibilité de " + eleve.getNomComplet() + " :");
            for (Cours cours : eleve.getCoursAssignes()) {
                boolean peut = eleve.peutPresenterExamen(cours);
                System.out.println("- " + cours.getNom() + " : " + (peut ? "✅ Admis" : "❌ Refusé")); // Affiche si l'élève est admissible ou non
            }
        } else {
            System.out.println("Élève avec ID " + idEleve + " non trouvé."); // Affiche un message si l'élève n'est pas trouvé
        }
    }

    // Getter pour la liste des élèves
    public List<Eleve> getEleves() {
        return eleves;
    }
}
