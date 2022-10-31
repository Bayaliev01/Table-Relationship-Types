package peaksoft.dao.impl;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.dao.CourseImpl;
import peaksoft.db.DbConnection;
import peaksoft.model.Course;
import peaksoft.model.Instructor;

import java.util.List;

public class CourseDao implements CourseImpl {
    SessionFactory sessionFactory = DbConnection.creatSessionFactory();

    @Override
    public void saveCourse(Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            session.close();
            return course;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> getAllCourse() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course  c").getResultList();
            session.getTransaction().commit();
            session.close();
            return courses;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Course course1 = session.find(Course.class, id);
            if (course1 != null) {
                course1.setCourseName(course.getCourseName());
                course1.setDuration(course1.getDuration());
                course1.setCreateAt(course.getCreateAt());
                course1.setImageLink(course.getImageLink());
                course1.setDescription(course1.getDescription());
                session.merge(course1);
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Course course = session.get(Course.class, id);

            for (Instructor i: course.getInstructors()) {
                i.setCourse(null);
            }

            session.delete(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public Course getCourseByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course  c").getResultList();
            session.getTransaction().commit();
            session.close();
            for (Course course : courses) {
                if (course.getCourseName().equals(name)) {
                    return course;
                }
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
