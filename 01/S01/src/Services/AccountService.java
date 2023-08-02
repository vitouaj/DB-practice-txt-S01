package Services;

import java.util.Scanner;

import Models.Account;
import Repository.AccountRepository;
import Utils.Menu;

public class AccountService {
    private AccountRepository accountRepository = new AccountRepository();
    private static int highestAccountIndex;

    public void manageAccount() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.accountMenu();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Enter Entity Id: ");
                    int entityId = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Enter PhoneNumber: ");
                    String phoneNumber = scanner.nextLine();

                    highestAccountIndex = accountRepository.getHighestIndex();
                    
                    accountRepository.createAccount(entityId, username, password, phoneNumber, highestAccountIndex);
                    break;
                case "b":
                    System.out.print("Enter Account Id: ");
                    int id = scanner.nextInt();

                    Account txtAccount = accountRepository.getAccountById(id);

                    if (txtAccount == null) {
                        System.out.println("Can't Find");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\nSearch Found...\n" + txtAccount.accountId + ", " + txtAccount.entityId + ", " + txtAccount.username + ", " + txtAccount.password + ", " + txtAccount.phoneNumber);
                    scanner.nextLine();
                    break;
                case "c":
                    System.out.print("Enter Account Id: ");
                    int aId = scanner.nextInt();
                    System.out.print("Enter Entity Id: ");
                    int eId = scanner.nextInt();
                    System.out.print("Enter Username: ");
                    scanner.nextLine();
                    String aUsername = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String aPassword = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String aPhonenumber = scanner.nextLine();


                    accountRepository.updateAccountById(aId, eId, aUsername, aPassword, aPhonenumber);
                    scanner.nextLine();
                    break;
                case "d":
                    System.out.print("Enter Account Id: ");
                    int fdId = scanner.nextInt();
                    accountRepository.deleteAccount(fdId);
                    highestAccountIndex++;
                    scanner.nextLine();
                    break;
                case "e":
                    run = false;
                    break;
            }
        }

    }

    public void createAccount() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);

        while (run) {
            Menu.createAccount();
            System.out.print("Command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    System.out.print("Enter Teacher Id: ");
                    int entityId = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Enter PhoneNumber: ");
                    String phoneNumber = scanner.nextLine();

                    highestAccountIndex = accountRepository.getHighestIndex();
                    
                    accountRepository.createAccount(entityId, username, password, phoneNumber, highestAccountIndex);
                    break;
                case "b":
                    System.out.print("Enter Student Id: ");
                    int entityIdd = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Enter Username: ");
                    String usernamee = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String passwordd = scanner.nextLine();

                    System.out.print("Enter PhoneNumber: ");
                    String phoneNumberr = scanner.nextLine();

                    highestAccountIndex = accountRepository.getHighestIndex();
                    
                    accountRepository.createAccount(entityIdd, usernamee, passwordd, phoneNumberr, highestAccountIndex);
                    break;
            }
        }
    }
}
