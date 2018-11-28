/////////////////////////////////////////////////////////////////////////////////////////
/// THIS FILE HAS TO BE COMPLETED BY THE STUDENT
//////////////////////////////////////////////////////////////////////////////////////////
package ucd.ai.cf;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import com.opencsv.*;

/**
 * The Evaluation class runs a series of tests on a <tt>SimilarityMetric</tt>, e.g MeanSquaredDifference or Pearson in order to asses their recommendation performance.
 */
public class Evaluation {

	//	 the metric to be evaluated
	private SimilarityMetric metric;
	private double threshold = Double.MIN_VALUE;

	/**
	 * Constructs an instance of Evaluation
	 * @param metric the similarity metric to be evaluated, e.g. an instance of Pearson or MeanSquaredDifference
	 * Example of use;
	 *  Pearson pearson = new Peason(profiles);
	 *  Evaluation eval = new Evaluation(pearson);
	 *  System.out.println(eval.getMeanAbsoluteError(2.0)); - prints out the mean absolute error for pearson at that threshold
	 */
	public Evaluation(SimilarityMetric metric) {
		this.metric = metric;
	}

	/**
	 * Calculates the mean absolute error for the the given similarity metric.
	 * @param threshold the threshold to pass into the similarity metric.
	 * @return the mean absolute error
	 * 
	 * Note that this method needs to be implemented by the student.
	 */
	public double getMeanAbsoluteError(final double threshold){
		Set<Profile> profiles = metric.getProfileSet();
		
		double absoluteDifference = 0.0, N = 0.0;
		
		// Iterate through profiles
		for(Profile tempProfile : profiles){
			// iterate through target movie list
			for(Movie targetMovie : tempProfile.getTargetMovieList()){
				//make a prediction
				double prediction = metric.predictRating(tempProfile, targetMovie, threshold);
				//if it was successful, compare to the actual rating of the movie
				if( prediction != -1 ){
					N++;
					double actualRating = tempProfile.getTargetRating(targetMovie);
					// compute the absolute difference between the values (absolute error)
					absoluteDifference += Math.abs(actualRating - prediction);
				}
			}
		}
		
		// check if N > 0 to prevent NaN being returned
		if( N > 0){
			// divide the absolute difference by the number of successfully rated movies 
			// to get the Mean Absolute Error
			return absoluteDifference / N;
		} else {
			return 0;
		}
		
	}

	/**
	 * Calculates the percentage of targets for which the metric was able to make a prediction.
	 * @param threshold the threshold to pass into the similarity metric.
	 * @return the percentage of targets for which a recommendation was made (e.g. 0.5 = 50%)
	 * 
	 * Note that this method needs to be implemented by the student.
	 */
	public double getPercentageRecommended(final double threshold){
		Set<Profile> profiles = metric.getProfileSet(); // get set of profiles
		
		double movieCount = 0.0, predictionCount = 0.0;
		
		//iterate through every profile
		for(Profile tempProfile : profiles){
			//iterate through target movies of profile
			for(Movie targetMovie : tempProfile.getTargetMovieList()){
				movieCount++;
				double prediction = metric.predictRating(tempProfile, targetMovie, threshold);
				if(prediction != -1){
					predictionCount++;
				}
			}
		}
		
		// no. of successful predictions / no. of total movies in target list = percentage of target 
		//	movies a prediction as successfully made for
		return  predictionCount / movieCount;
		
	}

	/**
	 * Calculates the standard deviation of the errors.
	 * @param threshold the threshold to pass into the similarity metric.
	 * @return the standard deviation of errors.
	 * 
	 * Note that this method needs to be implemented by the student.
	 */
	public double getStdDeviationError(final double threshold) {
		Set<Profile> profiles = metric.getProfileSet();
		
		double MAE = getMeanAbsoluteError(threshold);
		
		double N = 0.0, sum = 0.0;
		
		// Iterate through profiles
		for(Profile tempProfile : profiles){
			// iterate through target movie list
			for(Movie targetMovie : tempProfile.getTargetMovieList()){
				//make a prediction
				double prediction = metric.predictRating(tempProfile, targetMovie, threshold);
				//if it was successful, compare to the actual rating of the movie
				if( prediction != -1 ){
					N++;
					double actualRating = tempProfile.getTargetRating(targetMovie);
					// compute the absolute difference between the values (absolute error)
					double error = Math.abs(actualRating - prediction);
					sum += Math.pow(error - MAE, 2);
				}
			}
		}
		
		double fraction = sum / N;
		
		return Math.sqrt(fraction);
	}
	
