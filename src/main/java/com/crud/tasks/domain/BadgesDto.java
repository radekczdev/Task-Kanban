package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BadgesDto {
    private int votes;
    private AttachementsByTypeDto attachmentsByTypeDto;
}
