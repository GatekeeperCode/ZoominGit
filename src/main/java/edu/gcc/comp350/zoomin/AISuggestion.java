package edu.gcc.comp350.zoomin;

import java.util.ArrayList;
import java.util.Scanner;

public class AISuggestion {
	private ArrayList<String> ClassCodes = new ArrayList<String>();
	private ArrayList<String> DeptCodes = new ArrayList<String>();
	private ArrayList<String> TimesAvoid = new ArrayList<String>();
	private ArrayList<Course> RecCourses = new ArrayList<Course>();
	
	public ArrayList<Course> generateSchedule() {
		return null;
	}

	private void gatherCourseCodes()
	{
		Scanner scnr = new Scanner(System.in);
		boolean loopVar = true;

		System.out.println("What courses do you wish to take? Please input course code. (Ex. COMP141)");
		System.out.println("Input class codes in order of importance.");

			while(loopVar)
			{
				String courseCode = scnr.next();
				String dept = "";
				String code = "";

				if(courseCode.length()<4)
				{
					System.out.println("Sorry, that is an invalid Course Code");
					System.out.println("Please input a valid course code. Ex. Comp141");
				}
				else
				{
					for(int i=0; i<4; i++)
					{
						dept += courseCode.charAt(i);
					}

					DeptCodes.add(dept);

					if(courseCode.length()>4)
					{
						for(int i=4; i<courseCode.length(); i++)
						{
							code += courseCode.charAt(i);
						}
						ClassCodes.add(code);
					}

					System.out.println("Do you wish to take any more classes? (Y/N)");

					if(scnr.next().equalsIgnoreCase("N"))
					{
						loopVar = false;
					}
				}
			}
	}
	
	public void filterTime()
	{
		Scanner scnr = new Scanner(System.in);
		Boolean loopVar = true;

		System.out.println("What times are you trying to avoid?");
		System.out.println("Please input the start time of the class. (Ex. 8:00)");

		while(loopVar)
		{
			String hold = scnr.next();

			if(!Character.isAlphabetic(hold.charAt(0)))
			{
				TimesAvoid.add(hold);

				System.out.println("Do you wish to add another time to avoid? (Y/N)");

				if(scnr.next().equalsIgnoreCase("N"))
				{
					loopVar = true;
				}
			}
			else
			{
				System.out.println("That was not a valid time.");
				System.out.println("Please input a time like you see on a digital clock. (Ex. 8:00)");
			}
		}
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

	public ArrayList<String> getDeptCodes() {return DeptCodes;}

	public void setDeptCodes(ArrayList<String> deptCodes) {DeptCodes = deptCodes;}
}
