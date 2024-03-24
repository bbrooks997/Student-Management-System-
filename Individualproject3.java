package individualproject3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//A set to track the IDs that have been used to ensure uniqueness
import java.util.HashSet;

//Random number generator for ID generation
import java.util.Random;

//Fields of the student class
class Student {
    private static HashSet<Integer> usedIDs = new HashSet<>();
    private static Random random = new Random();
    private int studentID;
    private String firstName;
    private String lastName;
    private String level;
    private boolean isActive;

    //Constructors for the Student class
    public Student(String firstName, String lastName, String level) {
        this.studentID = generateUniqueID(); // Assign a unique ID to the student
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.isActive = true;
    }

 // Method to generate a unique student ID
    private static int generateUniqueID() {
        int id;
        do {
            id = random.nextInt(100);  // Generate a number between 0 and 99
        } while (usedIDs.contains(id)); // Ensure the ID is not already used
        usedIDs.add(id); // Add the new ID to the set of used IDs
        return id; 
    }


    // Getters for the Student class 
    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLevel() {
        return level;
    }

    public boolean isActive() {
        return isActive;
    }

    // Setters for the Student class 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

 // Overridden toString method for the Student class
    @Override
    public String toString() {
        return firstName + " " + lastName +
               "\nID: " + studentID +
               "\nLevel: " + level +
               "\nStatus: " + (isActive ? "Active" : "Inactive") + "\n";
    }
}

public class Individualproject3 {
	
	// List to store the student objects
    private static List<Student> students = new ArrayList<>();
    
 // Scanner object for reading input from the console
    private static Scanner scanner = new Scanner(System.in);

    //Main method - entry point of the program
    public static void main(String[] args) {
        System.out.println("Welcome to Student Management System!");
        System.out.print("Enter the number of students this system will have: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        while (true) {
        	// Printing the main menu options
        	
            System.out.println("\n***Welcome to Student Management System***");
            System.out.println("Press '1' to add a student");
            System.out.println("Press '2' to deactivate a student");
            System.out.println("Press '3' to display all students");
            System.out.println("Press '4' to search for a student by ID");
            System.out.println("Press '0' to exit the system\n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the remaining newline

            // Switch-case to handle user's menu choice
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    deactivateStudent();
                    break;
                case 3:
                    displayStudents();
                    break;
                case 4:
                    findStudent();
                    break;
                case 0:
                    System.exit(0); // Exiting the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

 // Method to add a student to the list
    private static void addStudent() {
        System.out.print("\nEnter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter level of the student: ");
        String level = scanner.nextLine();

        Student newStudent = new Student(firstName, lastName, level);
        students.add(newStudent);
        System.out.println("\n" + firstName + " " + lastName + " has been added as a " + level + " with ID " + newStudent.getStudentID() + "\n");
    }
    
 // Method to deactivate a student based on ID
    private static void deactivateStudent() {
        System.out.print("\nEnter the ID for the student you want to deactivate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.getStudentID() == id) {
                student.setActive(false);
                System.out.println("\n" + student.getFirstName() + " " + student.getLastName() + " has been deactivated\n");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    
// Method to display a student in the system 
    private static void displayStudents() {
        System.out.println();
        students.stream()
                .sorted(Comparator.comparing(Student::getFirstName))
                .forEach(student -> System.out.println(student));
    }
    
// Method to find a student in the system based on ID number 
    private static void findStudent() {
        System.out.print("\nEnter the student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Student student : students) {
            if (student.getStudentID() == id) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found.");
    }
}

