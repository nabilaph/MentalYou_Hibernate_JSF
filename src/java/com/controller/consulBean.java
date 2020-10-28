/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.consulDetDAO;
import com.dao.userDAO;
import com.model.ConsulDet;
import com.model.User;
import com.model.Userdet;
import java.util.List;
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
public class consulBean {
    
    private User newuser = new User();
    private consulDetDAO consulDao = new consulDetDAO();
    private userBean usr = new userBean();
    private Userdet userdet = new Userdet();
    private ConsulDet consul = new ConsulDet();
    /**
     * Creates a new instance of consulBean
     */
    public consulBean() {
    }

    private List< ConsulDet > consulDetList;
    ConsulDet consuldet = new ConsulDet();

    public List < ConsulDet > getConsulDet() {
        consulDetList = consulDao.consulDet();
        return consulDetList;
    }
    
   public void addConsulDet() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");
        newuser.setUserUsername("username");
       //consuldet.setUser(newuser);
        consulDao.addConsulDet(consuldet);
        System.out.println("Consul Detail successfully added.");
    }

    public void deleteConsulDet(ConsulDet consuldet) {
        int id = consuldet.getConsulId();
        consulDao.delete(consuldet);
        System.out.println("Consul Detail successfully deleted.");
    }
    
    public void getUserConsulDet(Userdet userdet, ConsulDet consul){
       
      usr.addUserDet();
      addConsulDet();
}
    
    public userBean getUsr() {
        return usr;
    }

        public void setUsr(userBean usr) {
        this.usr = usr;
    }
        
    public Userdet getUserdet() {
        return userdet;
    }

        public void setUserdet(Userdet userdet) {
        this.userdet = userdet;
    }
        
    public ConsulDet getConsul() {
        return consul;
    }

        public void setConsul(ConsulDet consul) {
        this.consul = consul;
    }

}
