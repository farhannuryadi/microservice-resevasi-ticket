package com.farhan.scheduleservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SearchRequest {

    @NotEmpty(message = "searchKey is required")
    private String searchKey;
}
