package peaksoft.dao;

import peaksoft.model.Task;

import java.util.List;

public interface TaskImpl {
    void saveTask(Long lessonId,Task task);

    void updateTask(Long id, Task task);

    List<Task> getAllTaskByLessonId(Long lessonId);

    void deleteTaskById(Long id);
}
