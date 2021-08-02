package to_do_list.service;

import to_do_list.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task getTaskById(int id);

    List<Task> getIncompleteTasks();

    List<Task> getCompletedTasks();

    void saveTask(Task task);

    void updateTask(Task task);

    void deleteTaskById(int id);
}
