package ru.gibadullin.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gibadullin.hibernate.entity.*;

public class CreateCoursesAndStudentsDemo {
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

            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            System.out.println("\nSaving the course...");

            session.save(tempCourse);

            System.out.println("Saved the course: " + tempCourse);

            Student student1 = new Student("Vladimir", "Doe", "vladimir@gmail.com");
            Student student2 = new Student("Vasya", "Qoe", "vasya@gmail.com");

            tempCourse.addStudent(student1);
            tempCourse.addStudent(student2);

            System.out.println("\nSaving students...");
            session.save(student1);
            session.save(student2);
            System.out.println("\nSaved students: " + tempCourse.getStudents());
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
