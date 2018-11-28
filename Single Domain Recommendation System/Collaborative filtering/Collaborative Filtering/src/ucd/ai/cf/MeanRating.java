/////////////////////////////////////////////////////////////////////////////////////////
/// THIS FILE HAS TO BE COMPLETED BY THE STUDENT
//////////////////////////////////////////////////////////////////////////////////////////
package ucd.ai.cf;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class is used to get the mean rating, it uses the Similarity Metric interface for it to work alongside the rest of the program
 * 3 methods in this class need to be implemented by the student.
 */
public class MeanRating implements SimilarityMetric{

	
	public MeanRating(final Set<Profile> profileSet){
		setup(profileSet);
	}

	/**
	 * returns 1, all profiles have equal similarity
	 */
	public double computeSimilarity(final Profile a, final Profile b){
		return 1.0;
	}

	
	/**
	 * Returns the mean rating of a movie
	 */
	public double predictRating(final Profile profile, final Movie movie, final double simThreshold) {
		
		double count = 0.0;
		Set<Profile> neighbours = computeNeighbours(profile, simThreshold);
		
		double topOfFraction = 0.0;
		
		// Implementation of the equation from the assignment specifications
		//	Summation for loop; loop through every neighbour
		for(Profile tempProfile : neighbours){
			if(tempProfile.hasRated(movie)){ // check if the neighbour has rated the movie
				count++;
				topOfFraction += tempProfile.getRatingFor(movie);
			}
		}
		
		if(count > 0){
			if ((topOfFraction / count) > 5)
				return 5;
			else if( (topOfFraction / count < 0) )
				return 0;
			else
				return topOfFraction / count;
		} else
			return -1;
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
			if(getMean(profile, tempProfile) > simThreshold && profile.getUserId() != tempProfile.getUserId())	// if similarity is greater than simThreshold
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
		computeAllMean(profiles);
		this.profileSet = profiles;
	}

	private void computeAllMean(final Set<Profile> set){
		for (Profile a: set){
			for (Profile b: set){
				setMean(a, b, computeSimilarity(a, b));
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
	private void setMean(final Profile a, final Profile b, final double value) {
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
	private double getMean(final Profile a, final Profile b) {
		return matrix[Profile.INSTANCE_COUNT - a.internalID() -1][Profile.INSTANCE_COUNT - b.internalID() -1];
	}

	/**
	 * @return Returns the set of profiles that the similarity metric is working on.
	 */
	public Set<Profile> getProfileSet() {
		return profileSet;
	}

}
