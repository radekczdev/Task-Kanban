package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        // Given
        Task task1 = new Task(1L, "Task1", "Cont1");
        Task task2 = new Task(2L, "Task2", "Cont2");
        Task task3 = new Task(3L, "Task3", "Cont3");
        List<Task> taskList = new ArrayList<Task>() {
            {
                add(task1);
                add(task2);
                add(task3);
            }
        };

        // When
        when(taskRepository.findAll()).thenReturn(taskList);

        // Then
        List<Task> taskListFromDb = dbService.getAllTasks();
        assertEquals(taskList.size(), taskListFromDb.size());
    }

    @Test
    public void getTask() {
        // Given
        Task task = new Task(1L, "Task1", "Cont1");

        // When
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Then
        Task taskFromDb = dbService.getTask(1L).get();
        assertEquals(task.getTitle(), taskFromDb.getTitle());
    }

    @Test
    public void saveTask() {
        // Given
        Task task = new Task(1L, "title1", "content1");

        // When
        when(taskRepository.save(task)).thenReturn(task);

        // Then
        Task savedTask = dbService.saveTask(task);
        assertEquals(task.getTitle(), savedTask.getTitle());
    }

    @Test
    public void deleteTask() {
        // Given
        // When
        doNothing().when(taskRepository).deleteById(1L);

        // Then
        dbService.deleteTask(1L);
    }
}