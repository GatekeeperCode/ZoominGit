package edu.gcc.comp350.zoomin;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class AISuggestion {
	private ArrayList<String> ClassCodes = new ArrayList<String>();
	private ArrayList<String> DeptCodes = new ArrayList<String>();
	private ArrayList<String> TimesAvoid = new ArrayList<String>();
	//private ArrayList<Course> RecCourses = new ArrayList<Course>();
	private ArrayList<String> timesCant = new ArrayList<String>();
	private String schedName;
	private String semest;
	MongoCollection collection;

	AISuggestion(String name, String semester, MongoCollection c)
	{
		schedName = name;
		semest = semester;
		collection = c;
		timesCant.add("Blank"); //This is so I can reference timesCant later without a null call
	}

	
	public Schedule generateSchedule() {
		Scanner scnr = new Scanner(System.in);

		//Reference Code from Search
//		Bson filterThree = Filters.regex("coursePrefix", filter.getDepartment(), "i");
//		Bson filterFour = Filters.regex("courseNumber", filter.getCourseCode(), "i");
//		ArrayList<Document> docList = new ArrayList<>();
//		FindIterable<Document> results = collection.find(filterAnd);
//		results.forEach(doc -> newResults.add(new Course(doc)));

		//Gathering user input
		gatherCourseCodes();

		System.out.println("Are there any times that you do not wish to have classes in? (Y/N)");

		if(scnr.next().contains("Y") || scnr.next().contains("y"))
		{
			filterTime();
		}

		//AI Creating Schedule
		Schedule AISched = new Schedule(schedName, semest);

		for(int i=0; i<ClassCodes.size(); i++)
		{
			AISched = addCourse(AISched, i);
		}


		return AISched;
	}

	Schedule addCourse(Schedule sched, int index)
	{
		Schedule hold = sched;
		Boolean courseAdded = false;
		int loopIteration = 1;

		Bson deptFilter = Filters.regex("coursePrefix", DeptCodes.get(index), "i");
		Bson codeFilter = Filters.regex("courseNumber", ClassCodes.get(index), "i");
		Bson mainFilter = Filters.and(deptFilter, codeFilter);

		if(timesCant.size()>1)
		{
			for(int i=1; i<timesCant.size(); i++)
			{
				Bson timeFilter = Filters.regex("startTime", timesCant.get(i), "i");
				mainFilter = Filters.and(mainFilter, Filters.not(timeFilter));
			}
		}

		ArrayList<Course> firstList = new ArrayList<>();
		FindIterable<Document> results = collection.find(mainFilter);
		results.forEach(doc -> firstList.add(new Course(doc)));

		if(firstList.size() < 1)
		{
			courseAdded = true;
		}

		while(!courseAdded)
		{
			for(int i=TimesAvoid.size()-loopIteration; i>=0; i--)
			{
				Bson timeFilter = Filters.regex("startTime", TimesAvoid.get(i), "i");
				timeFilter = Filters.not(timeFilter);
				mainFilter = Filters.and(mainFilter, timeFilter);
			}

			if(loopIteration>TimesAvoid.size())
			{
				break;
			}

			ArrayList<Course> secondList = new ArrayList<>();
			FindIterable<Document> results2 = collection.find(mainFilter);
			results2.forEach(doc -> secondList.add(new Course(doc)));

			if(secondList.size()>0)
			{
				hold.addClass(secondList.get(0));
				Scanner scnr = new Scanner(secondList.get(0).getTime());
				scnr.useDelimiter("-");

				timesCant.add(scnr.next());
				scnr.close();
				return hold;
			}
			else
			{
				loopIteration++;
			}
		}

		System.out.println("Class " + DeptCodes.get(index) + ClassCodes.get(index) + " could not be added because of time conflicts with higher priority class or because the class doesn't exits.");
		return hold;
	}

	/**
	 * Gets the course codes that the user wants to take.
	 */
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

	/**
	 * Gets the times that the user would like to avoid
	 */
	public void filterTime()
	{
		Scanner scnr = new Scanner(System.in);
		Boolean loopVar = true;

		System.out.println("What times are you trying to avoid?");
		System.out.println("Please input the start time of the class. (Ex. 8:00)");
		System.out.println("Any times put in are only suggestions, if forced, a class at this time will be taken.");

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

//	public ArrayList<Course> getRecCourses() {
//		return RecCourses;
//	}
//
//	public void setRecCourses(ArrayList<Course> recCourses) {
//		RecCourses = recCourses;
//	}

	public ArrayList<String> getDeptCodes() {return DeptCodes;}

	public void setDeptCodes(ArrayList<String> deptCodes) {DeptCodes = deptCodes;}
}
