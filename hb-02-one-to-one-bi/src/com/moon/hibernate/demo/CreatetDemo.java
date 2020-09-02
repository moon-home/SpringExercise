package com.moon.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moon.hibernate.entity.Instructor;
import com.moon.hibernate.entity.InstructorDetail;

public class CreatetDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
//			1. create the objects
//			2. associate the objects
//			3. start a transaction
//			4. save the instructor (also save instructor_detail because of the cascade)
//			5. commit transaction
			Instructor  tempInstructor = new Instructor("Garret", "Gavy", "gavy@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://github.baby.com", "filming");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			session.beginTransaction();
			System.out.println("Saving intructor" + tempInstructor);
			session.save(tempInstructor);
			session.getTransaction().commit();
			System.out.println("Done!!");
		}finally {
			factory.close();
		}

	}

}
