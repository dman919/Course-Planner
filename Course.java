/**
 * David Man 111940002 RO3
 * The Course class that creates the course for the planner
 */

import java.util.Scanner;

public class Course implements Cloneable {
	String name;
	String department;
	int code;
	byte section;
	String instructor;
		
	public Course() {
	}
	
	public Course(String name, String department, int code, 
	  byte section, String instructor) {
		this.name = name;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}
	/**
	 * Department mutator method
	 * @param department
	 *   String that gives the department of the course
	 * @throws IllegalArgumentException
	 *   Indicates if department is not 3 characters long
	 */
	public void setDepartment(String department)
	  throws IllegalArgumentException {
		if (department.length() != 3) {
			throw new IllegalArgumentException
			  ("Invalid Department, Try Again");
		}
		this.department = department;
	}

	public int getCode() {
		return code;
	}
	/**
	 * Code mutator method
	 * @param code
	 *   Integer value for the department code
	 * @throws IllegalArgumentException
	 *   Indicates if code does not have a value of at least 100
	 */
	public void setCode(int code) throws IllegalArgumentException {
		if (code < 0 || code < 100) {
			throw new IllegalArgumentException
			  ("Invalid Code, Try Again");
		}
		this.code = code;
	}

	public byte getSection() {
		return section;
	}
	/**
	 * Section mutator method
	 * @param section
	 *   Byte value for the section number
	 * @throws IllegalArgumentException
	 *   Indicates if section has a negative value
	 */
	public void setSection(byte section)
	  throws IllegalArgumentException {
		if (section < 0) {
			throw new IllegalArgumentException
			  ("Invalid Section, Try Again");
		}
		this.section = section;
	}

	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * @exception CloneNotSupportedException
	 *   Indicates if clone of planner object is not supported
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean equals(Object OBJ) {
		if (OBJ instanceof Course) {
			Course c = (Course) OBJ;
			return (c.name.equals(name)) && 
			  (c.department.equals(department)) && 
			  (c.code == code) && 
			  (c.section == section) && 
			  (c.instructor.equals(instructor));
		}
		else {
			return false;
		}
	}
	
	/**
	 * Create method for course object
	 * @return
	 *   Course object with new elements
	 */
	public Course createCourse() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		Course n = new Course();
		
		System.out.print("Enter course name: ");
		name = input.nextLine();
		setName(name);

		System.out.print("Enter department: ");
		department = input.nextLine();
		while (department.length() != 3) {
			try {
				setDepartment(department);
			}
			catch(Exception e) {
				System.out.println(e);
				System.out.print("Enter department: ");
				department = input.nextLine();
			}
		}
		
		System.out.print("Enter course code: ");
		code = input.nextInt();
		while (code < 0 || code < 100) {
			try {
				setCode(code);
			}
			catch(Exception e) {
				System.out.println(e);
				System.out.print("Enter course code: ");
				code = input.nextInt();
			}
		}

		System.out.print("Enter course section: ");
		section = (byte) input.nextInt();
		while(section < 0) {
			try {
				setSection(section);
			}
			catch(Exception e) {
				System.out.println(e);
				System.out.print("Enter course section: ");
				section = (byte) input.nextInt();
			}
		}
		
		System.out.print("Enter instructor: ");
		input.nextLine();
		instructor = input.nextLine();
		setInstructor(instructor);

		n = new Course(getName(), getDepartment(), getCode(), 
				  getSection(), getInstructor());
		return n;
	}
}