package ucd.ai.cf;

import java.util.Set;

/**
 * Run this class to test your CF Practical 2 code - it will print out predicted
 * ratings based on MeanSquaredDifference and Pearson 
 */

public class CFPractical2 {

	public static void main(String[] args) throws Exception{
		DatasetReader reader = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_100");

		//loading the evaluation profiles instead of all profiles
		reader.loadEvaluationProfiles(0.2);
		Set<Profile> profiles = reader.getProfiles();

		MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
		Pearson pearson = new Pearson(profiles);
		
		for (Profile profile: profiles){
			for (Movie m: profile.getTargetMovieList()) {
				double msdPredictedRating = msd.predictRating(profile, m, 0.0);
				double pearsonPredictedRating = pearson.predictRating(profile, m, 0.0);
				System.out.print(profile.getUserId() + "\t" + profile.getTargetRating(m) + "\t");
				System.out.print(m.getName() + "\t");
				System.out.print("MSD_prediction=" + msdPredictedRating  + "\t");
				System.out.println("Pearson_prediction=" + pearsonPredictedRating);
			}
		}
	}

}
