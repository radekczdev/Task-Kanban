package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTest {
    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    public void getTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "my_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloLists));

        // When
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // Then
        List<TrelloBoardDto> trelloBoardsFetched = trelloController.getTrelloBoards();
        assertEquals(trelloBoards.size(), trelloBoardsFetched.size());
    }

    @Test
    public void createTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Trello card", "Desc", "position", "12");
        TrelloBadgesDto trelloBadgesDto = new TrelloBadgesDto(12, new HashMap<>());
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("Trello", "Trello", "", trelloBadgesDto);

        // When
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // Then
        CreatedTrelloCardDto createdTrelloCardDtoFetched = trelloController.createTrelloCard(trelloCardDto);
        assertEquals(createdTrelloCardDto.getName(), createdTrelloCardDtoFetched.getName());
        assertEquals(createdTrelloCardDto.getTrelloBadgesDto().getAttachmentsByType(), createdTrelloCardDtoFetched.getTrelloBadgesDto().getAttachmentsByType());
        assertEquals(createdTrelloCardDto.getTrelloBadgesDto().getVotes(), createdTrelloCardDtoFetched.getTrelloBadgesDto().getVotes());

    }
}