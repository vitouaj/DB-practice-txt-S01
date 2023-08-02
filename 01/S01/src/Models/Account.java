package Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    public int accountId;
    public int entityId;
    public String username;
    public String password;
    public String phoneNumber;


    public Account(int accountId, int entityId, String username, String password, String phoneNumber) {
        this.accountId = accountId;
        this.entityId = entityId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public Account(int entityId, String username, String password, String phoneNumber, int initialIndex) {
        this.accountId = new AtomicInteger(initialIndex).incrementAndGet();
        this.entityId = entityId;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
