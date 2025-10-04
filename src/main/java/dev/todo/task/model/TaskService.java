package dev.todo.task.model;

import dev.todo.task.Status;
import dev.todo.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    void createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> getTaskByStatus(Status status);
    boolean updateTask(Long id, Task task);
    boolean deleteTask(Long id);
}
