package cdrs;
import au.com.bytecode.opencsv.CSVReader;
import java.util.*;
import java.io.*;

public class CDRS {
     //Movies dataset
     HashMap<String, Integer> moviesDS = new HashMap<>();
     //Declaring independent sets of genres of movies
     List<String> adventure,drama,mystery,action,comedy,fantasy,thriller,documentary,biography,crime,horror,animation,romance,scifi;
     //Books dataset
     HashMap<String, Integer> booksDS = new HashMap<>();
     //Declaring independent sets of genres of books
     List<String> fiction,romance1,scifi1,autobiography,comic,suspense;
     CDRS(){
         //Creating independent sets of genres of movies
         adventure = new ArrayList<>();
         drama = new ArrayList<>();
         mystery = new ArrayList<>();
         action = new ArrayList<>();
         comedy = new ArrayList<>();
         fantasy = new ArrayList<>();
         thriller = new ArrayList<>();
         documentary = new ArrayList<>();
         biography = new ArrayList<>();
         crime = new ArrayList<>();
         horror = new ArrayList<>();
         animation = new ArrayList<>();
         romance = new ArrayList<>();
         scifi = new ArrayList<>();
         
         //Creating independent sets of genres of movies
         fiction = new ArrayList<>();
         romance1 = new ArrayList<>();
         scifi1 = new ArrayList<>();
         autobiography = new ArrayList<>();
         comic = new ArrayList<>();
         suspense = new ArrayList<>();
     }
     
     public void PopulateDataSets()throws IOException{
         String FileName="C:\\Users\\gaura\\Documents\\NetBeansProjects\\CDRS\\DataSets\\Book_Dataset.csv";
         try{
             FileReader filereader = new FileReader(FileName); //Reading books dataset
             CSVReader bookcsv = new CSVReader(filereader, ',');// extended jar for dealing with csv files
           //Filling Data into independent sets of genres of books
             String bookdata[];
             while((bookdata = bookcsv.readNext()) != null){
                 if(bookdata[1].compareTo("Fiction")== 0)
                     fiction.add(bookdata[0]);
                 else if(bookdata[1].compareTo("Suspense")== 0)
                     suspense.add(bookdata[0]);
                 else if(bookdata[1].compareTo("Comic")== 0)
                     comic.add(bookdata[0]);
                 else if(bookdata[1].compareTo("Autobiography")== 0)
                     autobiography.add(bookdata[0]);
                 else if(bookdata[1].compareTo("Sci - Fi")== 0)
                     scifi1.add(bookdata[0]);
                 else if(bookdata[1].compareTo("Romance")== 0)
                     romance1.add(bookdata[0]);        
             }
             System.out.print(fiction + "\n" + suspense + "\n" + comic + "\n" + autobiography + "\n" + scifi1 +"\n" + romance1 +"\n");
             
             filereader.close();
             System.out.print(bookcsv);
         }
        
        
         catch(FileNotFoundException f){
             f.printStackTrace();
         }
        
        String FileName2="C:\\Users\\gaura\\Documents\\NetBeansProjects\\CDRS\\DataSets\\Movies_Database.csv";
          try{
             FileReader filereader1 = new FileReader(FileName2); //Reading books dataset
             CSVReader moviecsv = new CSVReader(filereader1, ',');// extended jar for dealing with csv files
           //Filling Data into independent sets of genres of books
             String moviedata[];
             while((moviedata = moviecsv.readNext()) != null){
                 if(moviedata[2].contains("Adventure"))
                     adventure.add(moviedata[1]);
                  if(moviedata[2].contains("Drama"))
                     drama.add(moviedata[1]);
                  if(moviedata[2].contains("Mystery"))
                     mystery.add(moviedata[1]);
                  if(moviedata[2].contains("Action"))
                     action.add(moviedata[1]);
                  if(moviedata[2].contains("Comedy"))
                     comedy.add(moviedata[1]);
                  if(moviedata[2].contains("Fantasy"))
                     fantasy.add(moviedata[1]);     
                  if(moviedata[2].contains("Thriller"))
                     thriller.add(moviedata[1]);
                   if(moviedata[2].contains("Documentary"))
                     documentary.add(moviedata[1]);
                  if(moviedata[2].contains("Biography"))
                     biography.add(moviedata[1]);
                  if(moviedata[2].contains("Crime"))
                     crime.add(moviedata[1]);
                  if(moviedata[2].contains("Horror"))
                     horror.add(moviedata[1]);
                  if(moviedata[2].contains("Animation"))
                     animation.add(moviedata[1]);
                  if(moviedata[2].contains("Romance"))
                     romance.add(moviedata[1]);
                  if(moviedata[2].contains("Sci-Fi"))
                     scifi.add(moviedata[1]);
    
             }
             System.out.print("\n");
             System.out.print(horror);
             System.out.print("\n");
             System.out.print(drama);
             filereader1.close();
         }
        
        
         catch(FileNotFoundException f){
             f.printStackTrace();
         }
      
     }
         
         

    public static void main(String[] args) throws IOException {

       CDRS obj = new CDRS();
       obj.PopulateDataSets();
        
       
    }
}
