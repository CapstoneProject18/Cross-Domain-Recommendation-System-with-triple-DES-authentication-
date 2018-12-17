package com.webIntelligence.movieRecommendationSystem.model;

public class Movie {

	
		private String movieName;
		private String MID;
		private String year;
		
		public Movie(String movieName, String mID, String year) {
			super();
			this.movieName = movieName;
			MID = mID;
			this.year = year;
		}
		public String getMovieName() {
			return movieName;
		}
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}
		public String getMID() {
			return MID;
		}
		public void setMID(String MID) {
			this.MID = MID;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		
	
}
