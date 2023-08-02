package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import Models.Teacher;



public class TeacherRepository {
    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/teacher.txt";

    public Teacher getTeacherById(int id) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int teacherId = Integer.parseInt(wordList[0]);
                String name = wordList[1];
                String gender = wordList[2];
                String phone = wordList[4];
                String dob = wordList[3];
                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    return new Teacher(teacherId, name, gender, dob, phone);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void addTeacher(String name, String gender, String dateString, String phone, int initialIndex) {
        Teacher newTeacher = new Teacher(name, gender, dateString, phone, initialIndex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(newTeacher.teacherId + ", " + newTeacher.name + ", " + newTeacher.gender + ", " + newTeacher.DOB + ", " + newTeacher.phoneNum + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void updateTeacherById(int id, String name, String gender, String date, String phone) {
        Teacher teacher = getTeacherById(id);
        if (teacher == null) {
            System.out.println("Can't find Teacher with given id...");
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
    
    public void deleteTeacherById(int id) {
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

    // public List<Teacher> getAllTeacherByDeptId(int deptId) {
    //     String line;
    //     List<Teacher> teachers = new ArrayList<>();
    //     try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
    //         while ((line = reader.readLine()) != null) {
    //             String[] wordList = line.split(", ");
    //             int teacherId = Integer.parseInt(wordList[0]);
    //             String name = wordList[1];
    //             String gender = wordList[2];
    //             String phone = wordList[4];

    //             String dateString = wordList[3];
    //             if (Integer.parseInt(wordList[5]) == (deptId)) {
    //                 Teacher newTeacher = new Teacher(
    //                     teacherId,
    //                     name,
    //                     gender,
    //                     dateString,
    //                     phone
    //                 );
    //                 teachers.add(newTeacher);
    //             }
    //         }
    //     } catch (IOException exception) {
    //         exception.printStackTrace();
    //     }
    //     return teachers;
    // }
}
