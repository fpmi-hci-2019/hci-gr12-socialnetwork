package com.epam.esm.service.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PaginationDto<T> {
    private Collection<T> collection;
    private Links links;
}
