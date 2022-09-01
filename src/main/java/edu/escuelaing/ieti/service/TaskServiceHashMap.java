package edu.escuelaing.ieti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.entities.Task;

@Service
public class TaskServiceHashMap implements TaskService {
    
    private HashMap<String, Task> taskHash = new HashMap<>();

    @Override
    public Task create(Task task) {
        if (taskHash.containsKey(task.getId())) {
            return null;
        }
        taskHash.put(task.getId(), task);
        return task;
    }

    @Override
    public Task findById(String id) {
        return taskHash.get(id);
    }

    @Override
    public List<Task> getAll() {
        List<Task> resTasks = new ArrayList<>();
        taskHash.values().forEach(resTasks::add);
        return resTasks;
    }

    @Override
    public boolean deleteById(String id) {
        if (taskHash.containsKey(id)) {
            taskHash.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Task update(Task task, String id) {
        if (!taskHash.containsKey(id)) {
            return null;
        }
        deleteById(id);
        return create(task);
    }

}
