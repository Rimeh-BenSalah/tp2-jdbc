package ma.projet.connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantM {

    Connection cn = connexion.getCn();

    // Ajouter un étudiant
    public boolean create(Etudiant o) {
        try {
            PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO Etudiant(nom, prenom, filiere, sexe) VALUES(?,?,?,?)");
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getFiliere());
            ps.setString(4, o.getSexe());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur create : " + e.getMessage());
            return false;
        }
    }

    // Supprimer un étudiant
    public boolean delete(Etudiant o) {
        try {
            PreparedStatement ps = cn.prepareStatement(
                "DELETE FROM Etudiant WHERE id=?");
            ps.setInt(1, o.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur delete : " + e.getMessage());
            return false;
        }
    }

    // Modifier un étudiant
    public boolean update(Etudiant o) {
        try {
            PreparedStatement ps = cn.prepareStatement(
                "UPDATE Etudiant SET nom=?, prenom=?, filiere=?, sexe=? WHERE id=?");
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getFiliere());
            ps.setString(4, o.getSexe());
            ps.setInt(5, o.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur update : " + e.getMessage());
            return false;
        }
    }

    // Chercher par id
    public Etudiant findById(int id) {
        try {
            PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM Etudiant WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etudiant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("sexe"),
                    rs.getString("filiere")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur findById : " + e.getMessage());
        }
        return null;
    }

    // Retourner tous les étudiants
    public List<Etudiant> findAll() {
        List<Etudiant> liste = new ArrayList<>();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Etudiant");
            while (rs.next()) {
                liste.add(new Etudiant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("sexe"),
                    rs.getString("filiere")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erreur findAll : " + e.getMessage());
        }
        return liste;
    }
}