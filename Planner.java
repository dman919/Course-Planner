/**
 * David Man 111940002 RO3
 * The Planner Class the holds all the Course objects in an array
 */

public class Planner implements Cloneable {
	//An array that holds course objects
	Course[] courses;
	//A constant that creates the size of the course
	static final int MAX_COURSES = 50;
	//A variable that keeps track of the number of courses
	int numCourses;
	
	public Planner() {
		this.courses = new Course[MAX_COURSES];
		numCourses = 0;
	}
	
	/**
	 * Size method to return size of planner currently
	 * @return
	 *   Returns the number of courses in the planner
	 */
	public int size() {
		return numCourses;
	}
	
	/**
	 * AddCourse method for adding course to position in planner
	 * @param newCourse
	 *   Course object for new course being added
	 * @param position
	 *   Integer value for position of the new course in the planner
	 * @exception FullPlannerException
	 *   Indicates if planner is full/if size of planner is 50
	 * @exception IllegalArgumentException 
	 *   Indicates if position value is below 1 or above 50
	 */
	public void addCourse(Course newCourse, int position) 
	  throws IllegalArgumentException, FullPlannerException {
		if (position < 1 || position > MAX_COURSES) {
			throw new IllegalArgumentException
			  ("Invalid Position, enter a position between 1 and 50");
		}
		else if (position > numCourses + 1) {
			throw new IllegalArgumentException
			  ("Enter a position between 1 and " + (numCourses + 1));
		}
		else if (fullPlanner()) {
			throw new FullPlannerException
			  ("Planner is Full");
		}
		if (courses[position - 1] == null) {
			courses[position - 1] = newCourse;
			numCourses++;
		}
		else {
			for (int i = numCourses; i >= position - 1; i--) {
				courses[i + 1] = courses[i];
			}
			courses[position - 1] = newCourse;
			numCourses++;
		}
		
		System.out.println(String.format(
		  "\n%s %s.%02d successfully added to planner.\n", 
		  newCourse.getDepartment(), 
		  newCourse.getCode(), 
		  newCourse.getSection()));
	}
	/**
	 * FullPlanner method for checking if planner is full
	 * @return
	 *   boolean is true is planner is full and false if isn't full
	 */
	public boolean fullPlanner() {
		int temp = 0;
		for (int i = 0; i < numCourses; i++) {
			if (courses[i] != null) {
				temp++;
			}
		}
		
		if (temp == MAX_COURSES) {
			return true;
		}
		return false;
	}
	
	/**
	 * AddCourse method for adding course to end of planner
	 * @param newCourse
	 *   Course object for new course being added
	 * @exception FullPlannerException
	 *   Indicates if planner is full/if size of planner is 50
	 * @exception IllegalArgumentException 
	 *   Indicates if position value is below 1 or above 50
	 */
	public void addCourse(Course newCourse) 
	  throws IllegalArgumentException, FullPlannerException {
		addCourse(newCourse, size());
	}
	
	/**
	 * RemoveCourse method for removing course at position in planner
	 * @param position
	 *   Integer value for position of course being removed from planner
	 * @exception IllegalArgumentException
	 *   Indicates if position value is below 1 or above number of courses
	 */
	public void removeCourse(int position)
	  throws IllegalArgumentException {
		Course temp = new Course();
		if (position < 1 || position > numCourses) {
			throw new IllegalArgumentException
			  ("Invalid Position, Try Again");
		}
		else {
			temp = courses[position - 1];
			for (int i = position - 1; i < numCourses; i++) {
				courses[i] = courses[i + 1];
			}
			numCourses--;
		}
		
		System.out.println(String.format(
		  "%s %s.%02d has been successfully removed from the planner.\n",
		  temp.getDepartment(), 
		  temp.getCode(),
		  temp.getSection()));
	}
	
