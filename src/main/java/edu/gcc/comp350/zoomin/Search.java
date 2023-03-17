package edu.gcc.comp350.zoomin;
import java.util.ArrayList;

public class Search {
	private Filter filter = new Filter();
	private ArrayList<Course> SearchResults = new ArrayList<Course>();
	
	
	public ArrayList<Course> search() {
		return null;
	}
	
	public void addFilters(Filter newFilter) {
		
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
