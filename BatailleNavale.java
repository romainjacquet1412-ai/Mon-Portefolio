import java.util.Scanner;

public class BatailleNavale {

    // Affiche le tableau du joueur
    static void affichagetab(int[][] tab, int nbcase) {
        System.out.print("  ");
        for (int indiceColonne = 0; indiceColonne < nbcase; indiceColonne++) {
            System.out.print(indiceColonne + " ");
        }
        System.out.println();

        for (int i = 0; i < nbcase; i++) {
            System.out.print(i + " "); // numéro de ligne
            for (int j = 0; j < nbcase; j++) {
                System.out.print(tab[i][j] == 1 ? "O " : "~ "); // pion ou case vide
            }
            System.out.println();
        }
    }

    // Affiche le tableau de l'ordinateur avec cases cachées
    static void affichageCache(int[][] tab, int nbcase) {
        System.out.print("  ");
        for (int indiceColonne = 0; indiceColonne < nbcase; indiceColonne++) {
            System.out.print(indiceColonne + " ");
        }
        System.out.println();

        for (int i = 0; i < nbcase; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < nbcase; j++) {
                switch (tab[i][j]) {
                    case 0 -> System.out.print("? "); // non découvert
                    case 2 -> System.out.print("O "); // bateau touché
                    case 3 -> System.out.print("X "); // tir raté
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] tabjoueur = new int[5][5];
        int[][] tabordi = new int[5][5];
        int choixJoueurLigne, choixJoueurColonne;
        boolean recommencer = true;

        while (recommencer) {
            // Initialisation tableau joueur
            for (int i = 0; i < tabjoueur.length; i++)
                for (int j = 0; j < tabjoueur[i].length; j++)
                    tabjoueur[i][j] = 0;

            // Placement des pions joueur
            int compteur = 0;
            while (compteur < 5) {
                affichagetab(tabjoueur, 5);
                do {
                    System.out.println("Ligne (0-4) :");
                    choixJoueurLigne = sc.nextInt();
                    System.out.println("Colonne (0-4) :");
                    choixJoueurColonne = sc.nextInt();

                    if (choixJoueurLigne < 0 || choixJoueurLigne > 4 || choixJoueurColonne < 0 || choixJoueurColonne > 4)
                        System.out.println("Valeurs non comprises entre 0 et 4");
                    else if (tabjoueur[choixJoueurLigne][choixJoueurColonne] != 0)
                        System.out.println("Case déjà prise.");
                } while (choixJoueurLigne < 0 || choixJoueurLigne > 4 || choixJoueurColonne < 0 || choixJoueurColonne > 4
                        || tabjoueur[choixJoueurLigne][choixJoueurColonne] != 0);

                tabjoueur[choixJoueurLigne][choixJoueurColonne] = 1;
                compteur++;
            }

            // Placement aléatoire des pions ordinateur
            compteur = 0;
            while (compteur < 5) {
                int ligne, col;
                do {
                    ligne = (int) (Math.random() * 5);
                    col = (int) (Math.random() * 5);
                } while (tabordi[ligne][col] != 0);
                tabordi[ligne][col] = 1;
                compteur++;
            }

            System.out.println("\nTableau du joueur :");
            affichagetab(tabjoueur, 5);

            int nbPionTrouveJoueur = 0, nbPionTrouveOrdi = 0;
            boolean finPartie = false, finPartieOrdi = false;

            // Boucle du jeu
            while (!finPartie && !finPartieOrdi) {
                // Tour joueur
                System.out.println("\nÀ vous de jouer !");
                affichageCache(tabordi, 5);

                int tirLigne, tirColonne;
                do {
                    System.out.println("Ligne (0-4) :");
                    tirLigne = sc.nextInt();
                    System.out.println("Colonne (0-4) :");
                    tirColonne = sc.nextInt();
                    if (tirLigne < 0 || tirLigne > 4 || tirColonne < 0 || tirColonne > 4)
                        System.out.println("Valeurs non comprises entre 0 et 4 !");
                } while (tirLigne < 0 || tirLigne > 4 || tirColonne < 0 || tirColonne > 4);

                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

                if (tabordi[tirLigne][tirColonne] == 0) {
                    System.out.println("Raté !");
                    tabordi[tirLigne][tirColonne] = 3;
                } else if (tabordi[tirLigne][tirColonne] == 1) {
                    System.out.println("Touché !");
                    tabordi[tirLigne][tirColonne] = 2;
                    nbPionTrouveJoueur++;
                } else {
                    System.out.println("Tir à blanc !");
                }

                System.out.println("\nTableau de l'ordinateur :");
                affichageCache(tabordi, 5);

                if (nbPionTrouveJoueur == 5) {
                    System.out.println("Bravo, vous avez trouvé tous les pions !");
                    finPartie = true;
                    break;
                }

                // Tour ordinateur
                System.out.println("\nTour de l'ordinateur...");
                System.out.println("L'ordinateur réfléchit...");
                int tirOrdiLigne, tirOrdiColonne;
                do {
                    tirOrdiLigne = (int) (Math.random() * 5);
                    tirOrdiColonne = (int) (Math.random() * 5);
                } while (tabjoueur[tirOrdiLigne][tirOrdiColonne] != 0 && tabjoueur[tirOrdiLigne][tirOrdiColonne] != 1);

                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

                if (tabjoueur[tirOrdiLigne][tirOrdiColonne] == 0) {
                    System.out.println("L'ordinateur a tiré en (" + tirOrdiLigne + ", " + tirOrdiColonne + ") → Raté !");
                    tabjoueur[tirOrdiLigne][tirOrdiColonne] = 3;
                } else if (tabjoueur[tirOrdiLigne][tirOrdiColonne] == 1) {
                    System.out.println("L'ordinateur a tiré en (" + tirOrdiLigne + ", " + tirOrdiColonne + ") → Touché !");
                    tabjoueur[tirOrdiLigne][tirOrdiColonne] = 2;
                    nbPionTrouveOrdi++;
                } else {
                    System.out.println("Tir à blanc !");
                }

                System.out.println("\nTableau du joueur mis à jour :");
                affichagetab(tabjoueur, 5);

                if (nbPionTrouveOrdi == 5) {
                    System.out.println("L'ordinateur a trouvé tous vos pions... Vous avez perdu !");
                    finPartieOrdi = true;
                    break;
                }
            }

            // Fin de partie
            System.out.println("\n==============================");
            if (finPartie) System.out.println("Félicitations, vous avez gagné la partie");
            else if (finPartieOrdi) System.out.println("L'ordinateur a gagné la partie");

            System.out.println("Voulez-vous rejouer ? (o/n)");
            char rejouer = sc.next().toLowerCase().charAt(0);
            if (rejouer != 'o') {
                recommencer = false;
                System.out.println("Fin du jeu");
            }
        }
        sc.close();
    }
}
