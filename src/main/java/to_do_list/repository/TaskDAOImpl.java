package to_do_list.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import to_do_list.entity.Task;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
public class TaskDAOImpl implements TaskDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Task> findAll() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Task> query = currentSession.createQuery("from Task t ORDER BY t.updatedAt DESC", Task.class);
            List<Task> list =   query.getResultList();
            return list;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public List<Task> getIncompleteTasks() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Task> query = currentSession.createQuery("from Task t where t.status = :status  ORDER BY t.updatedAt DESC", Task.class);
            query.setParameter("status", 0);
            List<Task> list =   query.getResultList();
            return list;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<Task> getCompletedTasks() {
        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Query<Task> query = currentSession.createQuery("from Task t where t.status = :status  ORDER BY t.updatedAt DESC", Task.class);
            query.setParameter("status", 1);
            List<Task> list =  query.getResultList();
            return list;
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public Task getTaskById(int id) {
        try{
        Session currentSession = entityManager.unwrap(Session.class);
        Task task = currentSession.get(Task.class, id);
        return task;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void saveTask(Task task) {
        try{
        Session currentSession = entityManager.unwrap(Session.class);
            //update current time
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            formatter.format(date);

            task.setUpdatedAt(date);
            task.setCreatedAt(date);
        int id = (int)  currentSession.save(task);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void updateTask(Task task) {
        try{

        Session currentSession = entityManager.unwrap(Session.class);
        //update current time
        Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            formatter.format(date);
            System.out.println(date.toString());
            task.setUpdatedAt(date);
        //update
        currentSession.saveOrUpdate(task);
        }catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }


    @Override
    public void deleteTaskById(int id) {
        try{
        Session currentSession = entityManager.unwrap(Session.class);
        Task task = currentSession.get(Task.class, id);
        currentSession.delete(task);
        }catch (Exception e){
            throw e;
        }
    }
}
