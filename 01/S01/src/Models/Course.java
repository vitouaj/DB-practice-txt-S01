package Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    public int courseId;
    public String courseName;
    public String credit;
    public String type;
    public int deptId;



    public Course(int courseId, String courseName, String credit, String type, int deptId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.type = type;
        this.deptId = deptId;
    }

    public Course(String courseName, String credit, String type, int deptId, int initialIndex) {
        this.courseId = new AtomicInteger(initialIndex).incrementAndGet();
        this.courseName = courseName;
        this.credit = credit;
        this.type = type;
        this.deptId = deptId;
    }
}
