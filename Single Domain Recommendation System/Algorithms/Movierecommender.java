import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
public class MovieRecommender {

	public static void main (String [] args)
	{
		  try{
		         //creating data model
		         DataModel datamodel = new FileDataModel(new File("data/moviedata.csv")); //data
		         File file = new File("data/output.txt");
		         file.createNewFile();
		         FileWriter writer = new FileWriter(file); 
		         
		         //Creating UserSimilarity object.
		         UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(datamodel);
		      
		         //UserNeighbourHHood object.
		         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(0.1, usersimilarity, datamodel);
		      
		         //UserRecomender
		         UserBasedRecommender recommender = new GenericUserBasedRecommender(datamodel, userneighborhood, usersimilarity);
		         
		         //Write recommendations to file
		         String writeToFile = "";
		            for (LongPrimitiveIterator users = datamodel.getUserIDs(); users.hasNext();) 
		               {
		                long userId = users.next();
		                List<RecommendedItem> recommendations = recommender.recommend(userId, 10);
		                writeToFile = userId + "[" ;
		                for (RecommendedItem recommendation : recommendations) 
		                {
		                	writeToFile =  writeToFile + recommendation.getItemID() +":" + recommendation.getValue() +",";
		                	
		                }
		                writeToFile = writeToFile + "]" + "\n";
		                writer.write(writeToFile);
		                
		              }
		      writer.flush();
		      writer.close();
		      }
		  catch(Exception e){e.printStackTrace();}
		      
		 }
	}