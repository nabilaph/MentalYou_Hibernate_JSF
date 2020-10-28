/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import com.model.User;
import com.model.Userdet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
/**
 *
 * @author Acer
 */
public class userDAO {
    
    public List<User> logIn(User user) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();

        List<User> list = new ArrayList<>();

        try {
            session.beginTransaction();
            Query qu = session.createQuery("From User U where U.userUsername=:username AND U.userPassword=:password");
            qu.setParameter("username", user.getUserUsername());
            qu.setParameter("password", user.getUserPassword());
            list = qu.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return list;
    }
    
    public void signUp(User newuser) {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {   
            session.beginTransaction();  
            session.merge(newuser);  
            session.flush();  
            System.out.println("Newuser added");  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();
            session.close();
        }     
    }
        
//nampilin profile
// urutannya email, fullname, nickname, birthday,phone,package
    public List < Userdet > userDet()
    {
            Session session = HibernateUtil.getSessionFactory().openSession();
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpSession sessions = (HttpSession) facesContext.getExternalContext().getSession(true);
            String username = (String) sessions.getAttribute("username");
            List < Userdet> userdet= new ArrayList < > ();
            try
            {
                    session.beginTransaction();
                    Query qu = session.createQuery("From Userdet U where U.userUsername=:username"); 
                    qu.setParameter("username", username);
                    userdet= qu.list();
                    session.getTransaction().commit();
            }
            catch (Exception e)
            {
                    e.printStackTrace();
                    session.getTransaction().rollback();
            }
            finally
            {
                    session.close();
            }

            return userdet;
    }
    
        public Userdet getUserDet()
    {
            Session session = HibernateUtil.getSessionFactory().openSession();
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpSession sessions = (HttpSession) facesContext.getExternalContext().getSession(true);
            String username = (String) sessions.getAttribute("username");
            Userdet userdet= new Userdet();
            try
            {
                    session.beginTransaction();
                    Query qu = session.createQuery("From Userdet U where U.userUsername=:username"); 
                    qu.setParameter("username", username);
                    userdet= (Userdet) qu.uniqueResult();
                    session.getTransaction().commit();
            }
            catch (Exception e)
            {
                    e.printStackTrace();
                    session.getTransaction().rollback();
            }
            finally
            {
                    session.close();
            }

            return userdet;
    }

public void updateUserDet(Userdet userdet)
{
	Session session = HibernateUtil.getSessionFactory().openSession();
	try
	{
		session.beginTransaction();
		session.update(userdet);
		session.flush();
		session.getTransaction().commit();
	}
	catch (Exception e)
	{
	e.printStackTrace();
	session.getTransaction().rollback();
	}

	session.close();
}

    public void addUserDet(Userdet userdet)
    {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try
            {
                    session.beginTransaction();
                    session.merge(userdet);
                    session.flush();
                    session.getTransaction().commit();
            }
            catch (Exception e)
            {
                    e.printStackTrace();
                    session.getTransaction().rollback();
            }

            session.close();
    }
}
