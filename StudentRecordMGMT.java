import java.util.Scanner;

class Student 
{
    private int student_ID;
    private String name;
    private int age;
    private String department;

    public Student(int studentID, String name, int age, String department)
    {
        this.student_ID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID() 
    {
        return student_ID;
    }

    public String getName() 
    {
        return name;
    }

    public int getAge() 
    {
        return age;
    }

    public String getDepartment()
    {
        return department;
    }

    @Override
    public String toString() 
    {
        return "Student ID : " + student_ID + ", Name : " + name + ", Age : " + age + ", Department : " + department;
    }
}

class StudentRecordSystem 
{
    private Student[] students;
    private int count;

    public StudentRecordSystem(int capacity) 
    {
        students = new Student[capacity];
        count = 0;
    }

    public void addStudent(Student student)
    {
        if (count < students.length) 
        {
            students[count] = student;
            count++;
            System.out.println("Student added successfully.");
        }
        else 
        {
            System.out.println(" Cannot add more students. ");
        }
    }

    public Student getStudent(int studentID) 
    {
        for (int i = 0; i < count; i++) 
        {
            if (students[i].getStudentID() == studentID) 
            {
                return students[i];
            }
        }
        return null; 
    }

    public void displayAllStudents()
    {
        if (count == 0) 
        {
            System.out.println("No student records available.");
        } 
        else 
        {
            for (int i = 0; i < count; i++) 
            {
                System.out.println(students[i]);
            }
        }
    }
}


public class StudentRecordMGMT 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        int capacity = 10; 
        StudentRecordSystem recordSystem = new StudentRecordSystem(capacity);

        while (true)
        {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add Student Record ");
            System.out.println("2. View Student Record by ID ");
            System.out.println("3. Display All Student Records ");
            System.out.println("4. Exit ");
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                   
                    System.out.print("Enter Student ID : ");
                    int student_Id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Name : ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age : ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Department : ");
                    String department = scanner.nextLine();

                    Student student = new Student(student_Id, name, age, department);
                    recordSystem.addStudent(student);
                    break;

                case 2:
                  
                    System.out.print("Enter Student ID : ");
                    student_Id = scanner.nextInt();

                    Student foundStudent = recordSystem.getStudent(student_Id);
                    if (foundStudent != null) 
                    {
                        System.out.println("Student Record : " + foundStudent);
                    } 
                    else 
                    {
                        System.out.println("Student with ID " + student_Id + " not found.");
                    }
                    break;

                case 3:
                    
                    System.out.println("All Student Records : ");
                    recordSystem.displayAllStudents();
                    break;

                case 4:
                   
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:

                    System.out.println("Invalid option.");
            }
        }
    }
}
