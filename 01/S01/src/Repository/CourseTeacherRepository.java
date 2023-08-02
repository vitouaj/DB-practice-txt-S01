package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Course;

public class CourseTeacherRepository {

    private CourseRepository courseRepository;

    public CourseTeacherRepository() {
        this.courseRepository = new CourseRepository();
    }
    

    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/teacher_course.txt";


    public void assignCourse(int cid, int tid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(tid + ", " + cid + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCourseFromTeacher(int cid, int tid) {

        boolean existed = checkExist(cid, tid);

        if (existed == false) {
            System.out.println("\n\tCourse or Teacher ID does not exist!\n");
            return;
        }
        // delete ONE record of stuId and deptId from Student_department table
        List<String> readList = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                if (Integer.parseInt(wordList[0]) == (tid) && Integer.parseInt(wordList[1]) == cid) {
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

    public boolean checkExist(int cid, int tid) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int teacherId = Integer.parseInt(wordList[0]);
                int courseId = Integer.parseInt(wordList[1]);

            
                if (courseId == cid && teacherId == tid) {
                    return true;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public List<Course> getAllCoursesByTeacher(int tid) {
        String line;
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int ttid = Integer.parseInt(wordList[0]);
                int ccid = Integer.parseInt(wordList[1]);

            
                if (tid == ttid) {
                    Course course = courseRepository.getCourseById(ccid);
                    courses.add(course);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return courses;
    }
}
