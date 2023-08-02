package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Course;

public class CourseRepository {
    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/course.txt";

    public Course getCourseById(int id) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int courseId = Integer.parseInt(wordList[0]);
                String courseName = wordList[1];
                String credit = wordList[2];
                String type = wordList[3];
                int deptId = Integer.parseInt(wordList[4]);

                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    return new Course(courseId, courseName, credit, type, deptId);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public void addCourse(String courseName, String credit, String type, int dpId, int initialIndex) {
        Course newCourse = new Course(courseName, credit, type, dpId, initialIndex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(newCourse.courseId + ", " + newCourse.courseName + ", " + newCourse.credit + ", " + newCourse.type + ", " + newCourse.deptId + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void updateCourseById(int courseId, String courseName, String credit, String type, int dpid) {
        Course course = getCourseById(courseId);
        if (course == null) {
            System.out.println("Can't find Teacher with given id...");
            return;
        }

        List<String> readLines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {

            while((line = reader.readLine()) != null) {
                
                String[] wordList = line.split(", ");                
                
                if (Integer.parseInt(wordList[0]) == courseId) {
                    String newLine = courseId + ", " + courseName + ", " + credit + ", " + type + ", " + dpid;
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

    public void deleteCourseById(int id) {
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

    public List<Course> getAllCourseByDeptId(int deptId) {
        String line;
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int courseId = Integer.parseInt(wordList[0]);
                String courseName = wordList[1];
                String credit = wordList[2];
                String type = wordList[3];
                int depId = Integer.parseInt(wordList[4]);


                if (Integer.parseInt(wordList[4]) == (deptId)) {
                    Course newCourse = new Course(
                        courseId,
                        courseName,
                        credit,
                        type,
                        depId
                    );
                    courses.add(newCourse);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return courses;
    }
}
