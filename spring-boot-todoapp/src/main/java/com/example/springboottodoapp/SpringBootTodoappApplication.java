package com.example.springboottodoapp;

import com.example.springboottodoapp.models.TodoItem;
import com.example.springboottodoapp.models.User;
import com.example.springboottodoapp.repository.TodoItemRepository;
import com.example.springboottodoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RequiredArgsConstructor
@EnableWebMvc
@EnableSwagger2
public class SpringBootTodoappApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TodoItemRepository todoItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTodoappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setId(1L);
        user.setFirstName("Faik");
        user.setLastName("Turan");
        user.setEmail("turanfaik@hotmail.com");
        user.setPassword(passwordEncoder.encode("password"));

        TodoItem todoItem = new TodoItem();
        todoItem.setId(1L);
        todoItem.setDescription("Start the Todo app");
        todoItem.getCompleted();

        user.getTodoItems().add(todoItem);

        todoItemRepository.save(todoItem);
        userRepository.save(user);
    }
}
