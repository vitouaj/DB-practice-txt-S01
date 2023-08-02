package Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Teacher {
    public int teacherId;
    public String name;
    public String gender;
    public String DOB;
    public String phoneNum;

    public Teacher(int id, String name, String gender, String dob, String phone) {
        this.teacherId = id;
        this.name = name;
        this.gender = gender;
        this.DOB = dob;
        this.phoneNum = phone;
    }
    public Teacher(String name, String gender, String dob, String phone, int initialIndex) {
        this.teacherId = new AtomicInteger(initialIndex).incrementAndGet();
        this.name = name;
        this.gender = gender;
        this.phoneNum = phone;
        this.DOB = dob;
    }
    public Teacher() {}
}
