package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;
    
    @Mock
    private TaskMapper taskMapper;
    
    @Mock
    private DbService dbService;

    @Test
    public void shouldGetTasks() {
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

        TaskDto taskDto1 = new TaskDto(1L, "Task1", "Cont1");
        TaskDto taskDto2 = new TaskDto(2L, "Task2", "Cont2");
        TaskDto taskDto3 = new TaskDto(3L, "Task3", "Cont3");
        List<TaskDto> taskDtoList = new ArrayList<TaskDto>() {
            {
                add(taskDto1);
                add(taskDto2);
                add(taskDto3);
            }
        };
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        // When
        List<TaskDto> taskDtoList1 = taskController.getTasks();

        // Then
        assertEquals(taskList.size(), taskDtoList1.size());
                
    }

    @Test
    public void shouldGetTask() throws Exception{
        // Given
        Task task = new Task(1L, "Task1", "Cont1");
        TaskDto taskDto = new TaskDto(1L, "Task1", "Cont1");
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        TaskDto taskDto1 = taskController.getTask(1L);

        // Then

        assertEquals(task.getContent(), taskDto1.getContent());

    }

    @Test
    public void shouldDeleteTask() {
        // Given
        doNothing().when(dbService).deleteTask(1L);

        // When

        // Then
        taskController.deleteTask(1L);
    }

    @Test
    public void shouldUpdateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title", "content");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When
        TaskDto taskDto1 = taskController.updateTask(taskDto);

        // Then
        assertEquals(taskDto.getContent(), taskDto1.getContent());
    }

    @Test
    public void shouldCreateTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Task task = new Task(1L, "title1", "content1");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        // When

        // Then
        taskController.createTask(taskDto);
    }
}