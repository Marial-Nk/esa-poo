package Exo_02_11.Exo_02_11;

import java.util.Optional;
import java.util.Random;

public class TestProgram {
    public static void main(String[] args) {
        SQStructure[] structures = {new Queue(), new Stack(), new RotatingQueue()};

        for (var file : structures) {
            String objet;

            if (file instanceof RotatingQueue) {
                objet = "RotatingQueue";
            } else if (file instanceof Queue) {
                objet = "Queue";
            } else if (file instanceof Stack) {
                objet = "Stack";
            } else {
                objet = "Inconnu";
            }

            System.out.printf("Gestion de %s%n", objet);
            System.out.println("****************************");
            System.out.printf("Test %s vide%n", objet);

            Optional<Integer> retour = file.Pop();
            if (retour.isPresent()) {
                System.out.println(retour.get());
            } else {
                System.out.printf("%s est vide%n", objet);
            }
            System.out.println();

            boolean addition = true;
            Random nombre = new Random();
            for (int x = 0; x < 10; x++) {
                for (int y = new Random().nextInt(40) + 1; y > 0; y--) {
                    if (addition) {
                        var valeur = nombre.nextInt(1000) + 1;
                        if (valeur % 13 == 0) {
                            System.out.printf("*** Vidage %s -> Nombre éléments : %d, Taille : %d ***%n", objet, file.Count(), file.Size());
                            file.Clear();
                        }
                        file.Push(valeur);
                        System.out.printf("Valeur ajoutée : %d, Nombre éléments : %d, Taille : %d%n", valeur, file.Count(), file.Size());
                    } else {
                        retour = file.Pop();
                        if (retour.isPresent()) {
                            System.out.printf("Valeur retirée : %d, Nombre éléments : %d, Taille : %d%n", retour.get(), file.Count(), file.Size());
                        } else {
                            System.out.printf("%s est vide%n", objet);
                        }
                    }
                }
                addition = !addition;
            }

            System.out.printf("%nVidage de %s élément par élément%n", objet);
            System.out.println("************************************");
            while ((retour = file.Pop()).isPresent()) {
                System.out.printf("Valeur retirée : %d, Nombre éléments : %d, Taille : %d%n", retour.get(), file.Count(), file.Size());
            }
            System.out.printf("%s est vide%n", objet);

            System.out.println(file.Count());
            System.out.println(file.Size());

            System.out.println("***********************************************************");
        }
    }
}
