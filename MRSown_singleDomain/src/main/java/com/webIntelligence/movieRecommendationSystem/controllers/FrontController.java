package com.webIntelligence.movieRecommendationSystem.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webIntelligence.movieRecommendationSystem.database.LoginDao;
import com.webIntelligence.movieRecommendationSystem.model.Movie;
import com.webIntelligence.movieRecommendationSystem.model.RandomRating;
import com.webIntelligence.movieRecommendationSystem.model.LoginForm;
import com.webIntelligence.movieRecommendationSystem.model.RegisterForm;
import com.webIntelligence.movieRecommendationSystem.model.Rating;


//import com.webIntelligence.movieRecommendationSystem.model.LoginForm;

@Controller
public class FrontController 
{
	LoginDao loginDao;
	//to get login form page
	String username = "i";
	String randMoviename= "a";
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginForm() 
	{
		//return html page name
		return "login";	
	}
	
			
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute(name="loginForm") LoginForm loginForm , Model model) throws ClassNotFoundException
	{
			loginDao = new LoginDao();
			String email = loginForm.getEmail();
			String password=loginForm.getPassword();
			username =  email;
			if (loginDao.authenticateUser(email, password))		
			{
				return"home";
			}
			//if email or password is wrong 
			model.addAttribute("ivalidCredentials",true);
			return"login";		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegisterForm() 
	{
		return "register";	
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registration(@ModelAttribute(name="registerForm") RegisterForm registerform , Model model) throws ClassNotFoundException
	{
	loginDao = new LoginDao();
	
	String FirstName = registerform.getFirstName();
	String LastName = registerform.getLastName();
	String UserName = registerform.getUserName();
	String Gender = registerform.getGender();
	String Email = registerform.getEmail();
	String Password=registerform.getPassword();
	
	System.out.println(LastName);
	
	if (loginDao.insertIntoTbl(FirstName, LastName, Gender, UserName, Email, Password))		
	{
		return"login";
	}

	model.addAttribute("ivalidCredentials",true);
	return"register";		
	
	}
	
	
	@RequestMapping(value="/addRatings", method=RequestMethod.GET)
	public String getAddRatingsForm() 
	{
		return "addRatings";	
	}	
	
	@RequestMapping(value="/addMovie",method=RequestMethod.POST)
	public String addMovie(@ModelAttribute(name="addMovieForm") Movie movie , Model model) throws ClassNotFoundException
	{
	loginDao = new LoginDao();
	
	String movieName = movie.getMovieName();
	String MID       = movie.getMID();
	String year      = movie.getYear();
	
	
	
	System.out.println(MID);
	
	if (loginDao.insertIntoTbl2(movieName, MID, year))		
	{
		return"dataInserted";
	}

	model.addAttribute("ivalidCredentials",true);
	return"addMovie";		
	
	}
	
	@RequestMapping(value="/addRatings",method=RequestMethod.POST)
	public String addRatings(@ModelAttribute(name="addRatingForm") Rating addRating , Model model) throws ClassNotFoundException
	{
	loginDao = new LoginDao();
	String movieName= addRating.getMovieName();
	
	int rating = addRating.getRating();
		
	System.out.println(rating);
	
	if (loginDao.insertIntoTbl3(movieName,username, rating))		
	{
		return"dataInserted";
	}

	model.addAttribute("ivalidCredentials",true);
	return"addRatings";		
	
	}
		
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String getHomePage() 
	{
		//return html page name```````````````````````````````````````````								
		return "home";	
	}
	
	@RequestMapping(value="/addMovie", method=RequestMethod.GET)
	public String getAddMoviePage() 
	{					
		return "addMovie";	
	}	

	@RequestMapping(value="/addRandomRating", method=RequestMethod.GET)
	public String getAddRandomRatingPage(Model model) throws ClassNotFoundException 
	{					
		loginDao = new LoginDao();
		String movieName = loginDao.getRandMovieName();
		model.addAttribute("movieName", movieName);
		randMoviename = movieName;
		return "addRandomRating";	
	}
	
	@RequestMapping(value="/addRandomRatings",method=RequestMethod.POST)
	public String addRandomRatings(@ModelAttribute(name="addRandomRatingForm") RandomRating addRandomRating , Model model) throws ClassNotFoundException
	{
	loginDao = new LoginDao();	
	int rating = addRandomRating. getRating();
		
	//System.out.println(rating);
	
	if (loginDao.insertIntoTbl3(randMoviename,username, rating))		
	{
		return"dataInserted";
	}

	return"home";		
	
	}



}