package ma.projet.connexion;

public class Etudiant {

    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private String filiere;

    // Constructeur 1 : AVEC id
    public Etudiant(int id, String nom, String prenom, String sexe, String filiere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.filiere = filiere;
    }

    public Etudiant(String nom, String prenom, String sexe, String filiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.filiere = filiere;
    }

    public int getId()          { return id; }
    public String getNom()      { return nom; }
    public String getPrenom()   { return prenom; }
    public String getSexe()     { return sexe; }
    public String getFiliere()  { return filiere; }

    public void setId(int id)           { this.id = id; }
    public void setNom(String nom)      { this.nom = nom; }
    public void setPrenom(String p)     { this.prenom = p; }
    public void setSexe(String s)       { this.sexe = s; }
    public void setFiliere(String f)    { this.filiere = f; }
}