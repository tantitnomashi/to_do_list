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
        if (list == null) {
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
        List<Task> list = taskDAO.getIncompleteTasks();
        if (list != null) {
            throw new BusinessException("400", "List task empty");
        }
        return list;
    }

    @Transactional
    @Override
    public List<Task> getCompletedTasks() {
        List<Task> list = taskDAO.getCompletedTasks();
        if (list != null) {
            throw new BusinessException("400", "List task empty");
        }
        return list;
    }

    @Transactional
    @Override
    public void saveTask(Task task) {
        boolean isSuccess = taskDAO.saveTask(task);
        if(!isSuccess){
            throw new BusinessException("400", "Save Task fail");
        }
    }

    @Transactional
    @Override
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    @Transactional
    @Override
    public void updateTaskStatus(int id, int status) {
        taskDAO.updateTaskStatus(id, status);
    }

    @Transactional
    @Override
    public void deleteTaskById(int id) {
        taskDAO.deleteTaskById(id);
    }
}
