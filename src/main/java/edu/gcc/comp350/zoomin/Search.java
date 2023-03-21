package edu.gcc.comp350.zoomin;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {
	private Filter filter = new Filter();
	private ArrayList<Course> SearchResults = new ArrayList<Course>();

	/**
	 * Working in console, will update once GUI and noSQL are implemented
	 */
	public ArrayList<Course> search(String inputSearch) {
		ArrayList<Course> AlteredSearchResults = new ArrayList<Course>();

		for(int i=0; i<SearchResults.size(); i++)
		{
			if(filterMatch(SearchResults.get(i)))
			{
				AlteredSearchResults.add(SearchResults.get(i));
			}
		}

		return AlteredSearchResults;
	}

	private boolean filterMatch(Course target)
	{
		boolean matchesFilters = true;

		if(!target.professor.equalsIgnoreCase(filter.getProfessor()) && !filter.getProfessor().equals(""))
		{
			matchesFilters = false;
		}
		else if(!target.time.equalsIgnoreCase(filter.getTimeSlot()) && !filter.getTimeSlot().equals(""))
		{
			matchesFilters = false;
		}
		else if(target.credits!=filter.getCreditHours() && filter.getCreditHours() != 0)
		{
			matchesFilters = false;
		}
		else if(!target.department.equals(filter.getDepartment()) && !filter.getDepartment().equals(""))
		{
			matchesFilters = false;
		}
		else if(!target.courseCode.equals(filter.getCourseCode()) && !filter.getCourseCode().equals(""))
		{
			matchesFilters = false;
		}

		return matchesFilters;
	}

	/**
	 * Working in console, will update once GUI and noSQL are implemented
	 */
	public void setFilters() {
		System.out.println("What filter do you want to change? TIMESLOT, PROFESSOR, CREDITHOURS, DEPARTMENT, COURSECODE");
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
				System.out.println("What do you want to set the filter to?");
				filter.setTimeSlot(scnr.next());
				correctInput=true;
			}
			else if(input.equalsIgnoreCase("CreditHours"))
			{
				System.out.println("What do you want to set the filter to?");
				filter.setCreditHours(scnr.nextInt());
				scnr.next();
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
		}
	}
	
	public void addClass(Course newCourse, Schedule sched) {
		//WE NEED TO MAKE SURE
		//NO DUPE SCHEDULE NAMES ALLOWED
		for (int i = 0; i < Driver.schedList.size(); i++){
			if(sched.getScheduleName().equals(Driver.schedList.get(i).getScheduleName())){
				sched.addClass(newCourse);
			}
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
