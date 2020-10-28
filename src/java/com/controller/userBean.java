/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import com.dao.userDAO;
import com.model.User;
import com.model.Userdet;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@ManagedBean
@RequestScoped
public class userBean {

    private User newuser = new User();
    private userDAO userDao = new userDAO();
    private String repeatPassword = "";
    private Userdet userdet = new Userdet();
    private Userdet newUserDet = new Userdet();

    public userBean() {
    }

    public void logIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        List<User> login = userDao.logIn(newuser);
        if (login.size() < 1) {
            // Invalid Credentials
            facesContext.addMessage("wrongLogin", new FacesMessage("Wrong username or password!"));
        } else {
            try {
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("username", login.get(0).getUserUsername());
                facesContext.getExternalContext().redirect("home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(userBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void signUp() {
        userDao.signUp(newuser);
    }

    public String checkLoginP() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        try {
            if (session.getAttribute("username") == null) {
                return "login.xhtml";
            } else {
                return "userProfile.xhtml";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "userProfile.xhtml";
    }

    public String checkLoginF() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        try {
            if (session.getAttribute("username") == null) {
                return "login.xhtml";
            } else {
                return "formConsul.xhtml";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "formConsul.xhtml";
    }

    public String logout() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        try {

            session.removeAttribute("username");
            session.invalidate();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return "home.xhtml";
    }

    private List< Userdet> userDetList;

    public List< Userdet> getUserDet() {
        userDetList = userDao.userDet();
        userdet = userDetList.get(0);
        return userDetList;
    }
    
    public void fetchUserDet() {
        this.newUserDet = userDao.getUserDet();
    }

    public Userdet getNewUserDet() {
        return newUserDet;
    }
    
    public void updateUserDetNew(Userdet userDet) {
        userDao.updateUserDet(userDet); 
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("userProfile.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(userBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void UpdateUserDet() {
        userdet = userDetList.get(0);
        userDao.updateUserDet(userdet);
    }

    public void addUserDet() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");
        userdet.setUserUsername(username);
        userDao.addUserDet(userdet);
        System.out.println(username + "User Detail successfully added.");
    }

    public void changeProfile(Userdet userdet) {
        this.newUserDet = userDao.userDet().get(0);
    }

    public User getNewuser() {
        return newuser;
    }

    public void setNewuser(User newuser) {
        this.newuser = newuser;
    }

    public Userdet getUserdet() {
        return userdet;
    }

    public void setUserdet(Userdet userdet) {
        this.userdet = userdet;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

}
