package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Project;

public class UtilDB {
	static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory != null) {
			return sessionFactory;
		}
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	public static List<Project> listProjects() {
		List<Project> resultList = new ArrayList<Project>();
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> projects = session.createQuery("FROM Project").list();
			for (Iterator<?> iterator = projects.iterator(); iterator.hasNext();) {
				Project project = (Project) iterator.next();
				resultList.add(project);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	
	public static List<Project> listProjects(String keyword) {
		List<Project> resultList = new ArrayList<Project>();
		
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
		tx = session.beginTransaction();
		List<?> projects = session.createQuery("FROM Project").list();
		for (Iterator<?> iterator = projects.iterator(); iterator.hasNext();) {
	        Project project = (Project) iterator.next();
	        if (project.getName().contains(keyword)) {
		          resultList.add(project);
		       }
		    }
		    tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
		    e.printStackTrace();
		} finally {
			session.close();
		}
		return resultList;
	}
	
	public static void createProject(String name, String deadline, String memberList, String organization) {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   session.save(new Project(name, deadline, memberList, organization));
		   tx.commit();
		} catch (HibernateException e) {
		   if (tx != null)
		      tx.rollback();
		   e.printStackTrace();
		} finally {
		   session.close();
		}
   }
}
