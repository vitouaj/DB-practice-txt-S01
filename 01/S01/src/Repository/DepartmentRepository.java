package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Department;

public class DepartmentRepository {
    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/department.txt";

    //backing up file dir
    public static String fileDir2 = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/department2.txt";


    public Department getDepartmentById(int id) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    return new Department(Integer.parseInt(wordList[0]), wordList[1], wordList[2], Integer.parseInt(wordList[3]), Integer.parseInt(wordList[4]));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<Department> getAllDepartmentByFacultyId(int facultyId) {
        String line;
        List<Department> departments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                if (Integer.parseInt(wordList[4]) == (facultyId)) {
                    Department newDepartment = new Department(Integer.parseInt(wordList[0]), wordList[1], wordList[2], Integer.parseInt(wordList[3]), Integer.parseInt(wordList[4]));
                    departments.add(newDepartment);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return departments;
    }

    public void addDepartment(String deptName, String headName, int officeNumber, int facultyId, int initialIndex) {
        Department department = new Department(deptName, headName, officeNumber, facultyId, initialIndex);
        // append = true
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(department.deptId + ", " + department.deptName + ", " + department.headName + ", " + department.officeNumber + ", " + department.facultyId +"\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void updateDepartmentById(int id, String deptName, String headName, int officeNumber, int facultyId) {
        Department department = getDepartmentById(id);

        if (department == null) {
            System.out.println("Can't find id...");
            return;
        }

        List<String> readLines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {

            while((line = reader.readLine()) != null) {
                
                String[] wordList = line.split(", ");                
                
                if (Integer.parseInt(wordList[0]) == id) {
                    String newLine = id + ", " + deptName + ", " + headName + ", " + officeNumber + ", " + facultyId;
                    readLines.add(newLine);
                } else {
                    readLines.add(line);
                }       
            }
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir))) {
            for (String ln : readLines) {
                writer.write(ln + "\n");
            }
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void deleteDepartmentById(int id) {
        List<String> readList = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    continue;
                } else {
                    readList.add(line);
                }
            }

            readList.remove("");


            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir))) {
            for (String ln : readList) {
                writer.write(ln + "\n");
            }
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // public int getNumberOfLine() {
    //     int lineCount = 0;
    //     try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
    //         String line;
    //         while ((line = reader.readLine()) != null) {
    //             lineCount++;
    //         }
    //     } catch (Exception exception) {
    //         exception.printStackTrace();
    //     }
    //     return lineCount;
    // }

    public int getHighestIndex() {
        String line;
        int hightestIndex = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                hightestIndex = Integer.parseInt(wordList[0]);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return hightestIndex;
    }
}
