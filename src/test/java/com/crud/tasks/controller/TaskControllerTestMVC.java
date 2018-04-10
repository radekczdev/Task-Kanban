package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestMVC {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @Test
    public void getTasks() throws Exception {
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

        // When
        when(taskController.getTasks()).thenReturn(taskDtoList);

        // Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].title", is("Task1")))
                .andExpect(jsonPath("$[1].title", is("Task2")))
                .andExpect(jsonPath("$[2].title", is("Task3")))
                .andExpect(jsonPath("$[0].content", is("Cont1")))
                .andExpect(jsonPath("$[1].content", is("Cont2")))
                .andExpect(jsonPath("$[2].content", is("Cont3")));
    }

    @Test
    public void getTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Task1", "Cont1");

        // When
        when(taskController.getTask(anyLong())).thenReturn(taskDto);

        // Then
        mockMvc.perform(get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task1")))
                .andExpect(jsonPath("$.content", is("Cont1")));
    }


    @Test
    public void deleteTask() throws Exception {
        // Given
        // When
        doNothing().when(taskController).deleteTask(anyLong());

        // Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void updateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Gson gson = new Gson();
        String json = gson.toJson(taskDto);
        // When
        when(taskController.updateTask(any(TaskDto.class))).thenReturn(taskDto);


        // Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.content", is("content1")));
    }

    @Test
    public void createTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        Gson gson = new Gson();
        String json = gson.toJson(taskDto);

        // When
        doNothing().when(taskController).createTask(any(TaskDto.class));

        // Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}