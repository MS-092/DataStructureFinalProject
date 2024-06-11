import java.util.Scanner;

public class StudentDataMana {
    public static class Student{
        int id;
        String name;
        double grade;

        public Student(int id, String name, double grade){
            this.id = id;
            this.name = name;
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void SetId(int id) { 
            this.id = id;
        }

        public String getName() { 
            return name; 
        }
        public void SetName(String name) { 
            this.name = name;
        }

        public Double getGrade() { 
            return grade; 
        }

        public void SetGrade(Double grade) { 
            this.grade = grade;
        }

        public String toString(){
            return "Student{" + "id=" + id + ", name="+ name + ", grade = " +grade + '}';
        }
    }

    public static class Node {
        Student student;
        Node next;

        public Node(Student student){
            this.student = student;
            this.next = null;
        }

        public Student getStudent() { 
            return student; 
        }

        public void setStudent( Student student) { 
            this.student = student;
        }

        public Node getNext() { 
            return next; 
        }

        public void setNext( Node next) { 
            this.next = next;
        }
    }

    public static class StudentLinkedList{
        Node head;
        
        public StudentLinkedList(){
            this.head = null;
        }

        //Method to add student ID
        public void addStudent(Student student){
            Node newNode = new Node(student);
            if (head == null){
                head = newNode;
            } else {
                Node current = head;
                while (current.getNext() != null){
                    current = current.getNext();
                }
                current.setNext(newNode);
            }
        }

        //Method to remove student ID
        public void removeStudent(int id){
            if (head == null) return;

            if (head.getStudent().getId() == id){
                head = head.getNext();
                return;
            }
            Node current = head;
            while(current.getNext() != null && current.getNext().getStudent().getId() != id){
                current = current.getNext();
            }

            if (current.getNext() != null) {
                current.setNext(current.getNext().getNext());
            }
        }
        
        //Method to display all student
        public void displayStudents(){
            Node current = head;
            while (current != null){
                System.out.println(current.getStudent());
                current = current.getNext();
            }
        }

        //Method to find a student by id
        public Student findStudent(int id) {
            Node current = head;
            while (current != null) {
                if (current.getStudent().getId() == id) {
                    return current.getStudent();
                }
                current = current.getNext();
            }
            return null;
        }
    }

    public static void main(String[] args){
        StudentLinkedList studentList = new StudentLinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Display Students");
            System.out.println("4. Find Student");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    //Add student
                    System.out.println("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter student Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter student grade: ");
                    double grade = scanner.nextDouble();

                    studentList.addStudent(new Student(id, name, grade));
                    System.out.println("Student added sucessfully.");
                    break;    

                case 2:
                    //Remove student
                    System.out.println("Enter the Student ID to remove: ");
                    int removeId = scanner.nextInt();
                    studentList.removeStudent(removeId);
                    System.out.println("Student remove sucessfully."); 
                    break;

                case 3:
                    //Display student
                    System.out.println("All student: ");
                    studentList.displayStudents();
                    break;

                case 4:
                    //Find student
                    System.out.println("Enter Student ID to find: ");
                    int findID =scanner.nextInt();

                    Student student = studentList.findStudent(findID);
                    if (student != null) {
                        System.out.println("Student: "+ student);
                    } else {
                        System.out.println("Student haven't added yet.");
                        System.out.println("try again");
                    }
                    break;
                
                case 5:
                    //Exit
                    System.out.println("Exiting.....");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid input. Please try again");
                    break;
            }
        }
    }
}
