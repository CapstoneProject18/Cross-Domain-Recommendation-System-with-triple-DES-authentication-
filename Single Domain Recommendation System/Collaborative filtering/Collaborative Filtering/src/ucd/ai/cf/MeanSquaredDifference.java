/////////////////////////////////////////////////////////////////////////////////////////
/// THIS FILE HAS TO BE COMPLETED BY THE STUDENT
//////////////////////////////////////////////////////////////////////////////////////////
package ucd.ai.cf;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class is used to compute the mean squared difference profile similarity metric and also to predict ratings
 * 3 methods in this class need to be implemented by the student.
 */
public class MeanSquaredDifference implements SimilarityMetric{

	/**
	 * Constructor for MSD
	 * @param profileSet the set of profiles on which the MSD will operate
	 * Examples of use:
	 * Set profiles = .... load from files
	 * MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
	 */
	public MeanSquaredDifference(final Set<Profile> profileSet){
		setup(profileSet);
	}

	/**
	 * YOU SHOULD IMPLEMENT THIS METHOD
	 * Computes the MSD similarity between 2 profiles. This has to be implemented by the student.
	 * @param a The first profile to compare
	 * @param b The second profile
	 * @return the MSD similarity between the 2 profiles
	 */
	public double computeSimilarity(final Profile a, final Profile b){
		final double N = 50; // constant N
		
		Set<Movie> common = a.getCommonMovies(b); // get set of common movie
		int commonSize = common.size(); //size of set of common movies
		
		double sum = 0; 
		
		if(commonSize > 0){
			for(Movie movie : common){ //iterate through common movies
				sum += Math.pow(a.getRatingFor(movie) - b.getRatingFor(movie), 2); // get the sum of the difference squared
			}
			
			double MSD = sum / commonSize;
			
			// get similarity metric
			double MSD_SIM = 1 - (MSD / (Math.pow(MeanSquaredDifference.MAX_RATING  - MeanSquaredDifference.MIN_RATING, 2)));
			
			// reduce rating value for small common movie set
			if(commonSize < N){
				return MSD_SIM * ( commonSize / N );
			} else {
				return MSD_SIM;
			}
		} else {
			return 0;
		}
	}

	
	/**
	 * Predicts the rating for a movie for the given profile using the MSD metric
	 * @param profile the profile for which the rating will be prdicted
	 * @param movie the movie for which the rating will be made
	 * @param simThreshold the maximum dissimilarity threshold (<i>L</i> as described in Social Information Filtering)
	 * @return the predicted rating that the owner of that profile would have made for that movie
	 */
	public double predictRating(final Profile profile, final Movie movie, final double simThreshold) {
		
		Set<Profile> neighbours = computeNeighbours(profile, simThreshold);
		
		double topOfFraction = 0.0, bottomOfFraction = 0.0;
		
		// Implementation of the equation from the assignment specifications
		//	Summation for loop; loop through every neighbour
		for(Profile tempProfile : neighbours){
			if(tempProfile.hasRated(movie)){ // check if the neighbour has rated the movie
				double similarity = getMSD(profile, tempProfile); // get MeanSquaredDIfference similarity
				topOfFraction += similarity * tempProfile.getRatingFor(movie); // equation for the top of the fraction
				bottomOfFraction += similarity;	// equation for the bottom
			}
		}
		
		if(bottomOfFraction > 0){
			// possibility to return NaN if there are no neighbours who have watched the movie
			double prediction = topOfFraction / bottomOfFraction; // equate fraction
			
			// if prediction greater than MAX_RATING or less than MIN_RATING, set prediction to respective limit
			if	(prediction > MeanSquaredDifference.MAX_RATING){
				prediction = MeanSquaredDifference.MAX_RATING;
			} else if (prediction < MeanSquaredDifference.MIN_RATING){
				prediction = MeanSquaredDifference.MIN_RATING;
			}
			
			return prediction; // return prediction
		} else {
			return -1;
		}
	}
	

	/**
	 * Computes the set of neighbours that will be used in the prediction of a movie rating for a given user.
	 * Note that its suggested that you implement this method but not necessary
	 * @param profile	The profile for which the neihbourhood will be found
	 * @param simThreshold the maximum dissimilarity threshold for the neigbours
	 * @return the set of neighbours that are most similar to the given profile
	 */
	private Set<Profile> computeNeighbours(final Profile profile, final double simThreshold){
		Set<Profile> neighbours = new HashSet<Profile>(); // declare new set to store neighbours
		
		for(Profile tempProfile : profileSet){	// compare every profile
			if(getMSD(profile, tempProfile) > simThreshold && profile.getUserId() != tempProfile.getUserId())	// if similarity is greater than simThreshold
				neighbours.add(tempProfile);	// add to neighbours set
		}
		
		return neighbours; // return set of neighbours
	}


	/////////////////////////////////////////////////////////////////////////////////////////
	// 				DO NOT EDIT BELOW THIS LINE UNLESS YOU KNOW WHAT YOU ARE DOING!
	/////////////////////////////////////////////////////////////////////////////////////////

	private static double MIN_RATING = 1;
	private static double MAX_RATING = 5;
	private double[][] matrix = null;//holds all the computed MSDs
	private Set<Profile> profileSet;

	private void setup(final Set<Profile> profiles){
		matrix = new double[profiles.size()][profiles.size()];
		computeAllMSD(profiles);
		this.profileSet = profiles;
	}

	private void computeAllMSD(final Set<Profile> set){
		for (Profile a: set){
			for (Profile b: set){
				setMSD(a, b, computeSimilarity(a, b));
			}
		}
	}

	/**
	 * Stores the Means Squared Difference (MSD) similary value in memory between 2 profiles
	 * @param a First profile
	 * @param b Second profile
	 * @param the similarity between to 2.
	 * 
	 * Note - do not edit this method or your code WILL fail.
	 */
	// Had to modify this code to allow me to open multiple files and create predictions without getting OutOfBOunds exceptions
	private void setMSD(final Profile a, final Profile b, final double value) {
		matrix[Profile.INSTANCE_COUNT - a.internalID() -1][Profile.INSTANCE_COUNT - b.internalID() -1] = value;
		matrix[Profile.INSTANCE_COUNT - b.internalID() -1][Profile.INSTANCE_COUNT - a.internalID() -1] = value;
	}

	/**
	 * Retrieves the previously computed MSD value between 2 profiles from memory
	 * @param First profile
	 * @param Second profile
	 * @return the MSD value for the 2 profiles
	 * 
	 * Note - do not edit this method or your code WILL fail.
	 */
	// Had to modify this code to allow me to open multiple files and create predictions without getting OutOfBOunds exceptions
	private double getMSD(final Profile a, final Profile b) {
		return matrix[Profile.INSTANCE_COUNT - a.internalID() -1][Profile.INSTANCE_COUNT - b.internalID() -1];
	}

	/**
	 * @return Returns the set of profiles that the similarity metric is working on.
	 */
	public Set<Profile> getProfileSet() {
		return profileSet;
	}

}
