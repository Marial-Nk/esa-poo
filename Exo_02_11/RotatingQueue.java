package Exo_02_11.Exo_02_11;

import java.util.Arrays;
import java.util.Optional;

/**
 * La classe RotatingQueue est une file (FIFO) circulaire dynamique;
 * héritant de la classe Queue, qui redimensionne le tableau interne lorsque
 * la file est pleine ou contient un nombre excessif de cases vides.
 */
public class RotatingQueue extends Queue{

    /**
     * Constructeur de la classe RotatingQueue.
     * Appelle le constructeur de la classe parente Queue.
     */
    public RotatingQueue(){

        super();
    }

    /**
     * Ajoute un élément à la fin de la file.
     * Si la file est pleine, elle augmente la capacité de 5 cases et;
     * copie les éléments dans le nouveau tableau de manière circulaire.
     *
     * @param value La valeur entière à ajouter à la file.
     */
    public void Push (int value) {

        // Si la file est pleine, on augmente la capacité de 5 cases
        if (this.Count() == this.Size()) {
            this.taille += 5 ;
            int [] newtableau = new int [this.taille];

            // Copier les éléments dans le nouveau tableau de manière circulaire
            if (this.queu <= this.tete) {
                // Cas où les éléments sont contigus dans le tableau.
                System.arraycopy(this.montableau, this.queu, newtableau, 0, this.Count());
            }
            else
            {
                // Cas où les éléments sont séparés entre la fin et le début du tableau.
                int tailleFin = this.Size() - this.queu;
                System.arraycopy(this.montableau, this.queu, newtableau, 0, tailleFin);
                System.arraycopy(this.montableau, 0, newtableau, tailleFin, this.tete + 1);
            }
            this.montableau = newtableau;
            this.queu = 0;
            this.tete = this.Count() - 1;
        }

        // Ajoute l'élément dans la file de manière circulaire
        this.tete = (this.tete + 1) % this.Size();
        this.montableau[this.tete] = value;

    }

    /**
     * Retire et retourne l'élément en tête de la file.
     * Si la file est vide, retourne un Optional.empty.
     *
     * @return L'élément retiré sous forme d'Optional, ou Optional.empty si la file est vide.
     */
    @Override
    public Optional<Integer> Pop() {
        // Vérifiez si la file est vide
        if (this.Count() == 0) {
            return Optional.empty();
        }

        // Récupérez l'élément à l'indice queue
        int valeur = this.montableau[this.queu] ;

        // Avancez l'indice queue de manière circulaire
        this.queu = (this.queu + 1) % this.Size();

        // Si la file devient vide après le retrait, réinitialisez les indices
        if (this.Count() == 0 || this.Size() == 1) {
            this.tete = -1;
            this.queu = 0;
        }

        // Rétrécir le tableau si le nombre de cases vides est égal à 5
        if (this.Size() - this.Count() == 5) {
            this.taille -= 5;  // Réduire la taille de 5 cases
            int[] newtableau = new int[this.taille];

            // Copier les éléments dans le nouveau tableau de manière circulaire
            if (this.queu <= this.tete) {
                System.arraycopy(this.montableau, this.queu, newtableau, 0, this.Count());
            } else {
                int tailleFin = this.Size() - this.queu;
                System.arraycopy(this.montableau, this.queu, newtableau, 0, tailleFin);
                System.arraycopy(this.montableau, 0, newtableau, tailleFin, this.tete + 1);
            }

            // Mettre à jour montableau et les indices
            this.montableau = newtableau;
            this.queu = 0;
            this.tete = this.Size() - 1;
        }

        return Optional.of(valeur);

    }

    /**
     * Calcule et retourne le nombre d'éléments présents dans la file.
     * Utilise l'ordre circulaire pour gérer les cas où tete est avant ou après queu.
     *
     * @return Le nombre d'éléments dans la file.
     */
    public int Count () {
        if (this.tete >= this.queu) {
            return (this.tete - this.queu + 1);
        }
        if ((this.tete < this.queu) && (this.tete != -1)) {
            return ((this.tete + 1) + (this.Size() - this.queu));
        }
        return 0;
    }

    /**
     * Affiche les éléments de la file de manière circulaire.
     * Affiche les éléments de queu à tete même si la file est divisée entre la fin et le début du tableau.
     */
    public void Affiche() {
        if (this.tete < 0) {
            System.out.println("La file est vide");
            System.out.println("Nombre d'elt: " +this.Count());
            System.out.println("Taille de la file: " +this.Size());
            return;
        }

        // Cas où tete est avant queue, la file est dans une seule section
        if (this.tete >= this.queu) {
            for (int i = this.queu; i <= this.tete; i++) {
                System.out.print(this.montableau[i] + " | ");
            }

        }

        else {
            // Cas où tete est avant queue dans la rotation circulaire
            for (int i = this.queu; i <= (this.Size() - 1); i++) {
                System.out.print(this.montableau[i] + " | ");
            }

            for (int i = 0; i <= this.tete; i++) {
                System.out.print(this.montableau[i] + " | ");
            }
        }
        System.out.println();
    }

    public void Clear() {
        super.Clear();
    }
}
