package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrelloBadgesDto {
    private int votes;
    private Map<String, Map<String, Integer>> attachmentsByType;
}
