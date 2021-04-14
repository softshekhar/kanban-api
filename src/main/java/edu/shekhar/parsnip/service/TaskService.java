package edu.shekhar.parsnip.service;

import edu.shekhar.parsnip.dao.TaskDao;
import edu.shekhar.parsnip.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskService {

    @Autowired
    @Qualifier("fakeData")
    private TaskDao taskDao;

    public Collection<Task> getAllTasks(){
        return this.taskDao.getAllTasks();
    }

    public Task getTaskById(int id){
        return this.taskDao.getTaskById(id);
    }


    public void removeTaskById(int id) {
        this.taskDao.removeTaskById(id);
    }

    public Task updateTask(Task task){
        return this.taskDao.updateTask(task);
    }

    public Task insertTask(Task task) {
        return this.taskDao.insertTask(task);
    }
}
