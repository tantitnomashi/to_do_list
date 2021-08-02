package to_do_list.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Get All list Fail");
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id){

        try {
           return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Get Fail");
        }
    }

    @GetMapping("/tasks/incompleteTasks")
    public ResponseEntity<?> getIncompleteTask(){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getIncompleteTasks());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Get Fail");
        }
    }

    @GetMapping("/tasks/completedTasks")
    public ResponseEntity<?> getCompletedTask(){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getCompletedTasks());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Get Fail");
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestBody Task task){

        try {
            taskService.saveTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Save Fail");
        }
    }

    @PutMapping("/tasks")
    public ResponseEntity<?> updateTask(@RequestBody Task task){

        try {
            taskService.updateTask(task);
           return ResponseEntity.status(HttpStatus.OK).body(task);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Update Fail");
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){

        try {
            taskService.deleteTaskById(id);
           return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Delete Fail");
        }
    }


}
