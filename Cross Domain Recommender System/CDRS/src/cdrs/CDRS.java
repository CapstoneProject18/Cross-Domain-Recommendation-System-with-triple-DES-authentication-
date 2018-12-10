package cdrs;
import java.util.*;
import java.io.*;

public class CDRS {
     //Movies dataset
     HashMap<String, Integer> moviesDS = new HashMap<>();
     //Declaring independent sets of genres of movies
     List adventure,drama,mystery,action,comedy,fantasy,thriller,documentary,biography,crime,horror,animation,romance,scifi;
     //Books dataset
     HashMap<String, Integer> booksDS = new HashMap<>();
     //Declaring independent sets of genres of books
     List fiction,romance1,scifi1,autobiography,comic,suspense;
     CDRS(){
         //Creating independent sets of genres of movies
         adventure = new ArrayList();
         drama = new ArrayList();
         mystery = new ArrayList();
         action = new ArrayList();
         comedy = new ArrayList();
         fantasy = new ArrayList();
         thriller = new ArrayList();
         documentary = new ArrayList();
         biography = new ArrayList();
         crime = new ArrayList();
         horror = new ArrayList();
         animation = new ArrayList();
         romance = new ArrayList();
         scifi = new ArrayList();
         
         //Creating independent sets of genres of movies
         fiction = new ArrayList();
         romance1 = new ArrayList();
         scifi1 = new ArrayList();
         autobiography = new ArrayList();
         comic = new ArrayList();
         suspense = new ArrayList();
     }
     
     public void PopulateDataSets(){
         File MDataSet = new File("");
         
     }

    public static void main(String[] args) {

       CDRS obj = new CDRS();
        
       
    }
}