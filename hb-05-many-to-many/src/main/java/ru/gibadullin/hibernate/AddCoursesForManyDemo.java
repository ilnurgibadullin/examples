package ru.gibadullin.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gibadullin.hibernate.entity.*;

public class AddCoursesForManyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();

            int studentId = 2;

            Student tempStudent = session.get(Student.class, studentId);

            System.out.println(tempStudent);
            System.out.println(tempStudent.getCourses());

            Course course1 = new Course("Dota2");
            Course course2 = new Course("WOW");

            course1.addStudent(tempStudent);
            course2.addStudent(tempStudent);

            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
