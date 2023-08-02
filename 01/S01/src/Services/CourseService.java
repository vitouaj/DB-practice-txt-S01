package Services;

import java.util.List;
import java.util.Scanner;

import Models.Course;
import Models.Teacher;
import Repository.CourseRepository;
import Repository.CourseTeacherRepository;
import Utils.Menu;

public class CourseService {
    
    private CourseRepository courseRepository = new CourseRepository();
    private CourseTeacherRepository courseTeacherRepository = new CourseTeacherRepository();

    private static int highestCourseIndex;

    public void manageCourseCrud() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.courseMenu();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Enter Course Name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter Credit: ");
                    String credit = scanner.nextLine();
                    System.out.print("Enter Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Department Id: ");
                    int deptId = scanner.nextInt();


                    highestCourseIndex = courseRepository.getHighestIndex();
                    
                    courseRepository.addCourse(courseName, credit, type, deptId, highestCourseIndex);
                    scanner.nextLine();
                    break;
                case "b":
                    System.out.print("Enter Course Id: ");
                    int id = scanner.nextInt();

                    Course txtCourse = courseRepository.getCourseById(id);

                    if (txtCourse == null) {
                        System.out.println("Can't Find");
                        scanner.nextLine();
                        break;
                    }
                    System.out.println("");
                    System.out.println("Search Found...");
                    System.out.println(txtCourse.courseId + " " + txtCourse.courseName + " " + txtCourse.credit  +" " + txtCourse.type + " " + txtCourse.deptId);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Course Id: ");
                    int courseId = scanner.nextInt();
                    System.out.print("Enter Course Name: ");
                    scanner.nextLine();
                    String cName = scanner.nextLine();
                    System.out.print("Enter Credit: ");
                    String cCredit = scanner.nextLine();
                    System.out.print("Enter Type: ");
                    String cType = scanner.nextLine();

                    

                    System.out.print("Enter Department Id: ");
                    int did = scanner.nextInt();


                    courseRepository.updateCourseById(courseId, cName, cCredit, cType, did);
                    scanner.nextLine();
                    break;
                case "d":
                    System.out.print("Enter Course Id: ");
                    int fdId = scanner.nextInt();
                    courseRepository.deleteCourseById(fdId);
                    highestCourseIndex++;
                    scanner.nextLine();
                    break;
                case "e":
                    run = false;
                    break;
            }
        }
    }

    public void assignCourseToTeacher() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.assignCourseToTeacher();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Enter Course Id: ");
                    int cid = scanner.nextInt();
                    System.out.print("Enter Teacher Id: ");
                    int tid = scanner.nextInt();
                    courseTeacherRepository.assignCourse(cid, tid);
                    scanner.nextLine();
                    break;

                case "b":
                    System.out.print("Enter Course Id: ");
                    int ccid = scanner.nextInt();
                    System.out.print("Enter Teacher Id: ");
                    int ttid = scanner.nextInt();

                    courseTeacherRepository.removeCourseFromTeacher(ccid, ttid);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Teacher Id: ");
                    int tttid = scanner.nextInt();

                    List<Course> courses = courseTeacherRepository.getAllCoursesByTeacher(tttid);

                    System.out.println("\n\tCourse Id\tCourse Name");
                    for (Course course : courses) {
                        
                        System.out.println( "\t"+course.courseId + "\t\t" + course.courseName);
                    }
                    System.out.println("");
                    scanner.nextLine();
                    break;
            }
        }

    }

}
