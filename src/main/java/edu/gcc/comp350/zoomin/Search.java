package edu.gcc.comp350.zoomin;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {
	private Filter filter;
	private ArrayList<Course> SearchResults = new ArrayList<Course>();


	/**
	 * Constructs the filter and the possible search results.
	 * @param filePath The filepath of where the CSV file is located.
	 */
	public Search(String filePath)
	{
		filter = new Filter();
		try {
			SearchResults = Driver.readInFile(filePath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Working in console, will update once GUI and noSQL are implemented
	 *
	 * if inputSearch is ''(nothing), i am unable to know whether this method will work properly or not.
	 * it might just work out and return all the courses in the department,
	 * or it will return nothing because theoretically none of the names contain ''?
	 *
	 * in that second case we just overload the constructer and copy paste relevant code
	 */
	public ArrayList<Course> search(String inputSearch, String courseCode, String timeTarget) {

		ArrayList<Course> AlteredSearchResults = new ArrayList<Course>();

		if(!inputSearch.equals(""))
		{
			filter.setCourseName(inputSearch);
		}

		if(!timeTarget.equals(""))
		{
			filter.setPrimaryTimeCheck(timeTarget);
		}

		if(!courseCode.equals(""))
		{
			String dept = "";
			String code = "";

			if(courseCode.length()<4)
			{
				System.out.println("Sorry, that is an invalid Course Code");

			}
			else
			{
				for(int i=0; i<4; i++)
				{
					dept += courseCode.charAt(i);
				}

				filter.setDepartment(dept);
			}

			if(courseCode.length()>4)
			{
				for(int i=4; i<courseCode.length(); i++)
				{
					code += courseCode.charAt(i);
				}

				filter.setCourseCode(code);
			}
		}

		for(int i=0; i<SearchResults.size(); i++)
		{
			if(filterMatch(SearchResults.get(i)))
			{
				AlteredSearchResults.add(SearchResults.get(i));
			}
		}

		return AlteredSearchResults;
	}

	/**Mike Buriok
	 * Compares a course to the filters.
	 * @param target The Class to be compared to the filters.
	 * @return True if target matches the filters, false otherwise.
	 */
	private boolean filterMatch(Course target)
	{
		boolean matchesFilters = true;


		if(!target.getCourseName().contains(filter.getCourseName()) && !filter.getCourseName().equals(""))
		{
			matchesFilters = false;
		}
		else if(!target.getProfessor().equalsIgnoreCase(filter.getProfessor()) && !filter.getProfessor().equals(""))
		{
			matchesFilters = false;
		}
		else if(target.getCredits()!=filter.getCreditHours() && filter.getCreditHours() != 0)
		{
			matchesFilters = false;
		}
		else if(!target.getDepartment().equals(filter.getDepartment()) && !filter.getDepartment().equals(""))
		{
			matchesFilters = false;
		}
		else if(!target.getCourseCode().equals(filter.getCourseCode()) && !filter.getCourseCode().equals(""))
		{
			matchesFilters = false;
		}
		else
		{
			//Time Schedule Checking
			if(filter.getPrimaryTimeCheck().equals(""))
			{
				if(!target.getTime().contains(filter.getStartTime()) && !filter.getStartTime().equals(""))
				{
					matchesFilters = false;
				}
				else if (!target.getTime().contains(filter.getEndTime()) && !filter.getEndTime().equals(""))
				{
					matchesFilters = false;
				}
			}
			else if(!target.getTime().contains(filter.getPrimaryTimeCheck()))
			{
				matchesFilters = false;
			}
		}

		return matchesFilters;
	}

	/**Mike Buriok
	 * Allows users to set the filters to compare the course list against.
	 * Working in console, will update once GUI and noSQL are implemented
	 */
	public void setFilters() {
		while(true)
		{
			System.out.println("What filter do you want to change? TIMESLOT, PROFESSOR, CREDITHOURS, DEPARTMENT, COURSECODE");
			System.out.println("If don't want to add that filter, just hit enter.");

			Scanner scnr = new Scanner(System.in);

			boolean correctInput = false;

			while(!correctInput)
			{
				String input = scnr.next();

				if(input.equalsIgnoreCase("Professor"))
				{
					System.out.println("What do you want to set the filter to?");
					filter.setProfessor(scnr.next());
					correctInput=true;
				}
				else if(input.equalsIgnoreCase("TimeSlot"))
				{
					System.out.println("What is the Start time that you want to search for?");
					filter.setStartTime(scnr.next());

					System.out.println("What is the End time that you want to search for?");
					filter.setEndTime(scnr.next());

					System.out.println("What days of the week are you checking for?");
					filter.setDaysOffered(scnr.next());
					correctInput=true;
				}
				else if(input.equalsIgnoreCase("CreditHours"))
				{
					System.out.println("What do you want to set the filter to?");
					filter.setCreditHours(scnr.nextInt());
//					scnr.next();
					correctInput=true;
				}
				else if(input.equalsIgnoreCase("Department"))
				{
					System.out.println("What do you want to set the filter to?");
					filter.setDepartment(scnr.next());
					correctInput=true;
				}
				else if(input.equalsIgnoreCase("CourseCode"))
				{
					System.out.println("What do you want to set the filter to?");
					filter.setCourseCode(scnr.next());
					correctInput=true;
				}
				else
				{
					System.out.println("The given input does not match the specifications.");
					System.out.println("What filter do you want to change? TIMESLOT, PROFESSOR, CREDITHOURS, DEPARTMENT, COURSECODE");
				}

				System.out.println("Would you like to add another filter (Y/N)");
				String loopContinue = scnr.next();

				if(loopContinue.equals("N"))
				{
					return;
				}
			}
		}
	}

	/**
	 * WE NEED TO MAKE SURE NO DUPE SCHEDULE NAMES ALLOWED
	 * @param newCourse
	 * @param sched
	 */
	public void addClass(Course newCourse, Schedule sched) {
		for (int i = 0; i < Driver.schedList.size(); i++){
			if(sched.getScheduleName().equals(Driver.schedList.get(i).getScheduleName())){
				sched.addClass(newCourse);
			}
		}
	}

	/**
	 * Compares the SearchResults to the Filters, and prints the results
	 * Works in console, When display changes, can be updated
	 */
	public void displayCourseSearch()
	{
		ArrayList<Course> results = new ArrayList<>();

		for(int i=0; i<SearchResults.size(); i++)
		{
			if(filterMatch(SearchResults.get(i)))
			{
				results.add(SearchResults.get(i));
			}
		}
		if (results.size() < 1){
		System.out.println("no results match criteria");
	}

		System.out.println("Course Code |||     Name     |||   Professor   |||  Days  |||   Time   ||| Credits");

		for(int i=0; i<results.size(); i++)
		{
			System.out.println(results.get(i).toString());
		}
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filterer) {
		filter = filterer;
	}

	public ArrayList<Course> getSearchResults() {
		return SearchResults;
	}

	public void setSearchResults(ArrayList<Course> searchresults) {
		SearchResults = searchresults;
	}
}