	/**
	 * GetCourse method for getting course from position in planner
	 * @param position
	 *   Integer value for position of course in planner
	 * @return
	 *   Returns course object of position in planner
	 * @exception IllegalArgumentException
	 *   Indicates if position value is below 1 or above number of courses
	 */
	public Course getCourse(int position) 
	  throws IllegalArgumentException {
		if (position < 1 || position > numCourses) {
			throw new IllegalArgumentException
			  ("Invalid Position, Try Again");
		}
		else {
			System.out.println("\nNo. Course Name               "
			  + "Department Code Section Instructor\n"
			  + "-------------------------------------"
			  + "------------------------------------------\n" 
			  + (String.format("%3s %-25s %-10s %4s      %02d %-25s\n",
				  position, 
				  courses[position - 1].getName(), 
				  courses[position - 1].getDepartment(), 
				  courses[position - 1].getCode(), 
				  courses[position - 1].getSection(), 
				  courses[position - 1].getInstructor())));	
		}

		return courses[position - 1];
	}
	
	/**
	 * Filter method for getting courses based on department
	 * @param planner
	 *   Planner object for all courses being filtered
	 * @param department
	 *   String for specific department of course filter
	 */
	public static void filter(Planner planner, String department) {		
		System.out.print("\nNo. Course Name               "
		  + "Department Code Section Instructor\n"
		  + "-------------------------------------"
		  + "------------------------------------------\n");
		
		for (int i = 0; i < planner.numCourses; i++) {
			if (planner.courses[i].getDepartment().equals(department)) {
				System.out.print(String.format(
				  "%3s %-25s %-10s %4s      %02d %-25s\n", (i + 1), 
				  planner.courses[i].getName(), 
				  planner.courses[i].getDepartment(), 
				  planner.courses[i].getCode(), 
				  planner.courses[i].getSection(), 
				  planner.courses[i].getInstructor()));
			}
		}
		System.out.println();
	}
	
	/**
	 * Exists method for verifying if course exists in planner
	 * @param course
	 *   Course object to check if specified object exists in planner
	 * @return
	 *   Returns true if course is found, false if course is not found
	 */
	public boolean exists(Course course) {
		for (int i = 0; i < numCourses; i++) {
			if (courses[i].equals(course)) {
				return true;
			}
		}
		
		return false;
	}
	/**
	 * Location method for getting position of existing course
	 * @param course
	 *   Course object for finding course in planner
	 * @return
	 *   Returns integer value of position of course in planner
	 */
	public int location(Course course) {
		int l = 0;
		for (int i = 0; i < numCourses; i++) {
			if (courses[i].equals(course)) {
				l = i;
			}
		}
		
		return l + 1;
	}
	
	/**
	 * Clone method for creating clone of planner
	 * @return
	 *   Returns copy of planner as a planner object
	 * @exception CloneNotSupportedException
	 *   Indicates if clone of planner object is not supported
	 */
	public Object clone() throws CloneNotSupportedException {
		Planner copy = new Planner();
		copy.numCourses = numCourses;
		for (int i = 0; i < size(); i++) {
			copy.courses[i] = (Course) courses[i].clone();
			
		}
		
		System.out.println("Created a backup of the current planner.\n");

		return copy;
	}

	/**
	 * Print method for printing all courses
	 */
	public void printAllCourses() {
		System.out.println(toString());
	}

	/**
	 * ToString method for creating formatted table of courses
	 */
	public String toString() {
		String s = "No. Course Name               "
		+ "Department Code Section Instructor\n"
		+ "-------------------------------------"
		+ "------------------------------------------\n";
		for (int i = 1; i <= numCourses; i++) {
			if (courses[i - 1] != null) {
				s += (String.format(
				  "%3s %-25s %-10s %4s      %02d %-25s\n", i, 
				  courses[i - 1].getName(), 
				  courses[i - 1].getDepartment(), 
				  courses[i - 1].getCode(),
				  courses[i - 1].getSection(), 
				  courses[i - 1].getInstructor()));
			}
		}
		
		return s;
	}
}

class FullPlannerException extends Exception {
	public FullPlannerException(String e) {
		super(e);
	}
}