package to_do_list.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to_do_list.common.TaskStatusCode;
import to_do_list.common.Utils;
import to_do_list.entity.Task;

import javax.persistence.EntityManager;
import java.util.Date;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Task> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("Select t from Task t ORDER BY t.updatedAt DESC", Task.class);
        List<Task> list = query.getResultList();
        if (list != null) {
            return list;
        }

        return null;
    }

    @Override
    public List<Task> getIncompleteTasks() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("Select t from Task t where t.status = :status  ORDER BY t.updatedAt DESC", Task.class);
        query.setParameter("status", TaskStatusCode.INCOMPLETE.getNumVal());
        List<Task> list = query.getResultList();
        return list;

    }

    @Override
    public List<Task> getCompletedTasks() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Task> query = currentSession.createQuery("Select t from Task t where t.status = :status  ORDER BY t.updatedAt DESC", Task.class);
        query.setParameter("status", TaskStatusCode.COMPLETED.getNumVal());
        List<Task> list = query.getResultList();
        return list;

    }

    @Override
    public Task getTaskById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);
        Task task = currentSession.get(Task.class, id);
        return task;

    }

    @Override
    public void saveTask(Task task) {

        Session currentSession = entityManager.unwrap(Session.class);
        //update current time
        Date date = Utils.getCurrentDateTime();
        task.setStatus(TaskStatusCode.INCOMPLETE.getNumVal());
        task.setUpdatedAt(date);
        task.setCreatedAt(date);
        currentSession.save(task);


    }

    @Override
    public void updateTask(Task task) {

        Session currentSession = entityManager.unwrap(Session.class);
        //update current time
        Date date = Utils.getCurrentDateTime();
        task.setUpdatedAt(date);
        //update entity
        currentSession.update(task);


    }

    @Override
    public void updateTaskStatus(int id, int status) {
        Session currentSession = entityManager.unwrap(Session.class);
        //update current time
        Task task = currentSession.get(Task.class, id);
        task.setUpdatedAt(Utils.getCurrentDateTime());
        if(status == TaskStatusCode.COMPLETED.getNumVal() || status ==  TaskStatusCode.INCOMPLETE.getNumVal()){
            task.setStatus(status);
            //update entity
            currentSession.update(task);
        }else{
            throw new RuntimeException("Invalid Params Format");
        }


    }


    @Override
    public void deleteTaskById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Task task = currentSession.get(Task.class, id);
        currentSession.delete(task);
    }
}
