package com.example.springboottodoapp.repository;

import com.example.springboottodoapp.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
