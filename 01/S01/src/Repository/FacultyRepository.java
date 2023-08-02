package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Faculty;

public class FacultyRepository {

    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/faculty.txt";

    //backing up file dir
    public static String fileDir2 = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/faculty2.txt";


    public Faculty getFacultyById(int id) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    return new Faculty(Integer.parseInt(wordList[0]), wordList[1], wordList[2], Integer.parseInt(wordList[3]));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void addFaculty(String facultyName, String deanName, int officeNumber, int initialIndex) {
        Faculty newFaculty = new Faculty(facultyName, deanName, officeNumber, initialIndex);
        // append = true
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(newFaculty.facultyId + ", " + newFaculty.facultyName + ", " + newFaculty.deanName + ", " + newFaculty.officeNumber + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void updateFacultyById(int id, String facultyName, String deanName, int officeNumber) {
        Faculty faculty = getFacultyById(id);

        if (faculty == null) {
            System.out.println("Can't find id...");
            return;
        }

        List<String> readLines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {

            while((line = reader.readLine()) != null) {
                
                String[] wordList = line.split(", ");                
                
                if (Integer.parseInt(wordList[0]) == id) {
                    String newLine = id + ", " + facultyName + ", " + deanName + ", " + officeNumber;
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

    public void deleteFacultyById(int id) {
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
