package edu.shekhar.parsnip.dao;

import edu.shekhar.parsnip.entity.Status;
import edu.shekhar.parsnip.entity.Task;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeTaskDaoImpl implements TaskDao {

    private static Map<Integer, Task> tasks;
    private static Integer keyId;

    static {

        keyId = 0;
        tasks = new HashMap<Integer, Task>(){

            {
                put(++keyId, new Task(keyId, "Said", "hello cs", Status.IN_PROGRESS));
                put(++keyId, new Task(keyId, "Alex U", "hello finance", Status.UN_STARTED));
                put(++keyId, new Task(keyId, "Anna", "hello maths", Status.COMPLETED));
            }
        };
    }

    @Override
    public Collection<Task> getAllTasks(){
        return this.tasks.values();
    }

    @Override
    public Task getTaskById(int id){
        return this.tasks.get(Integer.valueOf(id));
    }

    @Override
    public void removeTaskById(int id) {
        this.tasks.remove(Integer.valueOf(id));
    }

    @Override
    public Task updateTask(Task task){
        Task s = tasks.get(Integer.valueOf(task.getId()));
        s.setTitle(task.getTitle());
        s.setStatus(task.getStatus());
        s.setDescription(task.getDescription());
        return s;
    }

    @Override
    public Task insertTask(Task task) {
        task.setId(++keyId);
        this.tasks.put(keyId, task);
        return task;
    }
}
