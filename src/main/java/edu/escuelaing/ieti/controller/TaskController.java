package edu.escuelaing.ieti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.ieti.dto.TaskDto;
import edu.escuelaing.ieti.entities.Task;
import edu.escuelaing.ieti.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> tasks = new ArrayList<>();
        taskService.getAll().forEach((task) -> tasks.add(task.toDTO()));
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable String id) {
        Task task = taskService.findById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task.toDTO());
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) {
        Task task = taskService.create(taskDto.toEntity());
        if (task == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(task.toDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@RequestBody TaskDto task, @PathVariable String id) {
        return ResponseEntity.ok(taskService.update(task.toEntity(), id).toDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        taskService.deleteById(id);
        return ResponseEntity.ok(taskService.findById(id) == null);
    }
    
}
