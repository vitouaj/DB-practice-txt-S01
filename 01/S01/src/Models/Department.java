package Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Department {
    public int deptId;
    public String deptName;
    public String headName;
    public int officeNumber;
    public int facultyId;


    public Department(int deptId, String deptName, String headName, int officeNumber, int facultyId) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.headName = headName;
        this.officeNumber = officeNumber;
        this.facultyId = facultyId;
    }

    public Department(String deptName, String deptHead, int officeNumber, int facultyId, int initialIndex) {
        this.deptId = new AtomicInteger(initialIndex).incrementAndGet();
        this.headName = deptHead;
        this.deptName = deptName;
        this.officeNumber = officeNumber;
        this.facultyId = facultyId;
    }
}
