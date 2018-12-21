
import au.com.bytecode.opencsv.CSVReader;
import java.util.*;
import java.io.*;

public class CDRS {
     static String res = ""; 
     //Declaring independent sets of genres of movies
     List<String> adventure,drama,mystery,action,comedy,fantasy,thriller,documentary,biography,crime,horror,animation,romance,scifi;
     //Declaring independent sets of genres of books
     List<String> fiction,romance1,scifi1,autobiography,comic,suspense;
     //Declaring a flag variable
     int flag = 0; float BookRating;
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
                 if(bookdata[1].compareTo("Fiction")== 0){
                     fiction.add(bookdata[0]);
                     fiction.add(bookdata[2]);   
                 }
                 else if(bookdata[1].compareTo("Suspense")== 0){
                     suspense.add(bookdata[0]);
                     suspense.add(bookdata[2]);
                 }
                 else if(bookdata[1].compareTo("Comic")== 0){
                     comic.add(bookdata[0]);
                      comic.add(bookdata[2]);
                 }
                 else if(bookdata[1].compareTo("Autobiography")== 0){
                     autobiography.add(bookdata[0]);
                     autobiography.add(bookdata[2]);
                 }
                 else if(bookdata[1].compareTo("Sci - Fi")== 0){
                     scifi1.add(bookdata[0]);
                     scifi1.add(bookdata[2]);
                 }
                 else if(bookdata[1].compareTo("Romance")== 0){
                     romance1.add(bookdata[0]);
                     romance1.add(bookdata[2]);      
                 }
             }
             //System.out.print(fiction + "\n" + suspense + "\n" + comic + "\n" + autobiography + "\n" + scifi1 +"\n" + romance1 +"\n");
             
             filereader.close();
          
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
                 if(moviedata[2].contains("Adventure")){
                     adventure.add(moviedata[1]);
                     adventure.add(moviedata[3]);
                     adventure.add("Adventure");
                 }
                  if(moviedata[2].contains("Drama")){
                     drama.add(moviedata[1]);
                     drama.add(moviedata[3]);
                     drama.add("Drama");
                  }
                  if(moviedata[2].contains("Mystery")){
                     mystery.add(moviedata[1]);
                     mystery.add(moviedata[3]);
                     mystery.add("Mystery");
                  }
                  if(moviedata[2].contains("Action")){
                     action.add(moviedata[1]);
                     action.add(moviedata[3]);
                     mystery.add("Action");
                  }
                  if(moviedata[2].contains("Comedy")){
                     comedy.add(moviedata[1]);
                     comedy.add(moviedata[3]);
                     comedy.add("Comedy");
                  }
                  if(moviedata[2].contains("Fantasy")){
                     fantasy.add(moviedata[1]);
                     fantasy.add(moviedata[3]);
                     fantasy.add("Fantasy");
                  }
                  if(moviedata[2].contains("Thriller")){
                     thriller.add(moviedata[1]);
                     thriller.add(moviedata[3]);
                     thriller.add("Thriller");
                  }
                   if(moviedata[2].contains("Documentary")){
                     documentary.add(moviedata[1]);
                     documentary.add(moviedata[3]); 
                     documentary.add("Documentary");
                   }
                  if(moviedata[2].contains("Biography")){
                     biography.add(moviedata[1]);
                     biography.add(moviedata[3]);
                     biography.add("Biography");
                  }
                  if(moviedata[2].contains("Crime")){
                     crime.add(moviedata[1]);
                     crime.add(moviedata[3]);
                     crime.add("Crime");
                  }
                  if(moviedata[2].contains("Horror")){
                     horror.add(moviedata[1]);
                     horror.add(moviedata[3]);
                     horror.add("Horror");
                  }
                  if(moviedata[2].contains("Animation")){
                     animation.add(moviedata[1]);
                     animation.add(moviedata[3]);
                     animation.add("Animation");
                  }
                  if(moviedata[2].contains("Romance")){
                     romance.add(moviedata[1]);
                     romance.add(moviedata[3]);
                     romance.add("Romance");
                  }
                  if(moviedata[2].contains("Sci-Fi")){
                     scifi.add(moviedata[1]);
                     scifi.add(moviedata[3]);
                     scifi.add("Sci-Fi");
                  }
             }
             filereader1.close();
         }
        
        
         catch(FileNotFoundException f){
             f.printStackTrace();
         }
      
     }
     
     
  public int GenreSearch(List <String> temp, String userInput){
      int i = 0, j=i + 1;
      while( i < temp.size()/2) 
        { 
            if(userInput.equals(temp.get(2*i))) {
                BookRating = Float.parseFloat(temp.get((2*j) - 1));
                return (flag = 1);
            } 
            i++;
        }
      return flag;
  }
         
   public void mappingDataSets(String userInput){
   String UserGenre;
   while(true){
       if(GenreSearch(fiction,userInput)== 1){
           UserGenre = "Fiction";
           break;
   }
       else if(GenreSearch(suspense,userInput)== 1){
           UserGenre = "Suspense";
           break;
   }
       else if(GenreSearch(comic,userInput)== 1){
           UserGenre = "Comic";
           break;
   }
       else if(GenreSearch(autobiography,userInput)== 1){
           UserGenre = "Autobiography";
           break;
   }
       else if(GenreSearch(scifi1,userInput)== 1){
           UserGenre = "Sci-Fi";
           break;
   }
       else if(GenreSearch(romance1,userInput)== 1){
           UserGenre = "Romance";
           break;
   }
       else{
           UserGenre = " not in database";
           break;
   }
   }
   res="\nGenre of the queried book is " + UserGenre +  ",\nRating of the queried book is " + BookRating + ",\n";
   ResultSet(UserGenre);
   }

   public void ResultSet(String userGenre){
       if((adventure.get(2)).equals(userGenre)){
           printRecommendations(adventure);
       }
       else if(drama.get(2).equals(userGenre)){
           printRecommendations(drama);
       }
       else if(mystery.get(2).equals(userGenre)){
           printRecommendations(mystery);
       }
       else if(action.get(2).equals(userGenre)){
           printRecommendations(action);
       }
       else if(comedy.get(2).equals(userGenre)){
           printRecommendations(comedy);
       }
       else if(fantasy.get(2).equals(userGenre)){
           printRecommendations(fantasy);
       }
       else if(thriller.get(2).equals(userGenre)){
           printRecommendations(thriller);
       }
        else if(biography.get(2).equals(userGenre)){
           printRecommendations(biography);
       }
        else if(crime.get(2).equals(userGenre)){
           printRecommendations(crime);
       }
        else if(horror.get(2).equals(userGenre)){
           printRecommendations(horror);
       }
       else if(animation.get(2).equals(userGenre)){
           printRecommendations(animation);
       }
        else if(romance.get(2).equals(userGenre)){
           printRecommendations(romance);
       }
        else if((scifi.get(2)).equals(userGenre)){
           printRecommendations(scifi);
            }
       else
        {
            printRecommendations(adventure);
             
        }
    
   }
   
   public void printRecommendations(List <String> temp )
   {
       Random r = new Random(); 
       List <Integer> unique = new ArrayList<>();
       int count =0, length = temp.size()/3 - 1, min = 0,i;
       System.out.println("Movies Recommendations For you are:");
//       String res = "";
          while(count < 3){
              i = (int)(Math.random() * ((length - 1) - min)) + min;
              if(!unique.contains(i))
               if(Float.parseFloat(temp.get((3*(i+1)) -2)) <= 4.8 && Float.parseFloat(temp.get(3*(i+1) -2)) >= 3 && count < 3){
                   System.out.println("Movie Name: " +temp.get(3*i) + " Rating:" + temp.get(3*(i+1) -2));
                   res += "Movie Name: " +temp.get(3*i) + "\tRating:" + temp.get(3*(i+1) -2) + ",\n";
//                          System.out.println("----Hello how are you------"+ res+"----------------");
                   count++;
                   unique.add(i);
                   
                    }
         }
   
         }
             
   
   
   
   
    public String mainCDRS(String ip) throws IOException,NullPointerException {
//        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Book Name");
        String userInput = ip;
        CDRS obj = new CDRS();
        obj.PopulateDataSets();
        obj.mappingDataSets(userInput);
        System.out.println("----Hello how are you------"+ res+"----------------");
       return res;
    }
}