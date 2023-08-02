package Services;

import java.util.List;
import java.util.Scanner;

import Utils.Menu;
import Models.Teacher;
import Repository.TeacherRepository;

public class TeacherService {

    private TeacherRepository teacherRepository = new TeacherRepository();
    private static int highestTeacherIndex;

    public void manageTeacher() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.teacherMenu();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Enter Teacher Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter DOB ('dd/MM/yy'): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();


                    highestTeacherIndex = teacherRepository.getHighestIndex();
                    
                    teacherRepository.addTeacher(name, gender, dob, phone, highestTeacherIndex);
                    
                    break;
                case "b":
                    System.out.print("Enter Teacher Id: ");
                    int id = scanner.nextInt();

                    Teacher txtTeacher = teacherRepository.getTeacherById(id);

                    if (txtTeacher == null) {
                        System.out.println("Can't Find");
                        scanner.nextLine();
                        break;
                    }
                    System.out.println("");
                    System.out.println("Search Found...");
                    System.out.println(txtTeacher.teacherId + " " + txtTeacher.name + " " + txtTeacher.gender  +" " + txtTeacher.DOB + " " + txtTeacher.phoneNum);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Teacher Id: ");
                    int dId = scanner.nextInt();
                    System.out.print("Enter Teacher Name: ");
                    scanner.nextLine();
                    String dName = scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gd = scanner.nextLine();
                    System.out.print("Enter DOB('dd/mm/yyyy'): ");
                    String dd = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String pp = scanner.nextLine();

                  

                    teacherRepository.updateTeacherById(dId, dName, gd, dd, pp);
                    break;
                case "d":
                    System.out.print("Enter Teacher Id: ");
                    int fdId = scanner.nextInt();
                    teacherRepository.deleteTeacherById(fdId);
                    highestTeacherIndex++;
                    scanner.nextLine();
                    break;
                // case "e":
                //     System.out.print("Enter Department Id: ");
                //     int ffid = scanner.nextInt();
                //     List<Teacher> resultSet = teacherRepository.getAllTeacherByDeptId(ffid);

                //     System.out.println("");
                //     for (Teacher s : resultSet) {
                //         System.out.println(s.teacherId + "\t" + s.name + "\t\t" + s.gender  +"\t" + s.DOB + "\t" + s.phoneNum + "\t" + s.deptId);
                //     }

                //     scanner.nextLine();
                //     break;
                case "e":
                    run = false;
                    break;
            }
        }
    }

}
