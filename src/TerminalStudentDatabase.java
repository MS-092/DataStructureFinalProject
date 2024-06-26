import java.util.*;

public class TerminalStudentDatabase {

    private static HashMap<String, Student> students = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeStudentData(100);

        boolean running = true;
        while (running) {
            System.out.println("\nStudent Database Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Student");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    removeStudent(scanner);
                    break;
                case 4:
                    searchStudent(scanner);
                    break;
                case 5:
                    sortStudent(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
            }
        }

        scanner.close();
    }

    private static String getRandomName() {
        String[] names = {"John", "Jane", "Alice", "Bob", "Mike", "Emma", "Oliver", "Sophia", "William", "Mia"};
        return names[new Random().nextInt(names.length)];
    }

    // Method to generate a random name
    private static void initializeStudentData(int numofStudents) {
        String[] genders = {"M", "F"};

        // set amount of students for the dataset, all are randomly(1-200)
        for (int i = 1; i <= numofStudents; i++) {
            String id = "S" + i;
            String name = getRandomName();
            int age = 18 + (int) (Math.random() * 3);
            String gender = genders[i % 2];
            double assignment1 = Math.random() * 100;
            double assignment2 = Math.random() * 100;

            Student student = new Student(id, name, age, gender, assignment1, assignment2);
            students.put(id, student);
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String id = scanner.next();
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student gender (M/F): ");
        String gender = scanner.next();
        System.out.print("Enter assignment 1 score: ");
        double assignment1 = scanner.nextDouble();
        System.out.print("Enter assignment 2 score: ");
        double assignment2 = scanner.nextDouble();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Student student = new Student(id, name, age, gender, assignment1, assignment2);
        students.put(id, student);
        System.out.println("Student added successfully.");
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 10000000; // one 0
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to add student: " + duration + " ms");
        System.out.println("Memory used for add: " + memoryUsed + " bytes");
    }

    private static void viewStudents() {

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("\nCurrent Students:");
        for (Student student : students.values()) {
            System.out.println(student);
        }
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to view student: " + duration + " ms");
        System.out.println("Memory used for view: " + memoryUsed + " bytes");
    }

    private static void removeStudent(Scanner scanner) {

        System.out.print("Enter student ID to remove: ");
        String id = scanner.next();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student name successfully.");
        } else {
            System.out.println("Student not found.");
        }
        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to remove student: " + duration + " ms");
        System.out.println("Memory used for remove: " + memoryUsed + " bytes");
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter student ID to search: ");
        String id = scanner.next();

        // Force garbage collection to minimize interference
        System.gc();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        Student student = linearSearch(id);

        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endTime = System.nanoTime();

        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }

        long duration = (endTime - startTime);
        long memoryUsed = endMemory - startMemory;

        // Looking at the time and space complexity performance
        System.out.println("Time taken to search student: " + duration + " nano");
        System.out.println("Memory used for linear search: " + memoryUsed + " bytes");
    }

    // Method of Linear Search implementation
    private static Student linearSearch(String id) {
        for (Student student : students.values()) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Method to sort students by final score
    private static void sortStudent(Scanner scanner) {
        System.out.println("Choose the sorting criteria:\n1. By Name\n2. By Age\n3. By Final Score");
        int choice = scanner.nextInt();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        List<Student> studentsList = new ArrayList<>(students.values());
        Comparator<Student> comparator;

        switch (choice) {
            case 1:
                comparator = Comparator.comparing(Student::getName);
                break;
            case 2:
                comparator = Comparator.comparingInt(Student::getAge);
                break;
            case 3:
                comparator = Comparator.comparingDouble(Student::getFinalscore);
                break;
            default:
                System.out.println("Invalid selection. Sorting by name.");
                comparator = Comparator.comparing(Student::getName);
        }

        studentsList.sort(comparator.reversed()); // Sort in descending order

        System.out.println("\nSorted Students:");
        for (Student student : studentsList) {
            System.out.println(student);
        }

        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long duration = (endTime - startTime) / 1000000;
        long memoryUsed = endMemory - startMemory;
        System.out.println("Time taken to add student: " + duration + " ms");
        System.out.println("Memory used for add: " + memoryUsed + " bytes");
    }


    public static class Student {
        private String id;
        private String name;
        private int age;
        private String gender;
        private double assignment1;
        private double assignment2;
        private double finalscore;

        public Student(String id, String name, int age, String gender, double assignment1, double assignment2) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.assignment1 = assignment1;
            this.assignment2 = assignment2;
            this.finalscore = calculateFinalScore();
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Getter for age
        public int getAge() {
            return age;
        }

        // Getter for final score
        public double getFinalscore() {
            return finalscore;
        }

        // Calculation on how does Final Score calculated
        private double calculateFinalScore() {
            double percentage1 = 0.35;
            double percentage2 = 0.65;
            return (assignment1 * percentage1) + (assignment2 * percentage2);
        }

        // Looking at the full detail of student's information
        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender +
                    ", Assignment 1 Score: " + String.format("%.2f", assignment1) +
                    ", Assignment 2 Score: " + String.format("%.2f", assignment2) +
                    ", Final Score: " + String.format("%.2f", finalscore);
        }
    }
}