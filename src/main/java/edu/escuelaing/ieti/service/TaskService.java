package edu.escuelaing.ieti.service;

import java.util.List;

import edu.escuelaing.ieti.entities.Task;

public interface TaskService {
    
    Task create(Task task);

    Task findById(String id);

    List<Task> getAll();

    boolean deleteById(String id);

    Task update(Task task, String id);

}
