package peaksoft.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.dao.InstructorImpl;
import peaksoft.db.DbConnection;
import peaksoft.model.Course;
import peaksoft.model.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorDao implements InstructorImpl {
    private final SessionFactory sessionFactory = DbConnection.creatSessionFactory();

    @Override
    public void saveInstructor(Instructor instructor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(instructor);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor instructor1 = session.get(Instructor.class, id);
            if (instructor1 != null) {
                instructor1.setFirsName(instructor.getFirsName());
                instructor1.setLastName(instructor.getLastName());
                instructor1.setEmail(instructor.getEmail());
                instructor1.setPhoneNumber(instructor.getPhoneNumber());
                session.saveOrUpdate(instructor1);
                session.getTransaction().commit();
                session.close();
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            session.close();
            return instructor;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long courseId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
           List <Course> course = session.createQuery("select c from  Course  c where  c.id = :id", Course.class )
                   .setParameter("id",courseId).list();

           Course course1 = course.get(0);
            List<Instructor> instructors = new ArrayList<>(course1.getInstructors());
            session.getTransaction().commit();
            return instructors;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteInstructorById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor instructor = session.find(Instructor.class, id);
            session.remove(instructor);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignInstructorToCourse(Long instructor_id, Long course_id) {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructor_id);
            Course course = session.get(Course.class, course_id);
            instructor.getCourse().add(course);
            course.getInstructors().add(instructor);
            session.saveOrUpdate(instructor);
            session.saveOrUpdate(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }
    }

