package dev.todo.task.model;

import dev.todo.task.Status;
import dev.todo.task.Task;
import dev.todo.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createTask(Task task) {
        repository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public List<Task> getTaskByStatus(Status status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public boolean updateTask(Long id, Task task) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()){
            Task newTask = optionalTask.get();
            newTask.setDescription(task.getDescription());
            newTask.setStatus(task.getStatus());
            newTask.setCreatedAt(task.getCreatedAt());
            newTask.setUpdatedAt(LocalDate.now());
            repository.save(newTask);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTask(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;

    }

}
