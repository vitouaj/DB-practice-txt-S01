package Services;

import java.util.Scanner;

import Models.Faculty;
import Repository.FacultyRepository;
import Utils.Menu;

public class FacultyService {
    private FacultyRepository facultyRepository = new FacultyRepository();
    private static int highestFacultyIndex;

    public void manageFaculty() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.facultyMenu();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Faculty Name: ");
                    String facultyName = scanner.nextLine();
                    System.out.print("Enter Dean Name: ");
                    String deanName = scanner.nextLine();
                    System.out.print("Enter Office Number: ");
                    int officeNumber = scanner.nextInt();


                    highestFacultyIndex = facultyRepository.getHighestIndex();
                    
                    facultyRepository.addFaculty(facultyName, deanName, officeNumber, highestFacultyIndex);
                    scanner.nextLine();
                    break;
                case "b":
                    System.out.print("Enter Faculty Id: ");
                    int id = scanner.nextInt();

                    Faculty txtFaculty = facultyRepository.getFacultyById(id);

                    if (txtFaculty == null) {
                        System.out.println("Can't Find");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\nSearch Found...\n" + txtFaculty.facultyId + ", " + txtFaculty.facultyName + ", " + txtFaculty.deanName + ", " + txtFaculty.officeNumber);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Faculty Id: ");
                    int fId = scanner.nextInt();
                    System.out.print("Faculty Name: ");
                    scanner.nextLine();
                    String fName = scanner.nextLine();
                    System.out.print("Enter Dean Name: ");
                    String dName = scanner.nextLine();
                    System.out.print("Enter Office Number: ");
                    int oNumber = scanner.nextInt();


                    facultyRepository.updateFacultyById(fId, fName, dName, oNumber);
                    scanner.nextLine();
                    break;
                case "d":
                    System.out.print("Enter Faculty Id: ");
                    int fdId = scanner.nextInt();
                    facultyRepository.deleteFacultyById(fdId);
                    highestFacultyIndex++;
                    scanner.nextLine();
                    break;
                case "e":
                    run = false;
                    break;
            }
        }
    }

}
