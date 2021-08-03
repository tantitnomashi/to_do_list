package to_do_list.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import to_do_list.common.BusinessException;
import to_do_list.entity.Task;
import to_do_list.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired   //DI
    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTask(){

        try {
           return ResponseEntity.ok(taskService.findAll());
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id){

        try {
           return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
        }catch (Exception e){
            System.out.println("err in controller");
            throw e;
        }

    }

    @GetMapping("/tasks/incomplete-tasks")
    public ResponseEntity<?> getIncompleteTask(){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getIncompleteTasks());
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("/tasks/completed-tasks")
    public ResponseEntity<?> getCompletedTask(){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getCompletedTasks());
        }catch (Exception e){

            throw e;
        }
    }

    @PostMapping("/tasks")
        public ResponseEntity<?> addTask(@RequestBody Task task){

        try {
            taskService.saveTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }catch (Exception e){
            throw e;
        }
    }


    @PutMapping("/tasks")
    public ResponseEntity<?> updateTask(@RequestBody Task task){
        try {
            taskService.updateTask(task);
           return ResponseEntity.status(HttpStatus.OK).body(task);
        }catch (Exception e){
            throw e;
        }
    }
    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTaskStatus(@PathVariable int id, @RequestParam int status){

        try {
            taskService.updateTaskStatus(id, status);
           return ResponseEntity.status(HttpStatus.OK).body("Updated");
        }catch (Exception e){
            throw e;
        }
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){

        try {
            taskService.deleteTaskById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }catch (Exception e){
            throw e;
        }
    }


}
