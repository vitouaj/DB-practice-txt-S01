package Utils;

public class Menu {
    public static void facultyMenu() {
        System.out.println("\na. Add new faculty");
        System.out.println("b. Search a faculty by id");
        System.out.println("c. Update a faculty by id");
        System.out.println("d. Delete a faculty by id");
        System.out.println("e. exit manage faculty\n");
    }
    public static void accountMenu() {
        System.out.println("\na. Add new account");
        System.out.println("b. Search a account by id");
        System.out.println("c. Update a account by id");
        System.out.println("d. Delete a account by id");
        System.out.println("e. exit manage account\n");
    }

    public static void departmentMenu() {
        System.out.println("\na. Add new department");
        System.out.println("b. Search a department by id");
        System.out.println("c. Update a department by id");
        System.out.println("d. Delete a department by id");
        System.out.println("e. Display all departments belong to a faculty");
        System.out.println("f. exit manage department\n");
    }

    public static void studentMenuCrud() {
        System.out.println("\na. Add new student");
        System.out.println("b. Search a student by id");
        System.out.println("c. Update a student by id");
        System.out.println("d. Delete a student by id");
        System.out.println("f. exit manage student\n");
    }
    public static void teacherMenu() {
        System.out.println("\na. Add new teacher");
        System.out.println("b. Search a teacher by id");
        System.out.println("c. Update a teacher by id");
        System.out.println("d. Delete a teacher by id");
        System.out.println("e. exit manage teacher\n");
    }

    public static void courseMenu() {
        System.out.println("\na. Add new course");
        System.out.println("b. Search a course by id");
        System.out.println("c. Update a course by id");
        System.out.println("d. Delete a course by id");
        System.out.println("e. exit manage course\n");
    }

    public static void applicationMenu() {
        System.out.println("\n1. Manage Faculty");
        System.out.println("2. Manage Department");
        System.out.println("3. Manage Student");
        System.out.println("4. Enroll Student into Department");
        System.out.println("5. Manage Course");
        System.out.println("6. Manage Teacher");
        System.out.println("7. Assign Course to Teacher");
        System.out.println("8. Manage Account\n");

    }

    public static void createAccount() {
        System.out.println("a. create a teacher account");
        System.out.println("b. create a student account");
    }



    public static void enrollStudent() {
        System.out.println("a. Enroll a student into a department");
        System.out.println("b. Remove a student from a department");
        System.out.println("c. Display all students study at given department");
        System.out.println("d. Display all departments studied by given student");
    }

    public static void assignCourseToTeacher() {
        System.out.println("a. Assign a course to a teacher");
        System.out.println("b. Remove a course from a teacher");
        System.out.println("c. Display all courses taught by a teacher");
    }
}
