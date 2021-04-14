package edu.shekhar.parsnip.dao;

import edu.shekhar.parsnip.entity.Status;
import edu.shekhar.parsnip.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("postgresql")
public class PostgresqlTaskDaoImpl extends JdbcDaoSupport implements TaskDao {

    @Autowired DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    private static class TaskRowMapper implements RowMapper<Task> {

        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setStatus(Status.valueOf(resultSet.getString("status")));
            return task;
        }
    }


    @Override
    public Collection<Task> getAllTasks() {
        // SELECT column_name(s) FROM table_name
        final String sql = "SELECT id, title, description, status FROM tasks";
        List<Task> tasks = getJdbcTemplate().query(sql, new TaskRowMapper());
        return tasks;
    }

    @Override
    public Task getTaskById(int id) {
        // SELECT column_name(s) FROM table_name where column = value
        final String sql = "SELECT id, title, description, status FROM tasks where id = ?";
        Task task = getJdbcTemplate().queryForObject(sql, new TaskRowMapper(), Integer.valueOf(id));
        return task;
    }

    @Override
    public void removeTaskById(int id) {
        // DELETE FROM table_name
        // WHERE some_column = some_value
        final String sql = "DELETE FROM tasks WHERE id = ?";
        getJdbcTemplate().update(sql, Integer.valueOf(id));
    }

    @Override
    public Task updateTask(Task task) {
        // UPDATE table_name
        // SET column1=value, column2=value2,...
        // WHERE some_column=some_value
        final String sql = "UPDATE tasks SET title = ?, description = ?, status = ? WHERE id = ?";
        final int id = task.getId();
        final String title = task.getTitle();
        final String description = task.getDescription();
        final Status status = task.getStatus();

        getJdbcTemplate().update(sql, new Object[]{title, description, status, Integer.valueOf(id)});
        return null;
    }

    @Override
    public Task insertTask(Task task) {
        // INSERT INTO table_name (column1, column2, column3,...)
        // VALUES (value1, value2, value3,...)
        final String sql = "INSERT INTO tasks (title, description, status) VALUES (?, ?, ?)";
        final String title = task.getTitle();
        final String description = task.getDescription();
        final Status status = task.getStatus();
        getJdbcTemplate().update(sql, new Object[]{title, description, status});
        return null;
    }
}
