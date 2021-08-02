package to_do_list.repository;

import to_do_list.entity.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> findAll();

    Task getTaskById(int id);

    List<Task> getIncompleteTasks();

    List<Task> getCompletedTasks();

    void saveTask(Task task);

    void updateTask(Task task);

    void deleteTaskById(int id);
}