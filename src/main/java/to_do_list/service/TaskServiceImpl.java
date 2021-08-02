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
        try {
            return taskDAO.findAll();
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }

    }

    @Transactional
    @Override
    public Task getTaskById(int id) {
        try {
            return taskDAO.getTaskById(id);
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }
    }

    @Transactional
    @Override
    public List<Task> getIncompleteTasks() {
        try {
            return taskDAO.getIncompleteTasks();
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }
    }

    @Transactional
    @Override
    public List<Task> getCompletedTasks() {

        try {
            return  taskDAO.getCompletedTasks();
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void saveTask(Task task) {
        try {
            taskDAO.saveTask(task);
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }

    }

    @Transactional
    @Override
    public void updateTask(Task task) {

        try {
            taskDAO.updateTask(task);
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void updateTaskStatus(int id, int status) {

        try {
            taskDAO.updateTaskStatus(id, status);
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }
    }

    @Transactional
    @Override
    public void deleteTaskById(int id) {

        try {
            taskDAO.deleteTaskById(id);
        } catch (Exception e) {
            BusinessException.handleBussinessException(e);
            throw e;
        }
    }

}
