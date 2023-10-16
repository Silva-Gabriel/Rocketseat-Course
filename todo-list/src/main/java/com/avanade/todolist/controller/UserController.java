package com.avanade.todolist.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.avanade.todolist.exceptions.enums.ErrorMessageEnum;
import com.avanade.todolist.exceptions.exception.UsernameAlreadyExists;
import com.avanade.todolist.model.UserModel;
import com.avanade.todolist.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserRepository repository;

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel){
        var user = repository.findByUsername(userModel.getUsername());
        if(user != null)
            throw new UsernameAlreadyExists(ErrorMessageEnum.USERNAME_ALREADY_EXISTS.getMessage());

        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = repository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll(){
        var users = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
