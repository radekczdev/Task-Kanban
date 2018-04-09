package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoards() {
        // Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("test_id", "test_board", Collections.emptyList()));

        // When
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        // Then
        List<TrelloBoardDto> trelloBoardsFromClient = trelloService.fetchTrelloBoards();
        assertEquals(trelloBoards.size(), trelloBoardsFromClient.size());
    }

    @Test
    public void createTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com",
                null
        );

        // When
        when(trelloClient.createNewCard(any())).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");

        // Then
        CreatedTrelloCardDto createdTrelloCardDtoFromService = trelloService.createTrelloCard(trelloCardDto);
        assertEquals(trelloCardDto.getName(), createdTrelloCardDtoFromService.getName());
    }
}