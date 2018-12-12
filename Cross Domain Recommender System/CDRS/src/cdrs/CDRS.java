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
         adventure = new ArrayList<String>();
         drama = new ArrayList<String>();
         mystery = new ArrayList<String>();
         action = new ArrayList<String>();
         comedy = new ArrayList<String>();
         fantasy = new ArrayList<String>();
         thriller = new ArrayList<String>();
         documentary = new ArrayList<String>();
         biography = new ArrayList<String>();
         crime = new ArrayList<String>();
         horror = new ArrayList<String>();
         animation = new ArrayList<String>();
         romance = new ArrayList<String>();
         scifi = new ArrayList<String>();
         
         //Creating independent sets of genres of movies
         fiction = new ArrayList<String>();
         romance1 = new ArrayList<String>();
         scifi1 = new ArrayList<String>();
         autobiography = new ArrayList<String>();
         comic = new ArrayList<String>();
         suspense = new ArrayList<String>();
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
             System.out.print(fiction + "\n" + suspense + "\n" + comic + "\n" + autobiography + "\n" + scifi1 +"\n" + romance1);
             filereader.close();
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