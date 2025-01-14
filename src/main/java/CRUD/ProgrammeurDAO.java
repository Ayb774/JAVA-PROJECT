package CRUD;

import MODELS.Programmeur;

import java.sql.*;
import java.util.ArrayList;

public class ProgrammeurDAO {

    private static final String INSERT = "insert into programmeur (RESPONSABLE,HOBBY,ADRESSE,NOM,PRENOM,ANNAISSANCE,SALAIRE,PRIME,PSEUDO) values(?,?,?,?,?,?,?,?,?)";
    private static final String DELETE = "delete from programmeur where id = ?";
    private static final String SELECT_ID = "select * from programmeur where id = ?";
    private static final String SELECT_ALL = "select * from programmeur";
    private static final String UPDATE_SALAIRE = "update programmeur set salaire = ? where id = ?";



    public static void insertUser(Programmeur programmeur) throws SQLException {
        try (Connection conn = DbConfig.getConnection()) {
            String insertQuery = "INSERT INTO programmeur (nom, prenom, adresse, responsable, hobby, anNaissance, salaire, prime, pseudo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, programmeur.getNom());
                stmt.setString(2, programmeur.getPrenom());
                stmt.setString(3, programmeur.getAdresse());
                stmt.setString(4, programmeur.getResponsable());
                stmt.setString(5, programmeur.getHobby());
                stmt.setString(6, programmeur.getAnNaissance());
                stmt.setDouble(7, programmeur.getSalaire());
                stmt.setDouble(8, programmeur.getPrime());
                stmt.setString(9, programmeur.getPseudo());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("La création de l'utilisateur a échoué");
                }
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    programmeur.setId(rs.getInt(1));
                }
            }
        }
    }


    public static void deleteUser(int id) throws SQLException {
        try (Connection conn = DbConfig.getConnection()) {
            String deleteQuery = "DELETE FROM programmeur WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
                stmt.setInt(1, id);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Le programmeur avec l'ID " + id + " n'existe pas.");
                }
            }
        }
    }


    public static Programmeur getUserById(int id) throws SQLException {

        try (Connection conn = DbConfig.getConnection();) {

            PreparedStatement stmt = conn.prepareStatement(SELECT_ID, Statement.RETURN_GENERATED_KEYS);
            {
                stmt.setInt(1, id);


                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Programmeur p = new Programmeur();
                        p.setId(rs.getInt("id"));
                        p.setNom(rs.getString("nom"));
                        p.setPrenom(rs.getString("prenom"));
                        p.setAdresse(rs.getString("adresse"));
                        p.setResponsable(rs.getString("responsable"));
                        p.setHobby(rs.getString("hobby"));
                        p.setAnNaissance(rs.getString("anNaissance"));
                        p.setSalaire(rs.getDouble("salaire"));
                        p.setPrime(rs.getDouble("prime"));
                        p.setPseudo(rs.getString("pseudo"));
                        return p;
                    }
                    return null;
                }

            }
        }

    }

    public static ArrayList<Programmeur> getAllUsers() throws SQLException {

        try (Connection conn = DbConfig.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
            try (ResultSet rs = stmt.executeQuery()) {
                ArrayList<Programmeur> list = new ArrayList<>();
                while (rs.next()) {
                    Programmeur p = new Programmeur();
                    p.setId(rs.getInt("id"));
                    p.setNom(rs.getString("nom"));
                    p.setPrenom(rs.getString("prenom"));
                    p.setAdresse(rs.getString("adresse"));
                    p.setResponsable(rs.getString("responsable"));
                    p.setHobby(rs.getString("hobby"));
                    p.setAnNaissance(rs.getString("anNaissance"));
                    p.setSalaire(rs.getDouble("salaire"));
                    p.setPrime(rs.getDouble("prime"));
                    p.setPseudo(rs.getString("pseudo"));
                    list.add(p);

                }
                return list;
            }

        }
    }
    public static void updateUser(Programmeur programmeur) throws SQLException {
        String updateQuery = "UPDATE programmeur SET nom = ?, prenom = ?, adresse = ?, responsable = ?, hobby = ?, anNaissance = ?, salaire = ?, prime = ?, pseudo = ? WHERE id = ?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, programmeur.getNom());
            stmt.setString(2, programmeur.getPrenom());
            stmt.setString(3, programmeur.getAdresse());
            stmt.setString(4, programmeur.getResponsable());
            stmt.setString(5, programmeur.getHobby());
            stmt.setString(6, programmeur.getAnNaissance());
            stmt.setDouble(7, programmeur.getSalaire());
            stmt.setDouble(8, programmeur.getPrime());
            stmt.setString(9, programmeur.getPseudo());
            stmt.setInt(10, programmeur.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La mise à jour a échoué, aucun programmeur trouvé avec l'ID : " + programmeur.getId());
            }
        }
    }


    public static void updateSalaire(double newSalaire,int id) throws SQLException {

        try (Connection conn = DbConfig.getConnection();) {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_SALAIRE);
            stmt.setDouble(1, newSalaire);
            stmt.setInt(2,id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("L'utilisateur n'existe pas");
            }

        }
    }
}
