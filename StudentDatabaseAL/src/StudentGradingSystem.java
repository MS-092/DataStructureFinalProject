import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

// Define a Student class to hold student information
class Student {
    private String id;
    private String name;
    private int age;
    private double assignment1;
    private double assignment2;
    private double finalScore;

    // Constructor to create a new Student object
    public Student(String id, String name, int age, double assignment1, double assignment2) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.finalScore = calculateFinalScore(assignment1, assignment2);
    }

    // Method to calculate the final score based on assignment scores and percentages
    private double calculateFinalScore(double assignment1, double assignment2) {
        double percentage1 = 0.35;
        double percentage2 = 0.65;
        return (assignment1 * percentage1) + (assignment2 * percentage2);
    }

    // Getter method for the student's ID
    public String getId() {
        return id;
    }

    // Getter method for the student's name
    public String getName() {
        return name;
    }

    // Getter method for the student's final score
    public double getFinalScore() {
        return finalScore;
    }

    // Override the toString method to provide a string representation of the Student object
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Assignment 1: " + assignment1 + ", Assignment 2: " + assignment2 + ", Final Score: " + finalScore;
    }
}

public class StudentGradingSystem {
    private List<Student> students; // List to store all students
    private Scanner scanner; // Scanner object to read user input

    // Constructor to initialize the StudentGradingSystem
    public StudentGradingSystem() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        addRandomStudents(900); // Add 100 random students to the list
    }

    // Method to add a specified number of random students to the list
    private void addRandomStudents(int count) {
        for (int i = 0; i < count; i++) {
            String id = "S" + (i + 1); // Generate a unique ID for each student
            String name = getRandomName(); // Generate a random name
            int age = getRandomAge(); // Generate a random age
            double assignment1 = getRandomScore(); // Generate a random score for assignment 1
            double assignment2 = getRandomScore(); // Generate a random score for assignment 2
            students.add(new Student(id, name, age, assignment1, assignment2));
        }
    }

    // Method to generate a random name
    private String getRandomName() {
        String[] names = {"John", "Jane", "Alice", "Bob", "Mike", "Emma", "Oliver", "Sophia", "William", "Mia"};
        return names[new Random().nextInt(names.length)];
    }

    // Method to generate a random age
    private int getRandomAge() {
        return new Random().nextInt(100) + 1; // Generate a random age between 1 and 100
    }

    // Method to generate a random score
    private double getRandomScore() {
        return new Random().nextDouble() * 100; // Generate a random score between 0 and 100
    }

    // Main method to run the student grading system
    public void run() {
        while (true) {
            displayMenu(); // Display the menu options to the user
            int option = getOption(); // Get the user's chosen option
            processOption(option); // Process the user's chosen option
        }
    }

    // Method to display the menu options to the user
    private void displayMenu() {
        System.out.println("1. Add student");
        System.out.println("2. Remove student");
        System.out.println("3. Search student");
        System.out.println("4. View all students");
        System.out.println("5. Filter students by final score");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    // Method to get the user's chosen option
    private int getOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid option. Please choose again.");
            scanner.next(); // Consume invalid input
            return getOption();
        }
    }

    // Method to process the user's chosen option
    private void processOption(int option) {
        switch (option) {
            case 1:
                addStudent(); // Add a new student
                break;
            case 2:
                removeStudent(); // Remove a student
                break;
            case 3:
                searchStudent(); // Search for a student
                break;
            case 4:
                viewAllStudents(); // View all students
                break;
            case 5:
                filterStudentsByFinalScore(); // Filter students by final score
                break;
            case 6:
                System.out.println("Exiting the student grading system...");
                System.exit(0); // Exit the program
                break;
            default:
                System.out.println("Invalid option. Please choose again.");
                break;
        }
    }

    // Method to add a new student
    private void addStudent() {
        System.out.print("Enter the student's ID: ");
        String id = scanner.next();
        System.out.print("Enter the student's name: ");
        String name = scanner.next();
        System.out.print("Enter the student's age: ");
        int age = scanner.nextInt();
        System.out.print("Enter the student's assignment 1 score: ");
        double assignment1 = scanner.nextDouble();
        System.out.print("Enter the student's assignment 2 score: ");
        double assignment2 = scanner.nextDouble();
        Student newStudent = new Student(id, name, age, assignment1, assignment2);
        students.add(newStudent);
        System.out.println("Student added successfully.");
    }

    // Method to remove a student
    private void removeStudent() {
        System.out.print("Enter the student's ID: ");
        String id = scanner.next();
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId().equals(id)) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("No student found with the given ID.");
    }

    // Method to search for a student
    private void searchStudent() {
        System.out.print("Enter student ID or name to search: ");
        String query = scanner.next().toUpperCase();
        long startTime = System.nanoTime(); // Start timer
        Runtime runtime = Runtime.getRuntime(); // Get the current runtime
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory(); // Get the used memory before the search
        boolean studentFound = false;
        for (Student student : students) {
            if (student.getId().equals(query) || student.getName().toUpperCase().equals(query)) {
                System.out.println(student);
                studentFound = true;
                break;
            }
        }
        if (!studentFound) {
            System.out.println("Student not found.");
        }
        long endTime = System.nanoTime(); // Stop timer
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory(); // Get the used memory after the search
        long allocatedMemory = usedMemoryAfter - usedMemoryBefore; // Calculate the allocated memory
        System.out.println("Time taken to search student: " + duration + " ms");
        System.out.println("Allocated memory: " + allocatedMemory + " bytes");
    }

    // Method to view all students
    private void viewAllStudents() {
        System.out.println("ID\tName\tAge\tAssignment 1\tAssignment 2\tFinal Score");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Method to filter students by final score
    private void filterStudentsByFinalScore() {
        System.out.println("Enter the minimum final score to filter by:");
        double minScore = scanner.nextDouble();
        System.out.println("Enter the maximum final score to filter by:");
        double maxScore = scanner.nextDouble();
        System.out.println("Filtered students by final score:");
        System.out.println("ID\tName\tAge\tAssignment 1\tAssignment 2\tFinal Score");
        long startTime = System.nanoTime(); // Start timer
        Runtime runtime = Runtime.getRuntime(); // Get the current runtime
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory(); // Get the used memory before
        for (Student student : students) {
            if (student.getFinalScore() >= minScore && student.getFinalScore() <= maxScore) {
                System.out.println(student);
            }
        }
        long endTime = System.nanoTime(); // Stop timer
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory(); // Get the used memory after the search
        long allocatedMemory = usedMemoryAfter - usedMemoryBefore; // Calculate the allocated memory
        System.out.println("Time taken to search student: " + duration + " ms");
        System.out.println("Allocated memory: " + allocatedMemory + " bytes");
    }

    // Main method to run the student grading system
    public static void main(String[] args) {
        StudentGradingSystem system = new StudentGradingSystem();
        system.run();
    }
}
