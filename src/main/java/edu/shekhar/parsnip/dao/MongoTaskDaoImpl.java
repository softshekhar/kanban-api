package edu.shekhar.parsnip.dao;

import edu.shekhar.parsnip.entity.Status;
import edu.shekhar.parsnip.entity.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoTaskDaoImpl implements TaskDao {


    @Override
    public Collection<Task> getAllTasks() {
        return new ArrayList<Task>(){
            {
                add(new Task(1, "Mario", "Nothing at all", Status.IN_PROGRESS));
            }
        };
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public void removeTaskById(int id) {

    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public Task insertTask(Task task) {
        return null;
    }
}
