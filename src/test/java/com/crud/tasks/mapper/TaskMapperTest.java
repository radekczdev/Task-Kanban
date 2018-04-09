package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTest {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(123L, "Task title", "Task content");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(task.getId(), task.getId());
        assertEquals(task.getTitle(), task.getTitle());
        assertEquals(task.getContent(), task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        // Given
        Task task = new Task(123L, "Task title", "Task content");

        // When
        TaskDto dto = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(task.getId(), dto.getId());
        assertEquals(task.getTitle(), dto.getTitle());
        assertEquals(task.getContent(), dto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        Task task1 = new Task(123L, "Task title", "Task content");
        Task task2 = new Task(124L, "Task title", "Task content");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertEquals(2, taskDtoList.size());
    }
}