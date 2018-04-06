package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTest {
    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        // Given
        TrelloList trelloList1 = new TrelloList("1", "Test list 1", true);
        TrelloList trelloList2 = new TrelloList("2", "Test list 2", false);
        TrelloList trelloList3 = new TrelloList("3", "Test list 3", true);
        TrelloList trelloList4 = new TrelloList("4", "Test list 4", false);
        TrelloList trelloList5 = new TrelloList("5", "Test list 5", false);
        List<TrelloList> trelloLists1 = new ArrayList<TrelloList>() {
            {
                add(trelloList1);
                add(trelloList2);
            }
        };
        List<TrelloList> trelloLists2 = new ArrayList<TrelloList>() {
            {
                add(trelloList3);
                add(trelloList4);
                add(trelloList5);
            }
        };
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Trello Board", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("1", "Trello Board", trelloLists2);
        List<TrelloBoard> trelloBoards = new ArrayList<TrelloBoard>() {
            {
                add(trelloBoard1);
                add(trelloBoard2);
            }
        };

        // When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoards);
        List<TrelloBoard> trelloBoardsAfterMapping = trelloMapper.mapToBoards(trelloBoardDto);

        // Then
        int trelloBoardsAmount = trelloBoards.size();
        int trelloBoardsAmountAfterMapping = trelloBoardsAfterMapping.size();
        String trelloBoard1Name = trelloBoard1.getName();
        String trelloBoard1NameAfterMapping = trelloBoardsAfterMapping.get(0).getName();
        assertEquals(trelloBoardsAmount, trelloBoardsAmountAfterMapping);
        assertEquals(trelloBoard1Name, trelloBoard1NameAfterMapping);

    }

    @Test
    public void testMapToList() {
        // Given
        TrelloList trelloList1 = new TrelloList("1", "Test list 1", true);
        TrelloList trelloList2 = new TrelloList("2", "Test list 2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);

        // When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        List<TrelloList> trelloListAfterMappings = trelloMapper.mapToList(trelloListsDto);

        // Then
        int numberOfLists = trelloLists.size();
        int numberOfListsAfterMappings = trelloListAfterMappings.size();
        String nameOfSecondList = trelloList2.getName();
        String nameOfSecondListAfterMappings = trelloListAfterMappings.get(1).getName();
        assertEquals(numberOfLists, numberOfListsAfterMappings);
        assertEquals(nameOfSecondList, nameOfSecondListAfterMappings);
    }


    @Test
    public void testMapToCard() {
        // Given
        TrelloCard trelloCard = new TrelloCard("card", "desc", "pos", "id1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        TrelloCard trelloCardAfterMapping = trelloMapper.mapToCard(trelloCardDto);

        // Then
        String nameAfterMapping = trelloCardAfterMapping.getName();
        String descAfterMapping = trelloCardAfterMapping.getDescription();
        String posAfterMapping = trelloCardAfterMapping.getPos();
        String listIdAfterMapping = trelloCardAfterMapping.getListId();
        assertEquals(trelloCard.getName(), nameAfterMapping);
        assertEquals(trelloCard.getDescription(), descAfterMapping);
        assertEquals(trelloCard.getPos(), posAfterMapping);
        assertEquals(trelloCard.getListId(), listIdAfterMapping);
    }

}