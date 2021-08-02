package to_do_list.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to_do_list.common.BusinessException;
import to_do_list.common.TASK_STATUS_CODE;
import to_do_list.common.Utils;
import to_do_list.entity.Task;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
public class TaskDAOImpl implements TaskDAO {



    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Task> findAll() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Task> query = currentSession.createQuery("from Task t ORDER BY t.updatedAt DESC", Task.class);
            List<Task> list = query.getResultList();
            if (list != null) {
                return list;
            }
        } catch (Exception e) {
            throw e;
        }

        return null;
    }

    @Override
    public List<Task> getIncompleteTasks() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Task> query = currentSession.createQuery("from Task t where t.status = :status  ORDER BY t.updatedAt DESC", Task.class);
            query.setParameter("status", TASK_STATUS_CODE.UNCOMPLETE.getNumVal());
            List<Task> list = query.getResultList();
            return list;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Task> getCompletedTasks() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Task> query = currentSession.createQuery("from Task t where t.status = :status  ORDER BY t.updatedAt DESC", Task.class);
            query.setParameter("status", TASK_STATUS_CODE.COMPLETED.getNumVal());
            List<Task> list = query.getResultList();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public Task getTaskById(int id) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Task task = currentSession.get(Task.class, id);
            return task;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean saveTask(Task task) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            //update current time
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            formatter.format(date);

            task.setStatus(TASK_STATUS_CODE.UNCOMPLETE.getNumVal());
            task.setUpdatedAt(date);
            task.setCreatedAt(date);
            int id = (int) currentSession.save(task);
            return id > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean updateTask(Task task) {
        try {

            Session currentSession = entityManager.unwrap(Session.class);
            //update current time
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            formatter.format(date);

            task.setUpdatedAt(date);
            //update entity
             currentSession.update(task);
             return true;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public boolean updateTaskStatus(int id, int status) {
        try {

            Session currentSession = entityManager.unwrap(Session.class);
            //update current time
            Task task = currentSession.get(Task.class, id);
            task.setUpdatedAt(Utils.getCurrentDateTime());
            task.setStatus(status);
            //update entity
            currentSession.update(task);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }


    @Override
    public boolean deleteTaskById(int id) {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Task task = currentSession.get(Task.class, id);
            currentSession.delete(task);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
