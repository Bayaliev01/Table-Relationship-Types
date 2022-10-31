package peaksoft.service;

import peaksoft.dao.CourseImpl;
import peaksoft.dao.impl.CourseDao;
import peaksoft.model.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseService implements CourseImpl {

    CourseDao courseDao = new CourseDao();

    @Override
    public void saveCourse(Course course) {
        try {
            courseDao.saveCourse(course);
            System.out.println("Курс с названием " + course.getCourseName() + " успешно сохранен ");
        } catch (Exception e) {
            System.out.println("Курс не сохранен ");
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try {
            if (courseDao.getCourseById(id) != null) {
                return courseDao.getCourseById(id);
            } else {
                System.out.println("Курс не найден");
            }
        } catch (Exception e) {
            System.out.println("Курс не найден ");
        }
        return null;
    }

    @Override
    public List<Course> getAllCourse() {
        try {
            if (courseDao.getAllCourse().size() != 0) {
                return courseDao.getAllCourse();
            } else {
                System.out.println("таких курсов нет");
            }
        } catch (Exception e) {
            System.out.println("Курсы не найдены!");
        }
        return null;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try {
            courseDao.updateCourse(id, course);
        } catch (Exception e) {
            System.out.println("Курсы не обновлялись!");
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try {
            courseDao.deleteCourseById(id);
        } catch (Exception e) {
            System.out.println("Курсы не удалены!");
        }
    }

    @Override
    public Course getCourseByName(String name) {
        try {
            if (courseDao.getCourseByName(name) != null) {
                return courseDao.getCourseByName(name);
            } else {
                System.out.println("Курс с названием " + name + " не найдет");
            }
        } catch (Exception e) {

        }
        return null;
    }
}
