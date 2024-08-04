import java.util.Scanner;

class Course 
{
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits)
    {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }
    public int getCourseID() 
    {
        return courseID;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public int getCredits()
    {
        return credits;
    }

    @Override
    public String toString() 
    {
        return "Course ID : " + courseID + ", Name : " + courseName + ", Credits : " + credits;
    }
}

class Enrollment
{
    private int[][] studentCourses;
    private int[] count;

    public Enrollment(int numberOfStudents, int maxCoursesPerStudent) 
    {
        studentCourses = new int[numberOfStudents][maxCoursesPerStudent];
        count = new int[numberOfStudents];

        for (int i = 0; i < numberOfStudents; i++) 
        {
            for (int j = 0; j < maxCoursesPerStudent; j++)
            {
                studentCourses[i][j] = -1;
            }
        }
    }

    public void enroll(int studentID, int courseID) 
    {
        if (studentID < 0 || studentID >= studentCourses.length)
        {
            System.out.println("Invalid student ID.");
            return;
        }

        if (count[studentID] < studentCourses[studentID].length) 
        {

            for (int i = 0; i < count[studentID]; i++) 
            {
                if (studentCourses[studentID][i] == courseID) {
                    System.out.println("Student " + studentID + " is already enrolled in course " + courseID);
                    return;
                }
            }
            studentCourses[studentID][count[studentID]++] = courseID;
            System.out.println("Student " + studentID + " enrolled in course " + courseID);
        }
        else 
        {
            System.out.println("Student " + studentID + " cannot enroll in more courses.");
        }
    }

    public void drop(int studentID, int courseID) 
    {
        if (studentID < 0 || studentID >= studentCourses.length) 
        {
            System.out.println("Invalid student ID.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < count[studentID]; i++)
        {
            if (studentCourses[studentID][i] == courseID) 
            {
                found = true;
               
                for (int j = i; j < count[studentID] - 1; j++) 
                {
                    studentCourses[studentID][j] = studentCourses[studentID][j + 1];
                }
                studentCourses[studentID][count[studentID] - 1] = -1; 
                count[studentID]--;
                System.out.println("Student " + studentID + " dropped course " + courseID);
                break;
            }
        }
        if (!found) 
        {
            System.out.println("Course " + courseID + " not found for student " + studentID);
        }
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) 
    {
        if (studentID < 0 || studentID >= studentCourses.length) 
        {
            System.out.println("Invalid student ID.");
            return;
        }

        if (count[studentID] == 0) 
        {
            System.out.println("Student " + studentID + " is not enrolled in any courses.");
        } 
        else 
        {
            System.out.println("Courses for student " + studentID + ":");
            for (int i = 0; i < count[studentID]; i++) 
            {
                int courseID = studentCourses[studentID][i];
                for (Course course : courseCatalog) 
                {
                    if (course.getCourseID() == courseID) 
                    {
                        System.out.println(course);
                        break;
                    }
                }
            }
        }
    }
}

public class CourseEnrollment 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of courses to be added to the catalog : ");
        int numberOfCourses = scanner.nextInt();
        scanner.nextLine(); 

        Course[] courseCatalog = new Course[numberOfCourses];
        for (int i = 0; i < numberOfCourses; i++)
        {
            System.out.println("Enter details for course " + (i + 1) + ":");

            System.out.print("Course ID : ");
            int courseID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Course Name : ");
            String courseName = scanner.nextLine();

            System.out.print("Credits : ");
            int credits = scanner.nextInt();
            scanner.nextLine(); 

            courseCatalog[i] = new Course(courseID, courseName, credits);
        }

        System.out.print("Enter the number of students : ");
        int numberOfStudents = scanner.nextInt();
        System.out.print("Enter the maximum number of courses a student can enroll in : ");
        int maxCoursesPerStudent = scanner.nextInt();
        Enrollment enrollment = new Enrollment(numberOfStudents, maxCoursesPerStudent);

        int choice;

        do 
        {
           
            System.out.println("\nMenu:");
            System.out.println("1. Enroll a student in a course");
            System.out.println("2. Drop a course for a student");
            System.out.println("3. View enrolled courses for a student");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                
                    System.out.print("Enter student ID : ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter course ID : ");
                    int courseID = scanner.nextInt();
                    enrollment.enroll(studentID, courseID);
                    break;

                case 2:
              
                    System.out.print("Enter student ID : ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter course ID : ");
                    courseID = scanner.nextInt();
                    enrollment.drop(studentID, courseID);
                    break;

                case 3:
                    
                    System.out.print("Enter student ID : ");
                    studentID = scanner.nextInt();
                    enrollment.getEnrolledCourses(studentID, courseCatalog);
                    break;

                case 4:
                    
                    System.out.println("Exiting program.");
                    break;

                default:

                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
