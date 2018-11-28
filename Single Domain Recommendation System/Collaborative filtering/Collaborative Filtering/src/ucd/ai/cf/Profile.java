package ucd.ai.cf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class contains all the ratings that a user has made on movies
 */
public class Profile {

	/**YOU SHOULD IMPLEMENT THIS METHOD
	 * Returns the a set of movies that are common between the two profiles.
	 * @param other The other profile to compare
	 * @return a set of Movie objects
	 */
	public Set<Movie> getCommonMovies(Profile other) {
		Set<Movie> thisSet = new HashSet<Movie>(this.getMovieList()); // set of all the movies in profile
		Set<Movie> commonSet = new HashSet<Movie>(); // new set to return common movies
		
		for(Iterator<Movie> it = thisSet.iterator(); it.hasNext();){ //iterate through  list of movies
			Movie movie = it.next();	// next movie in set
			
			if (this.hasRated(movie) && other.hasRated(movie)) //if both profiles have rated the movie, add to common set
				commonSet.add(movie);
		}

		return commonSet; // return common set
	}

	/**
	 * This returns the set of Rating objects for this user
	 * @return
	 */
	public Set<Rating> getRatings() {
		//return new HashSet<Rating>(allRatingsMap.values());
		
		Set<Movie> movies = allRatingsMap.keySet();
		Set<Rating> ratings = new LinkedHashSet<Rating>();
		for (Movie m: movies)
			ratings.add(allRatingsMap.get(m));
		
		return ratings;
	}

	/**
	 * Returns the unique numeric id for this user
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Returns the number of ratings in this profile
	 * @return
	 */
	public int size(){
		return allRatingsMap.size();
	}

	/** Returns the Rating that this user has given for given movie
	 * 
	 * @param movie The movie for which the rating is sought
	 * @return the rating value for the movie
	 */
	public double getRatingFor(final Movie movie) {
		Rating rating = allRatingsMap.get(movie);
		if(rating == null){
			return 0;
		}
		return rating.getRating();
	}


	/**
	 * Returns a boolean indicating whether this user has rated a particular move
	 * This method is left for the student to implement - its only a suggestion
	 * @param movie The movie in question
	 * @return true if the user has rated it, false otherwise
	 */
	public boolean hasRated(final Movie movie) {
		return allRatingsMap.get(movie) != null;
	}

	/**
	 * Returns the average rating that the user has made across all the movies in the profile
	 * @return the mean rating over all the movies
	 */
	public double getMeanRating() {
		double total = 0;
		for (Rating r: allRatingsMap.values()){
			total = total + r.getRating();
		}
		return (size() > 0) ? total /  size() : -1;
	}

	
	/**
	 * Returns the standard deviation of ratings that the user has made across all the movies in the profile
	 * @return the mean rating over all the movies
	 */
	public double getStdevRating() {
		double mean = getMeanRating();
		double sqErr = 0;
		for (Rating r: allRatingsMap.values()){
			sqErr += + Math.pow(mean - r.getRating(), 2);
		}
		return (size() > 1) ? Math.sqrt(sqErr) / (size() - 1) : 0;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	//		DO NOT EDIT BELOW THIS LINE UNLESS YOU KNOW WHAT YOU ARE DOING!
	/////////////////////////////////////////////////////////////////////////////////////////
	private Integer userId;
	private Map<Movie, Rating> allRatingsMap;//the actual ratings given
	private Map<Movie, Rating> targetRatingsMap;//the ratings to predict

	public static int INSTANCE_COUNT = 0;
	private int internalID;

	public Profile(final Integer uid){
		this.userId = uid;
		allRatingsMap = new LinkedHashMap<Movie, Rating>();
		targetRatingsMap = new LinkedHashMap<Movie, Rating>();
		internalID = INSTANCE_COUNT;
		INSTANCE_COUNT++;
	}

	protected int internalID(){
		return this.internalID;
	}

	/**
	 * split the profile into training and test sets.
	 * The test set (targetRatingsMap) are removed from the profile
	 * and we try to predict their ratings based on the training
	 * set which is the remaining part of the profile (allRatingsMap)
	 * @param targetPercentage
	 */
	protected void split(final double targetPercentage) {
		int targetSize = (int)(allRatingsMap.size() * targetPercentage);
		targetRatingsMap = new LinkedHashMap<Movie, Rating>(targetSize);
		int count = 0;
		
		Set<Rating> ratings = getRatings();
		for(Iterator<Rating> it = ratings.iterator(); it.hasNext() && (count < targetSize);){
			Rating r = (Rating)it.next();
			targetRatingsMap.put(r.getMovie(), r);
			allRatingsMap.remove(r.getMovie());
			count++;
		}
	}

	public List<Movie> getTargetMovieList(){
		List<Movie> l = new ArrayList<Movie>(targetRatingsMap.size());
		for(Rating rating: targetRatingsMap.values()) {
			l.add(rating.getMovie());
		}
		return l;
	}
	
	public List<Movie> getMovieList(){
		List<Movie> l = new ArrayList<Movie>(allRatingsMap.size());
		for(Rating rating: allRatingsMap.values()) {
			l.add(rating.getMovie());
		}
		return l;
	}

	/**
	 * get the rating for the movie
	 * @param movie
	 * @return
	 */
	public double getTargetRating(final Movie movie) {
		Rating rating = targetRatingsMap.get(movie);
		return rating.getRating();
	}

	protected void addRating(final Rating rating){
		allRatingsMap.put(rating.getMovie(), rating);
	}

}
