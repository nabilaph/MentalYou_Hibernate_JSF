/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.ConsulDet;
import com.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Acer
 */
public class consulDetDAO {
    //nampilin detail consul
// urutannya date, time, doc, via/payment
public List < ConsulDet> consulDet()
{
	Session session = HibernateUtil.getSessionFactory().openSession();
	FacesContext facesContext = FacesContext.getCurrentInstance();
        
	HttpSession sessions = (HttpSession) facesContext.getExternalContext().getSession(true);
	String username = (String) sessions.getAttribute("username");
	List < ConsulDet> consuldet = new ArrayList < > ();
	try
	{
		session.beginTransaction();
		Query qu = session.createQuery("From ConsulDet U where U.userUsername=:username"); 
		qu.setParameter("username", username);
		consuldet = qu.list();
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
	
	return consuldet;
}

public void addConsulDet(ConsulDet consuldet)
{
	Session session = HibernateUtil.getSessionFactory().openSession();
	try
	{
		session.beginTransaction();
		session.merge(consuldet);
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

public void delete(ConsulDet consuldet) {
        Session session
                = HibernateUtil.getSessionFactory().openSession();
	FacesContext facesContext = FacesContext.getCurrentInstance();
        
	HttpSession sessions = (HttpSession) facesContext.getExternalContext().getSession(true);
	String username = (String) sessions.getAttribute("username");
        try {
            session.beginTransaction();
            Query q = session.createQuery("delete ConsulDet U where U.userUsername=:username");
            q.setParameter("username", username);
            q.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }
    
}
