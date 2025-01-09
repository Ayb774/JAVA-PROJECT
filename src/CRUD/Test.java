package CRUD;

import MODELS.Programmeur;

import java.util.ArrayList;

public class Test {


    public static void main(String[] args) {


        //  TEST

        ProgrammeurDAO dao = new ProgrammeurDAO();
        Programmeur p1 = new Programmeur(1,"lks","lsk","2000",2222,222,"lsk92","djizaijdiza","diihai","djziajidj");
        try
        {


            ArrayList<Programmeur> allProgrammeurs = ProgrammeurDAO.getAllUsers();
            System.out.println(allProgrammeurs);
            ProgrammeurDAO.updateSalaire(9555,2);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
