import java.util.ArrayList;

public class Start {

    /*

    Réponse aux questions de la partie A :

    6) je vais utiliser une ArrayList d'objets de type Programmeur
    8) listeProgrammeurs contient les instances de Programmeur 

    */

    public static void main(String[] args) {

        String dropIfExists = "drop table if exists programmeur;";

        String tableCreationScript = """
                create table programmeur(
                ID INTEGER,
                RESPONSABLE VARCHAR(255),
                HOBBY VARCHAR(255),
                ADRESSE VARCHAR(255),
                NOM VARCHAR(255),
                PRENOM VARCHAR(255),
                ANNAISSANCE VARCHAR(4),
                SALAIRE REAL,
                PRIME REAL,
                PSEUDO VARCHAR(255));""";

        String insertScript = """
                INSERT INTO programmeur(ID,RESPONSABLE,HOBBY,ADRESSE,NOM,PRENOM,ANNAISSANCE,SALAIRE,PRIME,PSEUDO)
                VALUES
                (1,'responsable','hobby','5 rue du crime','Simpson','Homer','1989',276,12,'homerdev'),
                (2,'responsable','hobby','5 rue du crime','Simpson','dzoz','1989',276,12,'homerdev'),
                (3,'responsable','hobby','5 rue du crime','Simpson','dak','1989',276,12,'homerdev'),
                (4,'responsable','hobby','5 rue du crime','Simpson','func','1989',276,12,'homerdev')
                """;
        ActionBD action = new ActionBD();

        action.executeScript(dropIfExists);
        System.out.println("Execution du script de création de la table : ");
        action.executeScript(tableCreationScript);
        System.out.println("Fin du script de création de la table\n");


        System.out.println("Execution du script d'insertion des données : ");
        action.executeScript(insertScript);
        System.out.println("Fin du script d'insertion des données\n");

        System.out.println("Récupération de tous les programmeurs :");
        ArrayList<Programmeur> listeProgrammeurs = action.getProgrammeurs();

        System.out.println("affichage de tous les programmeurs :");
        System.out.println(listeProgrammeurs);

        System.out.println("Affichage des programmeurs dont le nom n'est pas simpson");
        for(Programmeur p : listeProgrammeurs)
        {
            if(!p.getNom().equals("Simpson"))
            {
                System.out.println(p);
            }
        }

        System.out.println("FIN /!/ ");




    }
}
