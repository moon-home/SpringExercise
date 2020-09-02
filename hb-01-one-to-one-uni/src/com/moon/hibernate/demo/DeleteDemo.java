package com.moon.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moon.hibernate.entity.Instructor;
import com.moon.hibernate.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
 			session.beginTransaction();
 			int theId = 1;
 			Instructor tempInstructor = session.get(Instructor.class, theId);
 			System.out.println("Found instructor: " + tempInstructor);
 			if (tempInstructor != null) {
 				System.out.println("Deleting: " + tempInstructor);
 				session.delete(tempInstructor); //also delete associated "detail"  object
 			}
			session.getTransaction().commit();
			System.out.println("Done!!");
		}finally {
			factory.close();
		}

	}

}
