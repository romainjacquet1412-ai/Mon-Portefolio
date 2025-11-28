
public class Livre {

    private String titre;
    private String auteur;
    private int prix;

    public Livre() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void afficher() {
        System.out.println("Livre : " + titre);
        System.out.println("Écrit par : " + auteur);
        System.out.println("Prix : " + prix + " €");
    }

    public static void main(String[] args) {
        Livre monLivre = new Livre();

        monLivre.setTitre("Le Petit Prince");
        monLivre.setAuteur("Antoine de Saint-Exupéry");
        monLivre.setPrix(12);

        monLivre.afficher();
    }
}
