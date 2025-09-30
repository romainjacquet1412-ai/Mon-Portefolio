import java.util.Scanner;

public class shifumi {

    int nbpoints;
    Scanner sc = new Scanner(System.in);

    public void demarrerJeu() {
        boolean rejouer;
        
        do {
            // Affichage des règles
            System.out.println("=== BIENVENUE AU SHIFUMI ===");
            System.out.println("Règles du jeu :");
            System.out.println("- La pierre (¤) bat les ciseaux (>)");
            System.out.println("- Les ciseaux (>) battent la feuille (__)");
            System.out.println("- La feuille (__) bat la pierre (¤)");
            System.out.println("- Même choix : égalité");
            System.out.println("============================");

            // Etape 1 : Définir le nombre de points
            do {
                System.out.println("En combien de points se déroule la partie ? (3, 5 ou 10)");
                if (sc.hasNextInt()) {
                    nbpoints = sc.nextInt();
                } else {
                    System.out.println("Veuillez entrer un nombre valide !");
                    sc.next();
                    nbpoints = 0;
                }
            } while (nbpoints != 3 && nbpoints != 5 && nbpoints != 10);

            System.out.println("Partie en " + nbpoints + " points !");

            int scoreJoueur = 0;
            int scoreOrdi = 0;

            // Etape 6 : Boucle des manches
            while (scoreJoueur < nbpoints && scoreOrdi < nbpoints) {
                
                // Etape 2 : Choix du joueur
                char choixJoueur;
                do {
                    System.out.println("Choisissez un outil : P (pierre), F (feuille), C (ciseaux)");
                    String input = sc.next().toUpperCase();
                    choixJoueur = input.charAt(0);
                } while (choixJoueur != 'P' && choixJoueur != 'F' && choixJoueur != 'C');

                // Etape 8 : Mise en forme avec symboles
                String symboleJoueur = "";
                String choixJoueurTexte = "";
                if (choixJoueur == 'P') {
                    symboleJoueur = "¤";
                    choixJoueurTexte = "PIERRE";
                } else if (choixJoueur == 'F') {
                    symboleJoueur = "__";
                    choixJoueurTexte = "FEUILLE";
                } else {
                    symboleJoueur = ">";
                    choixJoueurTexte = "CISEAUX";
                }
                System.out.println("Vous avez choisi : " + symboleJoueur + " (" + choixJoueurTexte + ")");

                // Etape 3 : Choix aléatoire de l'ordinateur
                int aleatoire = (int)(Math.random() * 3) + 1;
                char choixOrdi;
                String symboleOrdi;
                String choixOrdiTexte;
                if (aleatoire == 1) {
                    choixOrdi = 'P';
                    symboleOrdi = "¤";
                    choixOrdiTexte = "PIERRE";
                } else if (aleatoire == 2) {
                    choixOrdi = 'F';
                    symboleOrdi = "__";
                    choixOrdiTexte = "FEUILLE";
                } else {
                    choixOrdi = 'C';
                    symboleOrdi = ">";
                    choixOrdiTexte = "CISEAUX";
                }

                // Etape 4 : Révélation du suspense
                System.out.print("L'ordinateur choisit");
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println();
                System.out.println("L'ordinateur a choisi : " + symboleOrdi + " (" + choixOrdiTexte + ")");

                // Etape 5 : Déterminer le gagnant
                if (choixJoueur == choixOrdi) {
                    System.out.println("Egalite ! Aucun point.");
                } else if (
                    (choixJoueur == 'P' && choixOrdi == 'C') ||
                    (choixJoueur == 'F' && choixOrdi == 'P') ||
                    (choixJoueur == 'C' && choixOrdi == 'F')
                ) {
                    System.out.println("Vous gagnez cette manche ! +1 point");
                    scoreJoueur++;
                } else {
                    System.out.println("L'ordinateur gagne cette manche ! +1 point");
                    scoreOrdi++;
                }

                // Affichage du score
                System.out.println("Score - Vous : " + scoreJoueur + " | Ordinateur : " + scoreOrdi);
            }

            // Etape 7 : Fin de partie
            if (scoreJoueur == nbpoints) {
                System.out.println("Felicitations ! Vous avez gagne la partie !");
            } else {
                System.out.println("L'ordinateur a gagne la partie !");
            }
            
            // Gestion de rejouer
            char reponse;
            do {
                System.out.println("Voulez-vous rejouer ? (O/N)");
                String input = sc.next().toUpperCase();
                reponse = input.charAt(0);
            } while (reponse != 'O' && reponse != 'N');
            
            rejouer = (reponse == 'O');
            
            if (rejouer) {
                System.out.println("=== NOUVELLE PARTIE ===");
            }
            
        } while (rejouer);

        // Fin du jeu
        System.out.println("Merci d'avoir joue a Shifumi ! A bientot !");
        sc.close();
    }

    public static void main(String[] args) {
        shifumi jeu = new shifumi();
        jeu.demarrerJeu();
    }
}
