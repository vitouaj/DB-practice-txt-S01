package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import Models.Student;

public class StudentRepository {
    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/student.txt";

    public Student getStudentById(int id) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int studentId = Integer.parseInt(wordList[0]);
                String name = wordList[1];
                String gender = wordList[2];
                String phone = wordList[4];

                String dateString = wordList[3];
                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    return new Student(studentId, name, gender, dateString, phone);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public int addStudent(String name, String gender, String dateString, String phone, int initialIndex) {
        Student newStudent = new Student(name, gender, dateString, phone, initialIndex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(newStudent.studentId + ", " + newStudent.name + ", " + newStudent.gender + ", " + newStudent.DOB + ", " + newStudent.phoneNum + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return newStudent.studentId;
    }

    public void updateStudentById(int id, String name, String gender, String date, String phone) {
        Student student = getStudentById(id);
        if (student == null) {
            System.out.println("Can't find Student with given id...");
            return;
        }

        List<String> readLines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {

            while((line = reader.readLine()) != null) {
                
                String[] wordList = line.split(", ");                
                
                if (Integer.parseInt(wordList[0]) == id) {
                    String newLine = id + ", " + name + ", " + gender + ", " + date + ", " + phone;
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
    
    public void deleteStudentById(int id) {
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

    // public List<Student> getAllStudentByDeptId(int deptId) {
    //     String line;
    //     List<Student> students = new ArrayList<>();
    //     try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
    //         while ((line = reader.readLine()) != null) {
    //             String[] wordList = line.split(", ");
    //             int studentId = Integer.parseInt(wordList[0]);
    //             String name = wordList[1];
    //             String gender = wordList[2];
    //             String phone = wordList[4];

    //             String dateString = wordList[3];
    //             if (Integer.parseInt(wordList[5]) == (deptId)) {
    //                 Student newStudent = new Student(
    //                     studentId,
    //                     name,
    //                     gender,
    //                     dateString,
    //                     phone
    //                 );
    //                 students.add(newStudent);
    //             }
    //         }
    //     } catch (IOException exception) {
    //         exception.printStackTrace();
    //     }
    //     return students;
    // }
}
