package peaksoft.dao;

import peaksoft.model.Instructor;

import java.util.List;

public interface InstructorImpl {
    void saveInstructor(Instructor instructor);

    void updateInstructor(Long id, Instructor instructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getInstructorsByCourseId(Long courseId);

    void deleteInstructorById(Long id);

    void assignInstructorToCourse(Long instructorId, Long courseId);

}
