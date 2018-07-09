package ua.pp.sola.stackoverfloo.entities;

public class UserEntity {

    private long userId;
    private String userName;
    private String userPas;
    private UserRole role;
    private boolean isActive;

    public UserEntity() {
    }

    public UserEntity(long userId, String userName, String userPas, UserRole role, boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.userPas = userPas;
        this.role = role;
        this.isActive = isActive;
    }

    public UserEntity(String userName, String userPas, UserRole userRole, boolean isActive) {
        this.userName = userName;
        this.userPas = userPas;
        this.role = userRole;
        this.isActive = isActive;
    }

    public long getUserId() {
        return userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPas() {
        return userPas;
    }

    public void setUserPas(String userPas) {
        this.userPas = userPas;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }



}
