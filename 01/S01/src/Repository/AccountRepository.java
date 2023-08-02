package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Account;
import Models.LoginResponse;

public class AccountRepository {
    public static String fileDir = "E:/Projects/ITE Y2 M4/APL M4/semester2/01 school management/01/Faculty/src/Data/account.txt";

    private StudentRepository studentRepository = new StudentRepository();
    private TeacherRepository teacherRepository = new TeacherRepository();

    public Account getAccountById(int id) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                int accountId = Integer.parseInt(wordList[0]);
                int entityId = Integer.parseInt(wordList[1]);
                String username = wordList[2];
                String password = wordList[3];
                String phoneNumber = wordList[4];

                
                if (Integer.parseInt(wordList[0]) == (id)) {
                    return new Account(accountId, entityId, username, password, phoneNumber);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public void createAccount(int entityId, String username, String password, String phoneNumber, int initialIndex) {
        Account newAccount = new Account(entityId, username, password, phoneNumber, initialIndex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir, true))) {
            writer.write(newAccount.accountId + ", " + newAccount.entityId + ", " + newAccount.username + ", " + newAccount.password + ", " + newAccount.phoneNumber + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void updateAccountById(int accountId, int entityId, String username, String password, String phoneNumber) {
        Account account = getAccountById(accountId);
        if (account == null) {
            System.out.println("Can't find Teacher with given id...");
            return;
        }

        List<String> readLines = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {

            while((line = reader.readLine()) != null) {
                
                String[] wordList = line.split(", ");                
                
                if (Integer.parseInt(wordList[0]) == accountId) {
                    var newLine = accountId + ", " + entityId + ", " + username +", "+ password +", "+ phoneNumber;
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

    public void deleteAccount(int id) {
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


    public LoginResponse login(String username, String password) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDir))) {
            while ((line = reader.readLine()) != null) {
                String[] wordList = line.split(", ");
                
                int entityId = Integer.parseInt(wordList[1]);
                String usernamee = wordList[2];
                String passwordd = wordList[3];
     
                if (usernamee.equals(username) && password.equals(passwordd)) {

                    String entityName = getEntityName(entityId);

                    return new LoginResponse(true, entityId, entityName);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new LoginResponse(false, -1, "");
    }

    public String getEntityName(int entityId) {
        var s1 = studentRepository.getStudentById(entityId);
        var t1 = teacherRepository.getTeacherById(entityId);

        if (t1 == null) {
            return "Student " + s1.name;
        }
        return "Teacher " + t1.name;
    }
}
    
