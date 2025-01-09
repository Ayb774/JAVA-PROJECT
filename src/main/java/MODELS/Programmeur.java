package MODELS;

public class Programmeur {
    private String nom;
    private String prenom;
    private String anNaissance;
    private double salaire;
    private double prime;
    private String pseudo;
    private Integer id ;
    private String adresse;
    private String responsable ;
    private String hobby;

    public Programmeur(){};
    public Programmeur(Integer id,String nom, String prenom, String anNaissance, double salaire, double prime, String pseudo,String adresse,String responsable,String hobby) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.responsable = responsable ;
        this.hobby = hobby;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "MODELS.Programmeur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", anNaissance='" + anNaissance + '\'' +
                ", salaire=" + salaire +
                ", prime=" + prime +
                ", pseudo='" + pseudo + '\'' +
                ", id=" + id +
                ", adresse='" + adresse + '\'' +
                ", responsable='" + responsable + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getAnNaissance() {
        return anNaissance;
    }

    public void setAnNaissance(String anNaissance) {
        this.anNaissance = anNaissance;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getId() {
        return id;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}