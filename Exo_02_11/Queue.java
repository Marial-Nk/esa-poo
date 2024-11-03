package Exo_02_11.Exo_02_11;

import java.util.Optional;
import java.util.Arrays;


/**
 * La classe Queue représente une file (FIFO);
 * héritant des fonctionnalités de la classe SQStructure.
 */
public class Queue extends SQStructure {

    // Index indiquant le début de la file dans le tableau montableau.
    int queu ;

    /**
     * Constructeur de la classe Queue.
     * Initialise la file en appelant le constructeur de SQStructure;
     * et initialise queu à 0 (début de la file).
     */
    public Queue () {

       super(); // Appel au constructeur de la classe parente.
       this.queu = 0;
    }

    /**
     * Retire l'élément en tête de la file.
     * Si la file est vide (si queu dépasse tete), retourne un Optional.empty();
     * Sinon, retourne l'élément retiré sous forme d'Optional.
     *
     * @return L'élément retiré de la file, encapsulé dans un `Optional`, ou `Optional.empty()` si la file est vide.
     */
    public Optional<Integer> Pop () {

        // Vérifie si la file est vide.
        if (this.queu > this.tete) {
            return Optional.empty();
        }

        int valeur = this.montableau[this.queu] ; // Récupère la valeur en tête de la file.
        this.queu += 1 ;

        // Condition pour redimensionner le tableau lorsque le nombre d'éléments
        // inutilisés au début de la file atteint 5.
        if (this.Size() - this.Count() == 5)
        {
            int [] newtableau = new int [this.Count()];
            System.arraycopy(this.montableau, this.queu, newtableau, 0, this.Count());
            this.montableau = new int [this.Count()];
            System.arraycopy(newtableau, 0, this.montableau, 0, this.Count());
            this.queu = 0;
            this.tete = newtableau.length - 1 ;
        }

        return Optional.of(valeur) ;
    }

    /**
     * Calcule et retourne le nombre d'éléments dans la file.
     * La différence entre tete et queu représente le nombre d'éléments dans la file.
     *
     * @return Le nombre d'éléments présents dans la file.
     */
    public int Count () {
        return (this.tete - this.queu + 1);
    }

    /**
     * Affiche les éléments de la file, en commençant par l'index queu;
     * jusqu'à l'index tete.
     * Affiche un message si la file est vide.
     */
    public  void Affiche() {

        // Vérifie si la file est vide
        if (this.tete < 0) {
            System.out.println("La file est vide");
            return;
        }

        // Parcourt et affiche les éléments de la file entre queu et tete.
        for (int i = this.queu; i <= this.tete; i++) {
            System.out.print(this.montableau[i] + " | ");
        }
        System.out.println();
    }

    public void Clear() {
        super.Clear();
        this.queu = 0;
    }
}
