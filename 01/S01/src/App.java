import java.util.Scanner;

import Models.LoginResponse;
import Repository.AccountRepository;
import Utils.Menu;
import Services.AccountService;
import Services.CourseService;
import Services.DepartmentService;
import Services.FacultyService;
import Services.StudentService;
import Services.TeacherService;

public class App {

    private static CourseService courseService = new CourseService();
    private static TeacherService teacherService = new TeacherService();
    private static StudentService studentService = new StudentService();
    private static DepartmentService departmentService = new DepartmentService();
    private static FacultyService facultyService = new FacultyService();
    private static AccountService accountService = new AccountService();
    private static AccountRepository accountRepository = new AccountRepository();
    public static void main(String[] args) throws Exception {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            scanner.nextLine();
            System.out.println("Login...");
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            LoginResponse response = accountRepository.login(username, password);

            if (response.isLogin == false) {
                System.out.println("\n\tLogin Failed...");
                System.out.println("\tApplication Terminate!");
                return;
            }

            System.out.println("\n\tHi "+ response.entityName);


            
            Menu.applicationMenu();
            System.out.print("Command: ");
            int command = scanner.nextInt();

            switch (command) {
                case 1: 
                    facultyService.manageFaculty();
                    break;
                case 2:
                    departmentService.manageDepartment();
                    break;
                case 3:
                    studentService.manageStudentCrud();
                    break;
                case 4:
                    studentService.enrollStudent();
                    break;
                case 5:
                    courseService.manageCourseCrud();
                    break;
                case 6:
                    teacherService.manageTeacher();
                    break;
                case 7:
                    courseService.assignCourseToTeacher();
                    break;
                case 8:
                    accountService.createAccount();
                default:
                    System.out.println("invalid command!...");
                    run = false;
                    scanner.close();
                    break;
            }
        }
        scanner.nextLine();
    }
}
