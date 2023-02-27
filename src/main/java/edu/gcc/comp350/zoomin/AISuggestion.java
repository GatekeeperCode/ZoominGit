package edu.gcc.comp350.zoomin;

import java.util.ArrayList;

public class AISuggestion {
	private ArrayList<String> ClassCodes = new ArrayList<String>();
	private ArrayList<String> TimesAvoid = new ArrayList<String>();
	private ArrayList<Course> RecCourses = new ArrayList<Course>();
	
	public ArrayList<Course> generateSchedule() {
		return null;
	}
	
	public ArrayList<Course> filterTime() {
		return null;
	}

	public ArrayList<String> getClassCodes() {
		return ClassCodes;
	}

	public void setClassCodes(ArrayList<String> classCodes) {
		ClassCodes = classCodes;
	}

	public ArrayList<String> getTimesAvoid() {
		return TimesAvoid;
	}

	public void setTimesAvoid(ArrayList<String> timesAvoid) {
		TimesAvoid = timesAvoid;
	}

	public ArrayList<Course> getRecCourses() {
		return RecCourses;
	}

	public void setRecCourses(ArrayList<Course> recCourses) {
		RecCourses = recCourses;
	}
}
