package edu.shekhar.parsnip.controller;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.shekhar.parsnip.entity.Task;
import edu.shekhar.parsnip.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Task> getAllTasks(){
        try {
            Thread.sleep(1000); // to show loading page to user
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return taskService.getAllTasks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task getTaskById(@PathVariable("id") int id){
        return taskService.getTaskById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTaskById(@PathVariable("id") int id){
        taskService.removeTaskById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task updateTaskById(@PathVariable("id") int id, @RequestBody Task task){
        if( id == task.getId()) {
            task = taskService.updateTask(task);
        }
        return task;
    }


    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTaskById(@RequestBody Task task){
        taskService.updateTask(task);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task insertTask(@RequestBody Task task){
        return taskService.insertTask(task);
    }


    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
}
