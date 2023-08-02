package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Department;
import Models.Student;

public class StudentDepartmentRepository {

    private StudentRepository studentRepository;
    private DepartmentRepository departmentRepository;

    public StudentDepartmentRepository() {
        this.studentRepository = new StudentRepository();
        this.departmentRepository = new DepartmentRepository();
    }
    
    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/student_department.txt";

    public void enrollExistingStudent(int stuId, int depId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(stuId + ", " + depId + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enrollNewStudent(String name, String gender, String dateString, String phone, int depId, int initialIndex) {
        int id  = studentRepository.addStudent(name, gender, dateString, phone, initialIndex);
        enrollExistingStudent(id, depId);
    }

    public void removeStudent(int sid, int did) {

        boolean existed = checkExist(sid, did);

        if (existed == false) {
            System.out.println("Student or Department ID does not exist!");
            return;
        }
        // delete ONE stuId and deptId from Student_department table
        List<String> readList = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                if (Integer.parseInt(wordList[0]) == (sid) && Integer.parseInt(wordList[1])==did) {
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

    public List<Student> getAllStudentInDep(int depId) {
        String line;
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int studentId = Integer.parseInt(wordList[0]);
                int deptId = Integer.parseInt(wordList[1]);

            
                if (depId == deptId) {
                    Student student = studentRepository.getStudentById(studentId);
                    students.add(student);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return students;
    }

    public List<Department> getAllDepartmentByStudentId(int stuId) {
        String line;
        List<Department> departments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int studentId = Integer.parseInt(wordList[0]);
                int deptId = Integer.parseInt(wordList[1]);

            
                if (studentId == stuId) {
                    Department dep = departmentRepository.getDepartmentById(deptId);
                    departments.add(dep);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return departments;
    }
    
    public boolean checkExist(int sid, int did) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int studentId = Integer.parseInt(wordList[0]);
                int deptId = Integer.parseInt(wordList[1]);

            
                if (studentId == sid && deptId == did) {
                    return true;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

}
