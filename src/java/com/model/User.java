package com.model;
// Generated Oct 26, 2020 12:55:57 PM by Hibernate Tools 4.3.1



/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer userId;
     private String userEmail;
     private String userUsername;
     private String userPassword;

    public User() {
    }

    public User(String userEmail, String userUsername, String userPassword) {
       this.userEmail = userEmail;
       this.userUsername = userUsername;
       this.userPassword = userPassword;
    }
   
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserUsername() {
        return this.userUsername;
    }
    
    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }




}


