package peaksoft.service;

import peaksoft.dao.InstructorImpl;
import peaksoft.dao.impl.InstructorDao;
import peaksoft.model.Instructor;

import java.util.List;

public class InstructorService implements InstructorImpl {
    InstructorDao instructorDao = new InstructorDao();

    @Override
    public void saveInstructor(Instructor instructor) {
        try {
            instructorDao.saveInstructor(instructor);
            System.out.println(" Инструктор с именем " + instructor.getFirsName() + " успешно сохранен");
        } catch (Exception e) {
            System.out.println("инструктор не сохранен");
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try {
            instructorDao.updateInstructor(id, instructor);
            System.out.println("Иструктор с именем " + instructor.getFirsName() + " успешно обнавлен");
        } catch (Exception e) {
        }
        System.out.println("Инструктор не был обновлен");
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try {
            if (instructorDao.getInstructorById(id) != null) {
                return instructorDao.getInstructorById(id);
            } else {
                System.out.println("Инструктор не найден!");
            }
        } catch (Exception e) {
            System.out.println("Инструктор не найден!");
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long courseId) {
        try {
            if (instructorDao.getInstructorsByCourseId(courseId).size() != 0) {
                return instructorDao.getInstructorsByCourseId(courseId);
            } else {
                System.out.println("нет таких инструкторов\n");
            }
        } catch (Exception e) {
            System.out.println("Инструкторы не найдены!\n");
        }
        return null;
    }

    @Override
    public void deleteInstructorById(Long id) {
        try {
            instructorDao.deleteInstructorById(id);
            System.out.println("Преподаватель успешно удален\n");
        } catch (Exception e) {
            System.out.println("Инструктор не удален\n");
        }
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        try {
            instructorDao.assignInstructorToCourse(instructorId, courseId);
            System.out.println("Инструктор с id " + instructorId + " успешно назначен");
        } catch (Exception e) {
            System.out.println("Инструктор или Курс не найден в базе данных\n");
        }
    }
}