package com.moon.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moon.hibernate.entity.Instructor;
import com.moon.hibernate.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
 			session.beginTransaction();
 			int theId = 3;
 			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
 			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
 			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
 			// remove the associated object reference, break bi0directional link
 			tempInstructorDetail.getInstructor().setInstructorDetail(null);
 			session.delete(tempInstructorDetail);
 			session.getTransaction().commit();
			System.out.println("Done!!");
		}catch(Exception exc){
			exc.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
