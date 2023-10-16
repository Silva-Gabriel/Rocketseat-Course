package com.avanade.todolist.controller;

import com.avanade.todolist.exceptions.enums.ErrorMessageEnum;
import com.avanade.todolist.exceptions.exception.EndDateIsBeforeStartDate;
import com.avanade.todolist.exceptions.exception.EntityNotFoundException;
import com.avanade.todolist.exceptions.exception.StartDateIsAfterCurrentDate;
import com.avanade.todolist.exceptions.exception.UnauthorizedUser;
import com.avanade.todolist.model.TaskModel;
import com.avanade.todolist.repository.ITaskRepository;
import com.avanade.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskRepository repository;

    @PostMapping
    public ResponseEntity<TaskModel> create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt())){
            throw new StartDateIsAfterCurrentDate(ErrorMessageEnum.START_DATE_IS_AFTER_CURRENT_DATE.getMessage());
        }
        if(taskModel.getEndAt().isBefore(taskModel.getStartAt())){
            throw new EndDateIsBeforeStartDate(ErrorMessageEnum.END_DATE_IS_BEFORE_START_DATE.getMessage());
        }

        var task = repository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllByUser(HttpServletRequest request){
        var userId = request.getAttribute("userId");
        var tasks = repository.findByUserId((UUID) userId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("all")
    public ResponseEntity<List<TaskModel>> getAll(){
       var allTasks = repository.findAll();
       return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskModel> update(@RequestBody TaskModel taskModel, @PathVariable("id") UUID id){
        var task = repository.findById(id).orElse(null);
        if(task == null)
           throw new EntityNotFoundException(ErrorMessageEnum.ENTITY_NOT_FOUND.getMessage());

        if(!task.getUserId().equals(id)){
            throw new UnauthorizedUser(ErrorMessageEnum.USER_NOT_AUTHORIZED.getMessage());
        }

        Utils.copyNonNullProperties(taskModel, task);
        var updatedTask = repository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }
}
