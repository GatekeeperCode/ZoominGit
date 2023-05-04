package edu.gcc.comp350.zoomin;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class AISuggestion {
	private ArrayList<Integer> ClassCodes = new ArrayList<Integer>();
	private ArrayList<String> DeptCodes = new ArrayList<String>();
	private ArrayList<String> TimesAvoid = new ArrayList<String>();
	//private ArrayList<Course> RecCourses = new ArrayList<Course>();
	private ArrayList<String> timesCant = new ArrayList<String>();
	private String schedName;
	private int semestYear;
	private String semestSession;
	int schedCredits;
	MongoCollection collection;

	AISuggestion(String name, String semester, MongoCollection c)
	{
		schedName = name;
		Scanner scnr = new Scanner(semester);
		scnr.useDelimiter(",");
		semestSession = scnr.next();
		semestYear = scnr.nextInt();

		collection = c;
		timesCant.add("Blank"); //This is so I can reference timesCant later without a null call
		schedCredits = 0;
	}

	/**
	 * Main Workhorse of the AI
	 * @return The Schedule Generated
	 */
	public Schedule generateSchedule() {
		Scanner scnr = new Scanner(System.in);

		//Reference Code from Search
//		ArrayList<Document> docList = new ArrayList<>();
//		FindIterable<Document> results = collection.find(filterAnd);
//		results.forEach(doc -> newResults.add(new Course(doc)));

		//Gathering user input
		gatherCourseCodes();

		System.out.println("Are there any times that you do not wish to have classes in? (Y/N)");

		String response = scnr.next();
		if(response.contains("Y") || response.contains("y"))
		{
			filterTime();
		}

		//AI Creating Schedule
		Schedule AISched = new Schedule(schedName, semestSession + semestYear);

		for(int i=0; i<ClassCodes.size(); i++)
		{
			AISched = addCourse(AISched, i);
		}

		while(schedCredits<12)
		{
			addExtraCourse(AISched);
		}


		return AISched;
	}

	Schedule addCourse(Schedule sched, int index)
	{
		Schedule hold = sched;
		Boolean courseAdded = false;
		int loopIteration = 1;

		Bson deptFilter = Filters.eq("coursePrefix", DeptCodes.get(index));
		Bson codeFilter = Filters.eq("courseNumber", ClassCodes.get(index));
//		Bson yearFilter = Filters.eq("year", semestYear);
		Bson semestFilter = Filters.eq("semester", semestSession);
		Bson mainFilter = Filters.and(deptFilter, codeFilter, semestFilter);

		if(timesCant.size()>1)
		{
			for(int i=1; i<timesCant.size(); i++)
			{
				System.out.println(timesCant.get(i));
				Bson timeFilter = Filters.regex("startTime", timesCant.get(i));
				timeFilter = Filters.not(timeFilter);
				mainFilter = Filters.and(mainFilter, timeFilter);
			}
		}

		ArrayList<Course> firstList = new ArrayList<>();
		FindIterable<Document> results = collection.find(mainFilter);
		results.forEach(doc -> firstList.add(new Course(doc)));
		System.out.println("FirstList size: " + firstList.size());

		if(firstList.size() < 1)
		{
			System.out.println("Nothing Found");
			courseAdded = true;
		}

		while(!courseAdded)
		{
			for(int i=TimesAvoid.size()-loopIteration; i>=0; i--)
			{
				Bson timeFilter = Filters.regex("startTime", TimesAvoid.get(i));
				timeFilter = Filters.not(timeFilter);
				mainFilter = Filters.and(mainFilter, timeFilter);
			}

			if(loopIteration>TimesAvoid.size() && TimesAvoid.size()>0)
			{
				break;
			}

			ArrayList<Course> secondList = new ArrayList<>();
			FindIterable<Document> results2 = collection.find(mainFilter);
			results2.forEach(doc -> secondList.add(new Course(doc)));
			System.out.println("SecondList size: " + secondList.size());

			if(secondList.size()>0)
			{
				hold.addClass(secondList.get(0));
				Scanner scnr = new Scanner(secondList.get(0).getTime());
				scnr.useDelimiter(":00-");
				schedCredits += secondList.get(0).getCredits();
				hold.setTotalCredits(schedCredits);

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

	Schedule addExtraCourse(Schedule sched)
	{
		Schedule hold = sched;

		Bson humaFilter = Filters.eq("coursePrefix", "HUMA");
		Bson codeFilter = Filters.ne("courseNumber", 102);
		Bson semestFilter = Filters.eq("semester", semestSession);
		Bson mainFilter = Filters.and(humaFilter, codeFilter, semestFilter);

		for(int i=0; i<timesCant.size(); i++)
		{
			Bson cantFilter = Filters.regex("startTime", timesCant.get(i));
			cantFilter = Filters.not(cantFilter);
			mainFilter = Filters.and(mainFilter, cantFilter);
		}

		for(int i=0; i<ClassCodes.size(); i++) //Making sure a duplicate class isn't given.
		{
			Bson avoidFilter = Filters.ne("courseNumber", ClassCodes.get(i));
			mainFilter = Filters.and(mainFilter, avoidFilter);
		}

		ArrayList<Course> humaList = new ArrayList<>();
		FindIterable<Document> results = collection.find(mainFilter);
		results.forEach(doc -> humaList.add(new Course(doc)));

		if(humaList.size()>0)
		{
			hold.addClass(humaList.get(0));
			schedCredits += humaList.get(0).getCredits();
			hold.setTotalCredits(schedCredits);

			Scanner intTranslator = new Scanner(humaList.get(0).getCourseCode());
			ClassCodes.add(intTranslator.nextInt());
			return hold;
		}


		Bson mainFilter2 = Filters.ne("courseNumber", ClassCodes.get(0));
		for(int i=1; i<ClassCodes.size(); i++) //Making sure a duplicate class isn't given.
		{
			Bson avoidFilter = Filters.ne("courseNumber", ClassCodes.get(i));
			mainFilter2 = Filters.and(mainFilter2, avoidFilter);
		}

		FindIterable<Document> results2 = collection.find(mainFilter2);
		results2.forEach(doc -> humaList.add(new Course(doc)));

		hold.addClass(humaList.get(0));
		schedCredits += humaList.get(0).getCredits();

		Scanner intTranslator = new Scanner(humaList.get(0).getCourseCode());
		ClassCodes.add(intTranslator.nextInt());
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
				if(scnr.hasNext())
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
							dept += Character.toUpperCase(courseCode.charAt(i));
						}

						DeptCodes.add(dept);

						if(courseCode.length()>4)
						{
							for(int i=4; i<courseCode.length(); i++)
							{
								code += courseCode.charAt(i);
							}
							Scanner intTranslator = new Scanner(code);
							ClassCodes.add(intTranslator.nextInt());
						}

						System.out.println("Dept: " + dept + " code: " + code);

						System.out.println("Do you wish to take any more classes? (Y/N)");

						if(scnr.next().equalsIgnoreCase("N"))
						{
							loopVar = false;
						}
						else
						{
							System.out.println("What courses do you wish to take? Please input course code. (Ex. COMP141)");
							System.out.println("Input class codes in order of importance.");
						}

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

	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
}
