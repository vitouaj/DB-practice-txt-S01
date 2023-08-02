package Models;

public class LoginResponse {
    public boolean isLogin;
    public int entityId;
    public String entityName;

    public LoginResponse(boolean isLogin, int entityId, String entityName) {
        this.isLogin = isLogin;
        this.entityId = entityId;
        this.entityName = entityName;
    }
    public LoginResponse() {}
}
