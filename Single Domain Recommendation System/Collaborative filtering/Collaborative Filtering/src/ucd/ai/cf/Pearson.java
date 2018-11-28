package ucd.ai.cf;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class is used to compute the pearson profile similarity metric and also to predict ratings
 * 3 methods in this class need to be implemented by the student.
 */
public class Pearson implements SimilarityMetric{

	/**
	 * Constructor for Pearson
	 * @param profileSet the set of profiles on which Pearson will operate
	 * Examples of use:
	 * Set profiles = .... load from files
	 * Pearson pearson = new Pearson(profiles);
	 */
	public Pearson(final Set<Profile> profileSet){
		setup(profileSet);
	}

	/**
	 * YOU SHOULD IMPLEMENT THIS METHOD
	 * Computes the pearson correlation coefficient (similarity) between 2 profiles. This has to be implemented by the student.
	 * @param a The first profile to compare
	 * @param b The second profile
	 * @return the pearson profile similarity between the 2 profiles
	 */
	public double computeSimilarity(final Profile a, final Profile b) {
		
		Set<Movie> common = a.getCommonMovies(b); // get common set of movies
		int commonSize = common.size(); // size of common set
		
		double topOfFractionSum = 0.0, aProfileBottomSum = 0.0, bProfileBottomSum = 0.0; // initialize variables for different parts of the sum
		
		
		for(Iterator<Movie> it = common.iterator(); it.hasNext();){ // master summation loop
			Movie movie = it.next();

			topOfFractionSum += ( a.getRatingFor(movie) - a.getMeanRating() ) * ( b.getRatingFor(movie) - b.getMeanRating() );
			aProfileBottomSum += Math.pow( ( a.getRatingFor(movie) - a.getMeanRating() ), 2);
			bProfileBottomSum += Math.pow( ( b.getRatingFor(movie) - b.getMeanRating() ), 2);
		}
		
		double bottomOfFraction = Math.sqrt(aProfileBottomSum * bProfileBottomSum); // compute bottom of the Pearson fraction
	
		
		// check for division by 0
		if( bottomOfFraction > 0){
			if(commonSize < 50){
				return (commonSize * 1.0 / 50 ) * (topOfFractionSum/bottomOfFraction);
			} else {
				return topOfFractionSum / bottomOfFraction;
			}
		} else {
			return 0;
		}
		
	}


	/**
	 * Predicts the rating for a movie for the given profile using the Pearson similarity metric
	 * @param profile the profile for which the rating will be prdicted
	 * @param movie the movie for which the rating will be made
	 * @param simThreshold the maximum dissimilarity threshold (<i>L</i> as described in Social Information Filtering)
	 * @return the predicted rating that the owner of that profile would have made for that movie
	 */
	public double predictRating(final Profile profile, final Movie m, final double minThreshold) {
		Set<Profile> neighbours = computeNeighbours(profile, minThreshold);
		
		double topOfFraction = 0.0, bottomOfFraction = 0.0;
		
		// Implementation of equation from the notes
		// Summation loop, loop through every neighbour
		for(Profile tempProfile : neighbours){
			if(tempProfile.hasRated(m)){ // If the neighbour has rated the movie we want a prediction for
				double similarity = getPearson(profile, tempProfile); // Get Pearson similarity
				topOfFraction += similarity * ( tempProfile.getRatingFor(m) - tempProfile.getMeanRating() ); // equate top of fraction
				bottomOfFraction += Math.abs(similarity); // equate bottom of fraction
			}
		}
		
		if (bottomOfFraction > 0){
			double prediction = profile.getMeanRating() + topOfFraction/bottomOfFraction; // get prediction
			
			// if prediction greater than MAX_RATING or less than MIN_RATING, set prediction to respective limit
			if	(prediction > Pearson.MAX_RATING){
				prediction = Pearson.MAX_RATING;
			} else if (prediction < Pearson.MIN_RATING){
				prediction = Pearson.MIN_RATING;
			}
			
			return prediction; // return prediction
		} else {
			return -1;
		}
	}


	/**
	 * Computes the set of neighbours that will be used in the prediction of a movie rating for a given user.
	 * Note that its suggested that you implement this method but not necessary
	 * @param profile	The profile for which the neighbourhood will be found
	 * @param simThreshold the maximum dissimilarity threshold for the neigbours
	 * @return the set of neighbours that are most similar to the given profile
	 */
	protected Set<Profile> computeNeighbours(final Profile profile, final double simThreshold){
		Set<Profile> neighbours = new HashSet<Profile>(); // declare new set to store neighbours
		
		for(Profile tempProfile : profileSet){	// compare every profile
			if(getPearson(profile, tempProfile) > simThreshold && profile.getUserId() != tempProfile.getUserId())	// if similarity is greater than simThreshold
				neighbours.add(tempProfile);	// add to neighbours set
		}
		
		return neighbours; // return set of neighbours
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////
	//		DO NOT EDIT BELOW THIS LINE UNLESS YOU KNOW WHAT YOU ARE DOING!
	/////////////////////////////////////////////////////////////////////////////////////////
	
	private static double MIN_RATING = 1;
	private static double MAX_RATING = 5;
	private double[][] simMatrix = null;//hold all the computed Pearson values
	private Set<Profile> profileSet;

	private void setup(final Set<Profile> profiles){
		simMatrix = new double[profiles.size()][profiles.size()];
		this.profileSet = profiles;
		computeAllSimilarity(profiles);
	}

	private void computeAllSimilarity(final Set<Profile> set){
		for (Profile a: set){
			for (Profile b: set) {
				setSim(a, b, computeSimilarity(a, b));
			}
		}
	}

	/**
	 * Stores the Pearson correlation coefficent (similary value) in memory between 2 profiles
	 * @param a First profile
	 * @param b Second profile
	 * @param the similarity between to 2.
	 * 
	 * Note - do not edit this method or your code WILL fail.
	 */
	// Had to modify this code to allow me to open multiple files and create predictions without getting OutOfBOunds exceptions
	private void setSim(final Profile a, final Profile b, final double value) {
		simMatrix[Profile.INSTANCE_COUNT - a.internalID() -1][Profile.INSTANCE_COUNT - b.internalID() -1] = value;
		simMatrix[Profile.INSTANCE_COUNT - b.internalID() -1][Profile.INSTANCE_COUNT - a.internalID() -1] = value;
	}

	/**
	 * Retrieves the previously computed Pearson value between 2 profiles from memory
	 * @param First profile
	 * @param Second profile
	 * @return the MSD value for the 2 profiles
	 * 
	 * Note - do not edit this method or your code WILL fail.
	 */
	// Had to modify this code to allow me to open multiple files and create predictions without getting OutOfBOunds exceptions
	private double getPearson(final Profile profile, final Profile candidate) {
		return simMatrix[Profile.INSTANCE_COUNT - profile.internalID()-1][Profile.INSTANCE_COUNT - candidate.internalID() -1];
	}

	/**
	 * @return Returns the set of profiles that the similarity metric is working on.
	 */
	public Set<Profile> getProfileSet() {
		return profileSet;
	}

	/**
	 * Computes the average rating given by a user for a set of given movies
	 * Note that its suggested that you implement this method but not necessary
	 * @param profile	The profile in question
	 * @param commonMovies The set of movies for which ratings were given
	 * @return the average rating given by user p for the set of movies
	 */
	protected double calcAverageRating(final Profile profile, final Set<Movie> commonMovies) {
		double total = 0;
		for (Movie movie: commonMovies) {
			total = total + profile.getRatingFor(movie);
		}
		return total / commonMovies.size();
	}
}
