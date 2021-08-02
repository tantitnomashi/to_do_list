package to_do_list.repository;

import to_do_list.entity.Task;

import java.util.List;


public interface TaskDAO {
    List<Task> findAll();

    Task getTaskById(int id);

    List<Task> getIncompleteTasks();

    List<Task> getCompletedTasks();

    boolean saveTask(Task task);

    boolean updateTask(Task task);

    boolean updateTaskStatus(int id, int status);

    boolean deleteTaskById(int id);
}
