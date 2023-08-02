package Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {
    public int studentId;
    public String name;
    public String gender;
    public String DOB;
    public String phoneNum;

    public Student(int id, String name, String gender, String dob, String phone) {
        this.studentId = id;
        this.name = name;
        this.gender = gender;
        this.DOB = dob;
        this.phoneNum = phone;
    }
    public Student(String name, String gender, String dob, String phone, int initialIndex) {
        this.studentId = new AtomicInteger(initialIndex).incrementAndGet();
        this.name = name;
        this.gender = gender;
        this.phoneNum = phone;
        this.DOB = dob;
    }
    public Student() {}
}
