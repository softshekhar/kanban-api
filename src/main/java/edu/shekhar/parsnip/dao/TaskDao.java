package edu.shekhar.parsnip.dao;

import edu.shekhar.parsnip.entity.Task;

import java.util.Collection;

public interface TaskDao {
    Collection<Task> getAllTasks();

    Task getTaskById(int id);

    void removeTaskById(int id);

    Task updateTask(Task task);

    Task insertTask(Task task);
}
