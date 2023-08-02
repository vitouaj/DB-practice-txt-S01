package Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Faculty {
    public int facultyId;
    public String facultyName;
    public String deanName;
    public int officeNumber;

    public Faculty() {}
    
    public Faculty(String facultyName, String deanName, int officeNumber, int initialIndex) {
        this.facultyId = new AtomicInteger(initialIndex).incrementAndGet();
        this.facultyName = facultyName;
        this.deanName = deanName;
        this.officeNumber = officeNumber;
    }

    public Faculty(int id, String name, String deanName, int offNo) {
        this.facultyId = id;
        this.facultyName = name;
        this.deanName = deanName;
        this.officeNumber = offNo;
    }
}
