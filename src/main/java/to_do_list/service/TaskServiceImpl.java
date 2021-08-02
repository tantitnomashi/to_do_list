package to_do_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import to_do_list.common.BusinessException;
import to_do_list.repository.TaskDAO;
import to_do_list.entity.Task;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;


    @Transactional
    @Override
    public List<Task> findAll() {


        List<Task> list = taskDAO.findAll();
        if (list != null) {
            throw new BusinessException("400", "List task empty");
        }
        return list;

    }

    @Transactional
    @Override
    public Task getTaskById(int id) {

        Task task = taskDAO.getTaskById(id);
        System.out.println(task);
        if (task == null) {
            throw new BusinessException("400", "Can't find task");
        }
        return task;

    }

    @Transactional
    @Override
    public List<Task> getIncompleteTasks() {

        return taskDAO.getIncompleteTasks();

    }

    @Transactional
    @Override
    public List<Task> getCompletedTasks() {

        return taskDAO.getCompletedTasks();
    }

    @Transactional
    @Override
    public void saveTask(Task task) {

        taskDAO.saveTask(task);
    }

    @Transactional
    @Override
    public void updateTask(Task task) {

        taskDAO.updateTask(task);

    }

    @Transactional
    @Override
    public void deleteTaskById(int id) {

        taskDAO.deleteTaskById(id);
    }
}
