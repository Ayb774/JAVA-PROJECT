import java.sql.*;
import java.util.ArrayList;

public class ActionBD {

    private String url ;

    public ActionBD()
    {
        this.url = "jdbc:sqlite:dbprojetjava";
    }

    public Connection openConnection()
    {
        try
        {
            return DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void executeScript(String script) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = openConnection();
            statement = connection.createStatement();

            statement.execute(script);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Programmeur> getProgrammeurs()
    {

        ArrayList<Programmeur> listeProgrammeurs = new ArrayList<>();

        try{

            Connection connection = openConnection();
            String query = "select * from programmeur";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Programmeur prog = new Programmeur(
                        resultSet.getInt("ID"),
                        resultSet.getString("NOM"),
                        resultSet.getString("PRENOM"),
                        resultSet.getString("ANNAISSANCE"),
                        resultSet.getFloat("SALAIRE"),
                        resultSet.getFloat("PRIME"),
                        resultSet.getString("PSEUDO"),
                        resultSet.getString("ADRESSE"),
                        resultSet.getString("RESPONSABLE"),
                        resultSet.getString("HOBBY")
                );
                listeProgrammeurs.add(prog);
            }
            return listeProgrammeurs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }








}
