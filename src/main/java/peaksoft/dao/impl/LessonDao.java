package peaksoft.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.dao.LessonImpl;
import peaksoft.db.DbConnection;
import peaksoft.model.Course;
import peaksoft.model.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonDao implements LessonImpl {
    private final SessionFactory sessionFactory = DbConnection.creatSessionFactory();

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course course = session.find(Course.class, courseId);
            course.setLessons(List.of(lesson));
            lesson.setCourse(course);
            session.save(lesson);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lesson lesson1 = session.get(Lesson.class, id);
            if (lesson1 != null) {
                lesson1.setName(lesson.getName());
                lesson1.setVideoLink(lesson.getVideoLink());
                session.saveOrUpdate(lesson1);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Lesson getLessonById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lesson lesson = session.get(Lesson.class, id);
            session.getTransaction().commit();
            session.close();
            return lesson;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course  c where c.id = :id", Course.class).
                    setParameter("id", courseId).list();

            Course course = courses.get(0);
            List<Lesson> lessons = new ArrayList<>(course.getLessons());
            session.getTransaction().commit();
            session.close();
            return lessons;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
