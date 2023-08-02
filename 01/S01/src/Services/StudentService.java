package Services;

import java.util.List;
import java.util.Scanner;

import Models.Department;
import Models.Student;
import Repository.StudentDepartmentRepository;
import Repository.StudentRepository;
import Utils.Menu;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    private StudentDepartmentRepository studentDepartmentRepository = new StudentDepartmentRepository();

    private static int highestStudentIndex;

    public void manageStudentCrud() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
          
            Menu.studentMenuCrud();
            
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter DOB ('dd/MM/yy'): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();
        

                    highestStudentIndex = studentRepository.getHighestIndex();
 
                    int studentID = studentRepository.addStudent(name, gender, dob, phone, highestStudentIndex);
                    
                    break;
                case "b":
                    System.out.print("Enter Student Id: ");
                    int id = scanner.nextInt();

                    Student txtStudent = studentRepository.getStudentById(id);

                    if (txtStudent == null) {
                        System.out.println("Can't Find");
                        scanner.nextLine();
                        break;
                    }
                    System.out.println("");
                    System.out.println("Search Found...");
                    System.out.println(txtStudent.studentId + " " + txtStudent.name + " " + txtStudent.gender  +" " + txtStudent.DOB + " " + txtStudent.phoneNum);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Student Id: ");
                    int sId = scanner.nextInt();
                    System.out.print("Enter Student Name: ");
                    scanner.nextLine();
                    String dName = scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gd = scanner.nextLine();
                    System.out.print("Enter DOB('dd/mm/yyyy'): ");
                    String dd = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String pp = scanner.nextLine();

                    studentRepository.updateStudentById(sId, dName, gd, dd, pp);
                    break;
                case "d":
                    System.out.print("Enter Student Id: ");
                    int fdId = scanner.nextInt();
                    studentRepository.deleteStudentById(fdId);
                    highestStudentIndex++;
                    scanner.nextLine();
                    break;
                // case "e":
                //     System.out.print("Enter Faculty Id: ");
                //     int ffid = scanner.nextInt();
                //     List<Student> resultSet = studentRepository.getAllStudentByDeptId(ffid);

                //     System.out.println("");
                //     for (Student s : resultSet) {
                //         System.out.println(s.studentId + "\t" + s.name + "\t\t" + s.gender  +"\t" + s.DOB + "\t" + s.phoneNum);
                //     }

                //     scanner.nextLine();
                //     break;
                case "f":
                    run = false;
                    break;
            }
        }
    }


    public void enrollStudent() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
          
            Menu.enrollStudent();
            
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":

                    System.out.println("a. Enroll Existing Student (more than 2 major)");
                    System.out.println("b. Enroll New Student to a department");
                    System.out.print("Commad: ");
                    String cmd = scanner.nextLine();

                    switch (cmd) {
                        case "a":
                            System.out.print("Enter Student Id: ");
                            int sId = scanner.nextInt();
                        
                            System.out.print("Enter Dept Id: ");
                            int dId = scanner.nextInt();

                            studentDepartmentRepository.enrollExistingStudent(sId, dId);
                            scanner.nextLine();
                            break;
                        case "b":
                            System.out.print("Enter Student Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Gender: ");
                            String gender = scanner.nextLine();
                            System.out.print("Enter DOB ('dd/MM/yy'): ");
                            String dob = scanner.nextLine();
                            System.out.print("Enter Phone Number: ");
                            String phone = scanner.nextLine();
                            System.out.print("Enter Department Id: ");
                            int deptId = scanner.nextInt();

                            highestStudentIndex = studentRepository.getHighestIndex();

                            studentDepartmentRepository.enrollNewStudent(name, gender, dob, phone, deptId, highestStudentIndex);
                            
                            scanner.nextLine();
                            break;
                    }
                    break;
                case "b":
                    System.out.print("Enter Student Id: ");
                    int sid = scanner.nextInt();
                    System.out.print("Enter Department Id: ");
                    int did = scanner.nextInt();

                    studentDepartmentRepository.removeStudent(sid, did);

                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Department Id: ");
                    int dId = scanner.nextInt();
                    
                    List<Student> students = studentDepartmentRepository.getAllStudentInDep(dId);

                    for (Student student : students) {
                        System.out.println(student.studentId + "\t" + student.name);
                    }

                    scanner.nextLine();
                    break;
                case "d":
                    System.out.print("Enter Student Id: ");
                    int Sid = scanner.nextInt();
                    
                    List<Department> departments = studentDepartmentRepository.getAllDepartmentByStudentId(Sid);

                    for (Department department : departments) {
                        System.out.println(department.deptId + "\t" + department.deptName);
                    }

                    scanner.nextLine();
                    break;
                case "e":
                    run = false;
                    break;
            }
        }
    }
}
