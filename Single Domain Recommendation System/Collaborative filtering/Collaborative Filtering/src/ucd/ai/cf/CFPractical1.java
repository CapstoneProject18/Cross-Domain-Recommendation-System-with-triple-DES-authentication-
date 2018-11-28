package ucd.ai.cf;

import java.util.Set;

/**
 * Run this class to test your CF Practical 1 code - it will print out the MSD and the
 * Pearson values for each possible pair of users
 */

public class CFPractical1 {

	public static void main(String[] args) throws Exception {
		DatasetReader ld = new DatasetReader("MovieLens/u.item", "MovieLens/u.data_40");
		ld.loadAllProfiles();
		Set<Profile> profiles = ld.getProfiles();
		
		MeanSquaredDifference msd = new MeanSquaredDifference(profiles);
		Pearson pearson = new Pearson(profiles);

		for (Profile a: profiles) {
			for (Profile b: profiles) {
				if (msd.computeSimilarity(a,b) > 0 && Math.abs(pearson.computeSimilarity(a,b)) > 0)
					System.out.println(a.getUserId() + "\t" + b.getUserId() + "\tMSD=\t" + msd.computeSimilarity(a,b) + "\t\tPearson=\t" + pearson.computeSimilarity(a,b));
			}
		}
	}
}
