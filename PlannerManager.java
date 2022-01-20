/**
 * David Man 111940002 RO3
 * The Planner Manager Class that manages the planner and courses
 */

import java.util.Scanner;

public class PlannerManager {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args)
		  throws CloneNotSupportedException, 
		    IllegalArgumentException, 
		    FullPlannerException {
		Planner planner = new Planner();
		Planner copy = new Planner();
		int i = 0;
		while (i == 0) {
			System.out.print("(A) Add Course\n" + 
							 "(G) Get Course\n" + 
							 "(R) Remove Course\n" + 
							 "(P) Print Courses in Planner\n" + 
							 "(F) Filter by Department Code\n" + 
							 "(L) Look For Course\n" + 
							 "(S) Size\n" + 
							 "(B) Backup\n" + 
							 "(PB) Print Courses in Backup\n" + 
							 "(RB) Revert to Backup\n" + 
							 "(Q) Quit\n\n" + 
							 "Enter a selection: ");
			String selection = input.next();
			System.out.println();
						
			//adds a course
			if (selection.equals("A") || selection.equals("a")) {
				Course newCourse = new Course();
				newCourse = newCourse.createCourse();
				
				System.out.print("Enter position: ");
				int position = input.nextInt();
				while (position < 1 || position > planner.numCourses + 1) {
					try {
						planner.addCourse(newCourse, position);
					}
					catch (Exception e) {
						System.out.println(e);
						System.out.print("Enter position: ");
						position = input.nextInt();
						System.out.println();
					}
				}
				
				planner.addCourse(newCourse, position);
			}
	
			//gets the courses
			if (selection.equals("G") || selection.equals("g")) {
				System.out.print("Enter position: ");
				int position = input.nextInt();
				while (position < 1 || position > planner.numCourses) {
					try {
						planner.getCourse(position);
					}
					catch (Exception e) {
						System.out.println(e);
						System.out.print("Enter position: ");
						position = input.nextInt();
						System.out.println();						
					}
				}
				
				planner.getCourse(position);
			}

			//removes the courses
			if (selection.equals("R") || selection.equals("r")) {
				System.out.print("Enter position: ");
				int position = input.nextInt();
				System.out.println();
				while (position < 1 || position > planner.numCourses) {
					try {
						planner.removeCourse(position);
					}
					catch (Exception e) {
						System.out.println(e);
						System.out.print("Enter position: ");
						position = input.nextInt();
					}
				}

				planner.removeCourse(position);
			}
			
			//prints all courses
			if (selection.equals("P") || selection.equals("p")) {
				System.out.print("Planner:\n");
				planner.printAllCourses();
			}
			
			//filters courses by department code
			if (selection.equals("F") || selection.equals("f")) {
				System.out.print("Enter department code: ");
				String dep = input.next();
				
				Planner.filter(planner, dep);
			}
			
			//looks for a course
			if (selection.equals("L") || selection.equals("l")) {
				Course newCourse = new Course();
				newCourse = newCourse.createCourse();
				
				boolean l = planner.exists(newCourse);
				if (l) {
					System.out.println(String.format(
					  "\n%s %s.%02d is found in the planner"
					    + " at position %s.\n",
					  newCourse.getDepartment(), 
					  newCourse.getCode(), 
					  newCourse.getSection(),
					  planner.location(newCourse)));
					
//					System.out.println("\n" + newCourse.getDepartment()
//					  + " " + newCourse.getCode() + "." + 
//					  newCourse.getSection() + 
//					  " is found in the planner at position " + 
//					  planner.location(newCourse) + ".\n");
				}
				else {
					System.out.println("\n" + newCourse.getDepartment()
					  + " " + newCourse.getCode() + "." + 
					  newCourse.getSection() + 
					  " is not found in the planner.\n");
				}
			}
	
			//gives the number of courses in planner
			if (selection.equals("S") || selection.equals("s")) {
				System.out.println("There are " + planner.size() +
								   " courses in the planner.\n");
			}
			
			//creates a backup of the planner
			if (selection.equals("B") || selection.equals("b")) {
				copy = (Planner) planner.clone();
			}
			
			//prints the backup of the planner
			if (selection.equals("PB") || selection.equals("pb")) {
				copy.printAllCourses();
			}

			//reverts the planner to the backup
			if (selection.equals("RB") || selection.equals("rb")) {					
				planner = copy;
				System.out.println
				  ("Planner successfully reverted to the backup copy.\n");
			}

			//ends the program
			if (selection.equals("Q") || selection.equals("q")) {
				System.out.print("Program terminating successfully...");
				System.exit(i);;
			}
		}
	}
}