	//crude method to export data to CSV file, adapted multiple times and not needed for rest of program.
	// used for own analysis
	public void tempFunction(final double threshold, String file) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(file), '\t');
		
		Set<Profile> profiles = metric.getProfileSet();
		
		double MAE = getMeanAbsoluteError(threshold);
		
		double N = 0.0, sum = 0.0;
		
		// Iterate through profiles
		for(Profile tempProfile : profiles){
			// iterate through target movie list
			for(Movie targetMovie : tempProfile.getTargetMovieList()){
				//make a prediction
				double prediction = metric.predictRating(tempProfile, targetMovie, threshold);
				//if it was successful, compare to the actual rating of the movie
				if( prediction != -1 ){
					N++;
					double actualRating = tempProfile.getTargetRating(targetMovie);
					// compute the absolute difference between the values (absolute error)
					String error = Double.toString(Math.abs(actualRating - prediction));
					String[] ary = new String[] {error};
					writer.writeNext(ary);
					//sum += Math.pow(error - MAE, 2);
				}
			}
		}
		
		//double fraction = sum / N;
		
		//Math.sqrt(fraction);
	}
	
	/**
	 * Calculates the mean absolute error for the the given similarity metric. 
	 * In this case, for actual ratings of 1, 2, 3, 4, and 5, this method prints:
	 * - the actual rating
	 * - the number of occurrences of the actual rating in the target set
	 * - the mean MAE calculated over each actual rating 	
	 * @param threshold the threshold to pass into the similarity metric.
	 * 
	 * Note that this method needs to be implemented by the student.
	 */
	public void getMeanAbsoluteErrorDist(final double threshold) {
		Set<Profile> profiles = metric.getProfileSet();
		
		double absoluteDifference1 = 0.0, absoluteDifference2 = 0.0, absoluteDifference3 = 0.0, absoluteDifference4 = 0.0, absoluteDifference5 = 0.0 ;
		double rating1 = 0.0, rating2 = 0.0, rating3 = 0.0, rating4 = 0.0, rating5 = 0.0;
		
		// Iterate through profiles
		for(Profile tempProfile : profiles){
			// iterate through target movie list
			for(Movie targetMovie : tempProfile.getTargetMovieList()){
				//make a prediction
				double prediction = metric.predictRating(tempProfile, targetMovie, threshold);
				//if it was successful, compare to the actual rating of the movie
				if( prediction != -1 ){
					double actualRating = tempProfile.getTargetRating(targetMovie);
					
					if(actualRating == 1.0){
						rating1++;
						absoluteDifference1 +=  Math.abs(actualRating - prediction);
					} else if(actualRating == 2.0){
						rating2++;
						absoluteDifference2 +=  Math.abs(actualRating - prediction);
					} else if(actualRating == 3.0){
						rating3++;
						absoluteDifference3 +=  Math.abs(actualRating - prediction);
					} else if (actualRating == 4.0){
						rating4++;
						absoluteDifference4 +=  Math.abs(actualRating - prediction);
					} else if (actualRating == 5.0){
						rating5++;
						absoluteDifference5 +=  Math.abs(actualRating - prediction);
					}
				}
			}
		}
		
		System.out.println ("Actual Rating\tNumber Ratings\t\tMAE");
		System.out.println ("1" + "\t\t" + rating1 + "\t\t\t" + absoluteDifference1 / rating1);
		System.out.println ("2" + "\t\t" + rating2 + "\t\t\t" + absoluteDifference2 / rating2);
		System.out.println ("3" + "\t\t" + rating3 + "\t\t\t" + absoluteDifference3 / rating3);
		System.out.println ("4" + "\t\t" + rating4 + "\t\t\t" + absoluteDifference4 / rating4);
		System.out.println ("5" + "\t\t" + rating5 + "\t\t\t" + absoluteDifference5 / rating5);
	}


	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(final double threshold) {
			this.threshold = threshold;
	}
}
