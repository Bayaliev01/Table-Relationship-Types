package peaksoft.service;

import peaksoft.dao.TaskImpl;
import peaksoft.dao.impl.TaskDao;
import peaksoft.model.Task;

import java.util.List;

public class TaskService implements TaskImpl {
    TaskDao taskDao = new TaskDao();

    @Override
    public void saveTask(Long lessonId, Task task) {
        try {
            taskDao.saveTask(lessonId, task);
            System.out.println("задача с именем " + task.getName() + "успешна сохранена ");
        } catch (Exception e) {
            System.out.println("Задача не сохранена");
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try {
            taskDao.updateTask(id, task);
            System.out.println("задача с именем " + task.getName() + " успешна обновлена");
        } catch (Exception e) {
            System.out.println("Задача не обновлена");
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        try {
            if (taskDao.getAllTaskByLessonId(lessonId).size() != 0) {
                return taskDao.getAllTaskByLessonId(lessonId);
            } else {
                System.out.println(" There is no such tasks");
            }
        } catch (Exception e) {
            System.out.println("Tasks not found!");
        }
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        try {
            taskDao.deleteTaskById(id);
        } catch (Exception e) {
            System.out.println("Task wasn't deleted!");
        }
    }
}
