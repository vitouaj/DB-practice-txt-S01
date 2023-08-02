package Services;

import java.util.List;
import java.util.Scanner;

import Models.Department;
import Repository.DepartmentRepository;
import Utils.Menu;

public class DepartmentService {
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    private static int highestDepartmentIndex;

    public void manageDepartment() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.departmentMenu();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Department Name: ");
                    String deptName = scanner.nextLine();
                    System.out.print("Enter Head Name: ");
                    String headName = scanner.nextLine();
                    System.out.print("Enter Office Number: ");
                    int officeNumber = scanner.nextInt();
                    System.out.print("Enter Faculty Id: ");
                    int facultyId = scanner.nextInt();


                    highestDepartmentIndex = departmentRepository.getHighestIndex();
                    
                    departmentRepository.addDepartment(deptName, headName, officeNumber, facultyId, highestDepartmentIndex);
                    scanner.nextLine();
                    break;
                case "b":
                    System.out.print("Enter Department Id: ");
                    int id = scanner.nextInt();

                    Department txtDepartment = departmentRepository.getDepartmentById(id);

                    if (txtDepartment == null) {
                        System.out.println("Can't Find");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\nSearch Found...\n" + txtDepartment.deptId + ", " + txtDepartment.deptName + ", " + txtDepartment.headName + ", " + txtDepartment.officeNumber + ", " + txtDepartment.facultyId);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Department Id: ");
                    int dId = scanner.nextInt();
                    System.out.print("Enter Department Name: ");
                    scanner.nextLine();
                    String dName = scanner.nextLine();
                    System.out.print("Enter Head Name: ");
                    String hName = scanner.nextLine();
                    System.out.print("Enter Office Number: ");
                    int oNumber = scanner.nextInt();
                    System.out.print("Enter Faculty Id: ");
                    int fid = scanner.nextInt();


                    departmentRepository.updateDepartmentById(dId, dName, hName, oNumber, fid);
                    scanner.nextLine();
                    break;
                case "d":
                    System.out.print("Enter Department Id: ");
                    int fdId = scanner.nextInt();
                    departmentRepository.deleteDepartmentById(fdId);
                    highestDepartmentIndex++;
                    scanner.nextLine();
                    break;
                case "e":
                    System.out.print("Enter Faculty Id: ");
                    int ffid = scanner.nextInt();
                    List<Department> resultSet = departmentRepository.getAllDepartmentByFacultyId(ffid);

                    System.out.println("");
                    for (Department d : resultSet) {
                        System.out.println(d.deptId + "\t" + d.deptName);
                    }

                    scanner.nextLine();
                    break;
                case "f":
                    run = false;
                    break;
            }
        }
    }
}
