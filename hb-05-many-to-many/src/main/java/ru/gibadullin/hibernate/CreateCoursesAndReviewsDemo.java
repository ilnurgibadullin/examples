package ru.gibadullin.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gibadullin.hibernate.entity.Course;
import ru.gibadullin.hibernate.entity.Instructor;
import ru.gibadullin.hibernate.entity.InstructorDetail;
import ru.gibadullin.hibernate.entity.Review;

public class CreateCoursesAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();

            Course tempCourse = new Course("Pacman - How To Score One Million Points");

            tempCourse.addReview(new Review("Great course... loved it!"));
            tempCourse.addReview(new Review("Y r an idiot"));
            tempCourse.addReview(new Review("Die"));

            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
