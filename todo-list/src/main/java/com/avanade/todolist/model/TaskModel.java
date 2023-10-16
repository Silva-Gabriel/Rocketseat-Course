package com.avanade.todolist.model;

import com.avanade.todolist.exceptions.enums.ErrorMessageEnum;
import com.avanade.todolist.exceptions.exception.SizeLimitExceededException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID userId;
    private String description;
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String priority;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    public void setTitle(String title){
        if(title.length() > 50){
            throw new SizeLimitExceededException(ErrorMessageEnum.SIZE_LIMIT_EXCEEDED.getMessage());
        }
        this.title = title;
    }
}
