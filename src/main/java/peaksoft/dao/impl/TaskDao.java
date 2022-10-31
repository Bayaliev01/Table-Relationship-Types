package peaksoft.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.dao.TaskImpl;
import peaksoft.db.DbConnection;
import peaksoft.model.Lesson;
import peaksoft.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDao implements TaskImpl {
    private final SessionFactory sessionFactory = DbConnection.creatSessionFactory();

    @Override
    public void saveTask(Long lessonId,Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lesson lesson = session.find(Lesson.class,lessonId);
            lesson.setTaskList(List.of(task));
            session.save(task);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Task task1 = session.get(Task.class, id);
            if (task1 != null) {
                task1.setName(task.getName());
                task1.setDeadLine(task.getDeadLine());
                task1.setTask(task.getTask());
                session.saveOrUpdate(task1);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Lesson> lessons = session.createQuery("select l from Lesson  l where  l.id = :id ", Lesson.class)
                    .setParameter("id", lessonId).list();

            Lesson lesson = lessons.get(0);
            List<Task> tasks = new ArrayList<>(lesson.getTaskList());
            session.getTransaction().commit();
            session.close();
            return tasks;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteTaskById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.remove(task);
            session.getTransaction().commit();
            session.close();
        }catch (HibernateException e){
            throw new RuntimeException(e);
        }
    }
}
