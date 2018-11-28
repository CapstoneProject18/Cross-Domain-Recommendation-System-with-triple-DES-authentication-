package ucd.ai.cf;

import java.util.Set;

public class CFReport3part2 {
	public static void main(String[] args) throws Exception{
		
		System.out.println ("Perc.\tMAE\t\t\tMAE Std. Dev.\t\tCoverage");
		
		for(int i = 1; i <= 10; i++){
			String perc = Integer.toString(i) +"0";
			String file = "MovieLens/u.data_" + perc;
			DatasetReader reader = new DatasetReader("MovieLens/u.item", file);
			reader.loadEvaluationProfiles(0.2);
			Set<Profile> profiles = reader.getProfiles();
			
			MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
			Evaluation evalMSD = new Evaluation(msd);
			System.out.println (perc + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
		}
		
		System.out.println("");
		System.out.println ("Perc.\tMAE\t\t\tMAE Std. Dev.\t\tCoverage");
		
		for(int i = 1; i <= 10; i++){
			String perc = Integer.toString(i) +"0";
			String file = "MovieLens/u.data_" + perc;
			DatasetReader reader = new DatasetReader("MovieLens/u.item", file);
			reader.loadEvaluationProfiles(0.2);
			Set<Profile> profiles = reader.getProfiles();
			
			Pearson pearson = new Pearson(profiles);
			Evaluation evalPearson = new Evaluation(pearson);
			System.out.println (perc + "\t" + evalPearson.getMeanAbsoluteError(0.0) + "\t" + evalPearson.getStdDeviationError(0.0) + "\t" + evalPearson.getPercentageRecommended(0.0));			
		}
		
		System.out.println("");
		System.out.println ("Perc.\tMAE\t\t\tMAE Std. Dev.\t\tCoverage");
		
		for(int i = 1; i <= 10; i++){
			String perc = Integer.toString(i) +"0";
			String file = "MovieLens/u.data_" + perc;
			DatasetReader reader = new DatasetReader("MovieLens/u.item", file);
			reader.loadEvaluationProfiles(0.2);
			Set<Profile> profiles = reader.getProfiles();
			
			MeanRating mean = new MeanRating(profiles);
			Evaluation evalMean = new Evaluation(mean);
			System.out.println (perc + "\t" + evalMean.getMeanAbsoluteError(0.0) + "\t" + evalMean.getStdDeviationError(0.0) + "\t" + evalMean.getPercentageRecommended(0.0));			
		}
		
//		//***DatasetReader reader = new DatasetReader("MovieLens/u.item", "MovieLens/u-filtered.data");
//		DatasetReader reader = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_10");
//		reader.loadEvaluationProfiles(0.2);
//		Set<Profile> profiles = reader.getProfiles();
//		
//
//		//MSD L=0.0;0.5;0.75;0.95
//		MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
//		Evaluation evalMSD = new Evaluation(msd);
//		System.out.println ("Perc.\tMAE\t\t\tMAE Std. Dev.\t\tCoverage");
//		System.out.println ("10" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader2 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_20");
//		reader2.loadEvaluationProfiles(0.2);
//		profiles = reader2.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("20" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//
//		DatasetReader reader3 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_30");
//		reader3.loadEvaluationProfiles(0.2);
//		profiles = reader3.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("30" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader4 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_40");
//		reader4.loadEvaluationProfiles(0.2);
//		profiles = reader4.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("40" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader5 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_50");
//		reader5.loadEvaluationProfiles(0.2);
//		profiles = reader5.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("50" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader6 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_60");
//		reader6.loadEvaluationProfiles(0.2);
//		profiles = reader6.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("60" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//
//		DatasetReader reader7 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_70");
//		reader7.loadEvaluationProfiles(0.2);
//		profiles = reader7.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("70" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader8 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_80");
//		reader8.loadEvaluationProfiles(0.2);
//		profiles = reader8.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("80" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader9 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_90");
//		reader9.loadEvaluationProfiles(0.2);
//		profiles = reader9.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("90" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//		
//		DatasetReader reader10 = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_100");
//		reader10.loadEvaluationProfiles(0.2);
//		profiles = reader10.getProfiles();
//		msd = new MeanSquaredDifference(profiles);
//		evalMSD = new Evaluation(msd);
//		System.out.println ("100" + "\t" + evalMSD.getMeanAbsoluteError(0.0) + "\t" + evalMSD.getStdDeviationError(0.0) + "\t" + evalMSD.getPercentageRecommended(0.0));
//

	}
}
