import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save student data to a file");
            System.out.println("6. Load student data from a file");
            System.out.println("7. Exit");

            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter student roll number: ");
                    String rollNumber = scanner.nextLine();

                    System.out.print("Enter student grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Student student = new Student(name, rollNumber, grade);
                    sms.addStudent(student);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter the roll number of the student to remove: ");
                    String removeRollNumber = scanner.nextLine();
                    sms.removeStudent(removeRollNumber);
                    System.out.println("Student removed successfully!");
                    break;

                case 3:
                    System.out.print("Enter the roll number of the student to search: ");
                    String searchRollNumber = scanner.nextLine();
                    Student foundStudent = sms.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student found:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    System.out.println("All students:");
                    for (Student s : sms.getAllStudents()) {
                        System.out.println(s);
                    }
                    break;

                case 5:
                    sms.saveToFile("studentData.ser");
                    System.out.println("Student data saved to a file.");
                    break;

                case 6:
                    sms.loadFromFile("studentData.ser");
                    System.out.println("Student data loaded from the file.");
                    break;

                case 7:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
