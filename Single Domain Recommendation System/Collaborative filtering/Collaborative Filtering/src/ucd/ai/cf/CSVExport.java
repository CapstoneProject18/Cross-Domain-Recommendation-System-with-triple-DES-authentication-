package ucd.ai.cf;
import java.util.Set;
import com.opencsv.*;

//used to export data to csv files to make graphs and analyse data
//not important for rest of file, just used for own analysis
public class CSVExport {
	public static void main(String[] args) throws Exception{
		DatasetReader reader = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_100");
		reader.loadEvaluationProfiles(0.2);
		Set<Profile> profiles = reader.getProfiles();

		MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
		Evaluation evalMSD = new Evaluation(msd);
		
		Pearson pearson = new Pearson(profiles);
		Evaluation evalPearson = new Evaluation(pearson);
		
		MeanRating mean = new MeanRating(profiles);
		Evaluation evalMean = new Evaluation(mean);
		
		evalMean.tempFunction(0.0, "mean_abs.csv");
		evalPearson.tempFunction(0.0, "pearson_abs.csv");
		evalMSD.tempFunction(0.0, "msd_abs.csv");

	}
}
