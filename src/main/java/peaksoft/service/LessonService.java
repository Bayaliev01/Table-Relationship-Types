package peaksoft.service;

import peaksoft.dao.LessonImpl;
import peaksoft.dao.impl.LessonDao;
import peaksoft.model.Lesson;

import java.util.List;

public class LessonService implements LessonImpl {
    LessonDao lessonDao = new LessonDao();

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        try {
            lessonDao.saveLesson(courseId, lesson);
            System.out.println("Урок с названием " + lesson.getName() + " успешно сохранен");
        } catch (Exception e) {
            System.out.println("Урок не сохранен");
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try {
            lessonDao.updateLesson(id, lesson);
            System.out.println("урок с названием " + lesson.getName() + " успешно обновлен");
        } catch (Exception e) {
            System.out.println("Урок не обновлен");
        }
    }

    @Override
    public Lesson getLessonById(Long id) {
        try {
            if (lessonDao.getLessonById(id) != null) {
                return lessonDao.getLessonById(id);
            } else {
                System.out.println("Урок не найден!");
            }
        } catch (Exception e) {
            System.out.println("Урок не найден!");
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        try {
            if (lessonDao.getLessonsByCourseId(courseId).size() != 0) {
            } else {
                System.out.println("нет таких инструкторов");
            }
        } catch (Exception e) {
            System.out.println("Инструкторы не найдены!");
        }
        return null;
    }
}
