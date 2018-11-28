package ucd.ai.cf;

import java.util.Set;

/**
 * Run this class to test your CF Practical 3 code - it will print out the Mean
 * Absolute Error and the Percentage Recommended using MSD with a threshold of 0.0, 0.5, 0.75 and 0.95,
 * and using Pearson with thresholds of 0.0, 0.25, 0.5 and 0.75
 */

public class CFPractical3 {
	public static void main(String[] args) throws Exception{
		//***DatasetReader reader = new DatasetReader("MovieLens/u.item", "MovieLens/u-filtered.data");
		DatasetReader reader = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_100");
		reader.loadEvaluationProfiles(0.2);
		Set<Profile> profiles = reader.getProfiles();

		//MSD L=0.0;0.5;0.75;0.95
		MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
		Evaluation evalMSD = new Evaluation(msd);
		
		System.out.println ("\t\tMAE\tPercentage Recommended");
		System.out.println ("MSD L=0.0" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
		System.out.println ("MSD L=0.5" + "\t" + evalMSD.getMeanAbsoluteError(0.5) + "\t" + evalMSD.getPercentageRecommended(0.5));
		System.out.println ("MSD L=0.75" + "\t" + evalMSD.getMeanAbsoluteError(0.75) + "\t" + evalMSD.getPercentageRecommended(0.75));
		System.out.println ("MSD L=0.95" + "\t" + evalMSD.getMeanAbsoluteError(0.95) + "\t" + evalMSD.getPercentageRecommended(0.95));
		
		//Pearson L=0.0;0.25;0.5;0.75;
		Pearson pearson = new Pearson(profiles);
		Evaluation evalPearson = new Evaluation(pearson);
		
		System.out.println ("\t\tMAE\tPercentage Recommended");
		System.out.println ("Pearson L=-0.25" + "\t" + evalPearson.getMeanAbsoluteError(-0.5) + "\t" + evalPearson.getPercentageRecommended(-0.5));
		System.out.println ("Pearson L=0.0" + "\t" + evalPearson.getMeanAbsoluteError(0.0) + "\t" + evalPearson.getPercentageRecommended(0.0));
		System.out.println ("Pearson L=0.25" + "\t" + evalPearson.getMeanAbsoluteError(0.25) + "\t" + evalPearson.getPercentageRecommended(0.25));
		System.out.println ("Pearson L=0.5" + "\t" + evalPearson.getMeanAbsoluteError(0.5) + "\t" + evalPearson.getPercentageRecommended(0.5));
		System.out.println ("Pearson L=0.75" + "\t" + evalPearson.getMeanAbsoluteError(0.75) + "\t" + evalPearson.getPercentageRecommended(0.75));
		
		//MeanRating L=0.0;
		MeanRating mean = new MeanRating(profiles);
		Evaluation evalMean = new Evaluation(mean);
		
		System.out.println ("\t\tMAE\tPercentage Recommended");
		System.out.println ("MeanRating L=0.0" + "\t" + evalMean.getMeanAbsoluteError(0.0) + "\t" + evalMean.getPercentageRecommended(0.0));

	}
}
