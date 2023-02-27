package edu.gcc.comp350.zoomin;
import java.util.ArrayList;

public class Search {
	private ArrayList<Filter> FilterList = new ArrayList<Filter>();
	private ArrayList<Course> CourseList = new ArrayList<Course>();
	
	
	public ArrayList<Course> search() {
		return null;
	}
	
	public void addFilters(ArrayList<Filter> newFilters) {
		
	}
	
	public void addClass(Course newCourse) {
		
	}

	public ArrayList<Filter> getFilterList() {
		return FilterList;
	}

	public void setFilterList(ArrayList<Filter> filterList) {
		FilterList = filterList;
	}

	public ArrayList<Course> getCourseList() {
		return CourseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		CourseList = courseList;
	}
}
